package by.danefka.tgbot_v2.mapper;

import by.danefka.tgbot_v2.entity.UserContext;
import by.danefka.tgbot_v2.entity.UserContextRedis;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserContextMapper {

    UserContext toEntity(UserContextRedis userContextRedis);
    @Mapping(target = "ttl", ignore = true)
    UserContextRedis toRedis(UserContext userContext);
}
