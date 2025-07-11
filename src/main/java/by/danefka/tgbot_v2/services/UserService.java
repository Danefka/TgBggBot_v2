package by.danefka.tgbot_v2.services;

import by.danefka.tgbot_v2.entity.UserContext;
import by.danefka.tgbot_v2.entity.UserContextRedis;
import by.danefka.tgbot_v2.exception.UserContextNotFoundException;
import by.danefka.tgbot_v2.mapper.UserContextMapper;
import by.danefka.tgbot_v2.repository.UserContextPostgresRepository;
import by.danefka.tgbot_v2.repository.UserContextRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserContextPostgresRepository postgresRepository;
    private final UserContextRedisRepository redisRepository;

    private final UserContextMapper userContextMapper;

    public UserContext getUserContext(Long userId) {
        Optional<UserContextRedis> userContextRedis = redisRepository.getByUserId(userId);
        if (userContextRedis.isPresent()) {
            return userContextMapper.toEntity(userContextRedis.get());
        }

        Optional<UserContext> userContext = postgresRepository.getByUserId(userId);
        if (userContext.isPresent()) {
            redisRepository.save(userContextMapper.toRedis(userContext.get()));
            return userContext.get();
        }

        throw new UserContextNotFoundException();
    }

    public void saveUserContext(UserContext userContext) {
        redisRepository.save(userContextMapper.toRedis(userContext));
    }


    @Scheduled(fixedRate = 60 * 1000)
    public void persistCacheToDatabase() {
        StreamSupport.stream(redisRepository.findAll().spliterator(), false)
                .map(userContextMapper::toEntity)
                .filter(Objects::nonNull)
                .forEach(postgresRepository::save);
    }

}
