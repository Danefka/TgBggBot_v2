package by.danefka.tgbot_v2.logic.textHandler;


import by.danefka.tgbot_v2.annotation.TextHandler;
import by.danefka.tgbot_v2.handler.TextCommandHandler;
import by.danefka.tgbot_v2.services.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@TextHandler("/help")
@RequiredArgsConstructor
public class HelpCommandHandler implements TextCommandHandler {

    private final TelegramService telegramService;

    @Override
    public void handle(Update update) {
        SendMessage message = new SendMessage();
        message.setText("I can do many things like...");
        message.setChatId(update.getMessage().getChatId());
        telegramService.sendMessage(message);
    }
}
