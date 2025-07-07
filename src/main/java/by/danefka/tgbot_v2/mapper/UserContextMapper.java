package by.danefka.tgbot_v2.mapper;

import by.danefka.tgbot_v2.entity.UserContextPostgres;
import by.danefka.tgbot_v2.entity.UserContextRedis;
import by.danefka.tgbot_v2.model.UserContext;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserContextMapper {

    UserContext toEntity(UserContextRedis userContextRedis);
    UserContext toEntity(UserContextPostgres userContextPostgres);

    UserContextRedis toRedis(UserContext userContext);
    UserContextRedis toRedis(UserContextPostgres userContextPostgres);

    UserContextPostgres toPostgres(UserContext userContext);
    UserContextPostgres toPostgres(UserContextRedis userContextRedis);
}
