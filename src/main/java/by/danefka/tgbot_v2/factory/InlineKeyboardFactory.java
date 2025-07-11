package by.danefka.tgbot_v2.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class InlineKeyboardFactory {
    private final InlineKeyboardMarkup mainMenu;

    public InlineKeyboardFactory() {
        this.mainMenu = initMainMenu();
    }

    public InlineKeyboardMarkup mainMenu() {
        return mainMenu;
    }

    private InlineKeyboardMarkup initMainMenu() {
        return new InlineKeyboardMarkup(List.of(
                List.of(button("🔍 Найти игру", "search")),
                List.of(button("➕ Добавить игру", "add_game_to_collection")),
                List.of(button("🧾 Игровые сессии", "add_gamesession"))
        ));
    }

    private InlineKeyboardButton button(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
