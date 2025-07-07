package by.danefka.tgbot_v2.services;

import by.danefka.tgbot_v2.entity.UserContextPostgres;
import by.danefka.tgbot_v2.entity.UserContextRedis;
import by.danefka.tgbot_v2.exception.UserContextNotFoundException;
import by.danefka.tgbot_v2.mapper.UserContextMapper;
import by.danefka.tgbot_v2.model.UserContext;
import by.danefka.tgbot_v2.repository.UserContextPostgresRepository;
import by.danefka.tgbot_v2.repository.UserContextRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

        Optional<UserContextPostgres> userContextPostgres = postgresRepository.getByUserId(userId);
        if (userContextPostgres.isPresent()) {
            redisRepository.save(userContextMapper.toRedis(userContextPostgres.get()));
            return userContextMapper.toEntity(userContextPostgres.get());
        }

        throw new UserContextNotFoundException();
    }

    public void saveUserContext(UserContext userContext) {
        redisRepository.save(userContextMapper.toRedis(userContext));
    }


    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void persistCacheToDatabase() {
        StreamSupport.stream(redisRepository.findAll().spliterator(), false)
                .map(userContextMapper::toEntity)
                .map(userContextMapper::toPostgres)
                .forEach(postgresRepository::save);
    }

}
