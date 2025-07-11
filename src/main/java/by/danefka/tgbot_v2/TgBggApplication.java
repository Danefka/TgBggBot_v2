package by.danefka.tgbot_v2;

import by.danefka.tgbot_v2.configuration.BotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({BotProperties.class})
@EnableScheduling
public class TgBggApplication {
	public static void main(String[] args) {
		SpringApplication.run(TgBggApplication.class, args);
	}
}
