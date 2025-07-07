package by.danefka.tgbot_v2.handler;

import by.danefka.tgbot_v2.annotation.InlineKeyHandler;
import by.danefka.tgbot_v2.annotation.TextHandler;
import by.danefka.tgbot_v2.annotation.UserStatusHandler;
import by.danefka.tgbot_v2.model.UserContext;
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

    private final Map<String, TextCommandHandler> textHandlers = new HashMap<>();
    private final Map<String, InlineKeyCommandHandler> inlineKeyHandlers = new HashMap<>();
    private final Map<UserStatus, UserStatusCommandHandler> userStatusHandlers = new HashMap<>();

    private final UserService userService;

    @Autowired
    public UpdateDispatcher(List<TextCommandHandler> textCommandHandlersBeans, List<InlineKeyCommandHandler> inlineKeyCommandHandlersBeans, List<UserStatusCommandHandler> userStatusHandlersBeans, UserService userService) {
        this.userService = userService;
        for (TextCommandHandler textCommandHandler : textCommandHandlersBeans) {
            if (textCommandHandler != null) {
                TextHandler annotation = textCommandHandler.getClass().getAnnotation(TextHandler.class);
                if (annotation != null) {
                    textHandlers.put(annotation.value(), textCommandHandler);
                }
            }
        }
        for (InlineKeyCommandHandler inlineKeyCommandHandler : inlineKeyCommandHandlersBeans) {
            if (inlineKeyCommandHandler != null) {
                InlineKeyHandler annotation = inlineKeyCommandHandler.getClass().getAnnotation(InlineKeyHandler.class);
                if (annotation != null) {
                    inlineKeyHandlers.put(annotation.value(), inlineKeyCommandHandler);
                }
            }
        }

        for (UserStatusCommandHandler userStatusCommandHandler : userStatusHandlersBeans) {
            if (userStatusCommandHandler != null) {
                UserStatusHandler annotation = userStatusCommandHandler.getClass().getAnnotation(UserStatusHandler.class);
                if (annotation != null) {
                    userStatusHandlers.put(annotation.value(), userStatusCommandHandler);
                }
            }
        }
    }

    public void dispatch(Update update) {
        if (update.hasCallbackQuery()) {
            InlineKeyCommandHandler handler = inlineKeyHandlers.get(update.getCallbackQuery().getData());
            if (handler != null) {
                handler.handle(update);
            }
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            TextCommandHandler handler = textHandlers.get(text);
            if (handler != null) {
                handler.handle(update);
            }

            UserContext userContext = userService.getUserContext(update.getMessage().getChatId());
            if (userContext != null && userContext.getStatus() != null) {
                UserStatusCommandHandler statusHandler = userStatusHandlers.get(userContext.getStatus());
                if (statusHandler != null) {
                    statusHandler.handle(update);
                }
            }
        }
    }
}
