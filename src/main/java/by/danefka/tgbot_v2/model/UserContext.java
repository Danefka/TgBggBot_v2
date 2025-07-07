package by.danefka.tgbot_v2.model;

import lombok.Data;

@Data
public class UserContext {
    private long id;
    private long userId;
    private String userName;
    private UserStatus status;
}
