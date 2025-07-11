package by.danefka.tgbot_v2.logic.textHandler;

import by.danefka.tgbot_v2.annotation.TextHandler;
import by.danefka.tgbot_v2.entity.UserContext;
import by.danefka.tgbot_v2.factory.InlineKeyboardFactory;
import by.danefka.tgbot_v2.handler.TextCommandOperation;
import by.danefka.tgbot_v2.model.UserStatus;
import by.danefka.tgbot_v2.services.TelegramService;
import by.danefka.tgbot_v2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
@TextHandler("/start")
@RequiredArgsConstructor
public class StartCommandOperation implements TextCommandOperation {

    private final TelegramService telegramService;
    private final UserService userService;
    private final InlineKeyboardFactory inlineKeyboardFactory;

    @Override
    public void handle(Update update) {
        SendMessage message = new SendMessage();

        message.setText("hello!");
        message.setChatId(update.getMessage().getChatId());
        message.setReplyMarkup(inlineKeyboardFactory.mainMenu());

        UserContext userContext = new UserContext();
        userContext.setUserId(update.getMessage().getFrom().getId());
        userContext.setUserName(update.getMessage().getFrom().getUserName());
        userContext.setUserStatus(UserStatus.Default);

        userService.saveUserContext(userContext);
        telegramService.sendMessage(message);
    }
}
