package by.danefka.tgbot_v2.repository;

import by.danefka.tgbot_v2.entity.UserContextPostgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContextPostgresRepository extends JpaRepository<UserContextPostgres, Long> {
    Optional<UserContextPostgres> getByUserId(Long userId);
}
