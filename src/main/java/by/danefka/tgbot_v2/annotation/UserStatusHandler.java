package by.danefka.tgbot_v2.annotation;

import by.danefka.tgbot_v2.model.UserStatus;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UserStatusHandler {
    UserStatus value();
}
