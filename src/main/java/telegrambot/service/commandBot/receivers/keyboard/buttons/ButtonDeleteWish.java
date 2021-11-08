package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import telegrambot.service.commandBot.utils.COMMANDS;

import java.util.ArrayList;
import java.util.List;

public class ButtonDeleteWish {
    private static final String BUTTONDELETE = "Удалить";

    public static InlineKeyboardMarkup getKeyBoardDeleteWish(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonDeleteWish = new InlineKeyboardButton();
        inlineKeyboardButtonDeleteWish.setText(BUTTONDELETE);
        inlineKeyboardButtonDeleteWish.setCallbackData(COMMANDS.DELETE.getCommand());

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonDeleteWish);

        keyboardButtons.add(keyboardButtonRow1);

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;
    }
}
