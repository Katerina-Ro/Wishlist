package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.utils.COMMANDS;

import java.util.ArrayList;
import java.util.List;

public class ButtonChooseThis {
    private static final String BUTTON_CHOOSE_THIS = "Выбрать";
    private static String nameWish;

    public static List <List<InlineKeyboardButton>>  getKeyBoardButtonChooseThis(){
        //InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonButtonChooseThis = new InlineKeyboardButton();
        inlineKeyboardButtonButtonChooseThis.setText(BUTTON_CHOOSE_THIS);
        inlineKeyboardButtonButtonChooseThis.setCallbackData(COMMANDS.CHOOSE.getCommand());

        InlineKeyboardButton inlineKeyboardButtonButtonWishFromDB = new InlineKeyboardButton();
        inlineKeyboardButtonButtonWishFromDB.setText(nameWish);

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonButtonChooseThis);
        List<InlineKeyboardButton> keyboardButtonRow2 = new ArrayList<>();
        keyboardButtonRow2.add (inlineKeyboardButtonButtonWishFromDB);

        keyboardButtons.add(keyboardButtonRow1);

        //inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return keyboardButtons;
    }
}
