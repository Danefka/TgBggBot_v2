package by.danefka.tgbot_v2.entity;

import by.danefka.tgbot_v2.model.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@RedisHash("UserContext")
public class UserContextRedis {
    @Id
    private long userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @TimeToLive
    private long ttl = 180L;
}