package by.danefka.tgbot_v2.repository;

import by.danefka.tgbot_v2.entity.UserContextRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContextRedisRepository extends CrudRepository<UserContextRedis, Long> {
    @Override
    boolean existsById(Long userId);

    Optional<UserContextRedis> getByUserId(Long id);
}
