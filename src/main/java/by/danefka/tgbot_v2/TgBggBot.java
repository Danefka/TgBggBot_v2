package by.danefka.tgbot_v2;

import by.danefka.tgbot_v2.configuration.BotProperties;
import by.danefka.tgbot_v2.handler.UpdateDispatcher;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
@Singleton
public class TgBggBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private final UpdateDispatcher updateDispatcher;


    @Override
    public void onUpdateReceived(Update update) {
        updateDispatcher.dispatch(update);
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUserName();
    }

    @Override
    public String getBotToken(){
        return botProperties.getToken();
    }
}
