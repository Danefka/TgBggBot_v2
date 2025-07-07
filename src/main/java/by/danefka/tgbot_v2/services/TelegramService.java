package by.danefka.tgbot_v2.services;

import by.danefka.tgbot_v2.TgBggBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramService {
    private final TgBggBot bot;


    @Autowired
    public TelegramService(@Lazy TgBggBot bot) {
        this.bot = bot;
    }

    public void sendMessage(SendMessage message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(DeleteMessage message){
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void editMessageText(EditMessageText messageText){
        try {
            bot.execute(messageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
