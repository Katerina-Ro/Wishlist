package telegrambot.service.commandBot.receivers.keyboard;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.utils.COMMANDS;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KeyboardMenu {

    private static final String INFO_LABEL = "О чем канал?";
    private static final String ADD_LABLE = "Добавить пожелание";
    private static final String GET_WISHLIST_LABLE = "Посмотреть список желаний";
    private static final String CHANGE_WISH_LABLE = "Изменить желание";

    public static InlineKeyboardMarkup getKeyBoardMenu(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonInfo = new InlineKeyboardButton();
        inlineKeyboardButtonInfo.setText(INFO_LABEL);
        inlineKeyboardButtonInfo.setCallbackData(COMMANDS.INFO.getCommand());

        InlineKeyboardButton inlineKeyboardButtonADD = new InlineKeyboardButton();
        inlineKeyboardButtonADD.setText(ADD_LABLE);
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
