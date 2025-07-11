package by.danefka.tgbot_v2.handler;

import by.danefka.tgbot_v2.annotation.InlineKeyHandler;
import by.danefka.tgbot_v2.annotation.TextHandler;
import by.danefka.tgbot_v2.annotation.UserStatusHandler;
import by.danefka.tgbot_v2.entity.UserContext;
import by.danefka.tgbot_v2.model.UserStatus;
import by.danefka.tgbot_v2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UpdateDispatcher {

    private final Map<String, TextCommandOperation> textHandlers = new HashMap<>();
    private final Map<String, InlineKeyCommandOperation> inlineKeyHandlers = new HashMap<>();
    private final Map<UserStatus, UserStatusCommandOperation> userStatusHandlers = new HashMap<>();

    private final UserService userService;

    @Autowired
    public UpdateDispatcher(List<TextCommandOperation> textCommandHandlersBeans, List<InlineKeyCommandOperation> inlineKeyCommandHandlersBeans, List<UserStatusCommandOperation> userStatusHandlersBeans, UserService userService) {
        this.userService = userService;
        for (TextCommandOperation textCommandOperation : textCommandHandlersBeans) {
            if (textCommandOperation != null) {
                TextHandler annotation = textCommandOperation.getClass().getAnnotation(TextHandler.class);
                if (annotation != null) {
                    textHandlers.put(annotation.value(), textCommandOperation);
                }
            }
        }
        for (InlineKeyCommandOperation inlineKeyCommandOperation : inlineKeyCommandHandlersBeans) {
            if (inlineKeyCommandOperation != null) {
                InlineKeyHandler annotation = inlineKeyCommandOperation.getClass().getAnnotation(InlineKeyHandler.class);
                if (annotation != null) {
                    inlineKeyHandlers.put(annotation.value(), inlineKeyCommandOperation);
                }
            }
        }

        for (UserStatusCommandOperation userStatusCommandOperation : userStatusHandlersBeans) {
            if (userStatusCommandOperation != null) {
                UserStatusHandler annotation = userStatusCommandOperation.getClass().getAnnotation(UserStatusHandler.class);
                if (annotation != null) {
                    userStatusHandlers.put(annotation.value(), userStatusCommandOperation);
                }
            }
        }
    }

    public void dispatch(Update update) {
        if (update.hasCallbackQuery()) {
            InlineKeyCommandOperation handler = inlineKeyHandlers.get(update.getCallbackQuery().getData());
            if (handler != null) {
                handler.handle(update);
            }
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            TextCommandOperation handler = textHandlers.get(text);
            if (handler != null) {
                handler.handle(update);
                return;
            }

            UserContext userContext = userService.getUserContext(update.getMessage().getChatId());
            if (userContext != null && userContext.getUserStatus() != null) {
                UserStatusCommandOperation statusHandler = userStatusHandlers.get(userContext.getUserStatus());
                if (statusHandler != null) {
                    statusHandler.handle(update);
                }
            }
        }
    }
}
