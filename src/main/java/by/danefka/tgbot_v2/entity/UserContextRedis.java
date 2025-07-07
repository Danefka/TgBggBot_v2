package by.danefka.tgbot_v2.entity;

import by.danefka.tgbot_v2.model.UserStatus;
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

    private UserStatus status;

    @TimeToLive
    private long ttl = 1800L;
}
