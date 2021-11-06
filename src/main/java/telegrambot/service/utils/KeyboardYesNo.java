package telegrambot.service.utils;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KeyboardYesNo {
    private static final String YES_LABEL = "Да";
    private static final String NO_LABLE = "Нет";

    //static ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public static InlineKeyboardMarkup getKeyBoardYesNo(){
        /*
        List<KeyboardRow> listKeyboard = new ArrayList<>();
        KeyboardRow keyboardRowFirs = new KeyboardRow();
        KeyboardRow keyboardRowSecond = new KeyboardRow();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>(); */

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonInfo = new InlineKeyboardButton();
        inlineKeyboardButtonInfo.setText(YES_LABEL);
        inlineKeyboardButtonInfo.setCallbackData(COMMANDS.INFO.getCommand());

        InlineKeyboardButton inlineKeyboardButtonADD = new InlineKeyboardButton();
        inlineKeyboardButtonADD.setText(NO_LABLE);
        inlineKeyboardButtonADD.setCallbackData(COMMANDS.ADD_WISH.getCommand());

        InlineKeyboardButton inlineKeyboardButtonGet = new InlineKeyboardButton();
        inlineKeyboardButtonGet.setText(GET_WISHLIST_LABLE);
        inlineKeyboardButtonGet.setCallbackData(COMMANDS.WISHLIST.getCommand());

        InlineKeyboardButton inlineKeyboardButtonChange = new InlineKeyboardButton();
        inlineKeyboardButtonChange.setText(CHANGE_WISH_LABLE);
        inlineKeyboardButtonChange.setCallbackData(COMMANDS.CHANGE_WISH.getCommand());

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonInfo);
        keyboardButtonRow1.add (inlineKeyboardButtonGet);

        List<InlineKeyboardButton> keyboardButtonRow2 = new ArrayList<>();
        keyboardButtonRow2.add (inlineKeyboardButtonADD);
        keyboardButtonRow2.add (inlineKeyboardButtonChange);

        keyboardButtons.add(keyboardButtonRow1);
        keyboardButtons.add(keyboardButtonRow2);

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;
    }
}
