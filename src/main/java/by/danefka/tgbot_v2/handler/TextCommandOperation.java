package by.danefka.tgbot_v2.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TextCommandOperation {
    void handle(Update update);
}
