package by.danefka.tgbot_v2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bot")
@PropertySource("classpath:application.properties")
@Data
public class BotProperties {
    private  String userName;
    private  String token;
}
