package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.utils.COMMANDS;

import java.util.ArrayList;
import java.util.List;

public class ButtonMoreDetails {
    private static final String MORE_DETAILS = "Подробнее";

    public static InlineKeyboardMarkup getKeyBoardMoreDetails(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonMoreDetails = new InlineKeyboardButton();
        inlineKeyboardButtonMoreDetails.setText(MORE_DETAILS);
        inlineKeyboardButtonMoreDetails.setCallbackData(COMMANDS.MORE_DETAILS.getCommand());

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonMoreDetails);

        keyboardButtons.add(keyboardButtonRow1);

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;
    }
}
