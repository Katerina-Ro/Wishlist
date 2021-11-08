package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.utils.COMMANDS;

import java.util.ArrayList;
import java.util.List;

public class ButtonBack {
    private static final String BUTTONBACK = "Назад";

    public static List <List<InlineKeyboardButton>>  getKeyBoardButtonBack(){
        //InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTONBACK);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.BACK.getCommand());

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonButtonBack);

        keyboardButtons.add(keyboardButtonRow1);

        //inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return keyboardButtons;
    }
}
