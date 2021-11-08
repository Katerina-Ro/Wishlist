package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.utils.COMMANDS;

import java.util.ArrayList;
import java.util.List;

public class ButtonSend {
    private static final String BUTTON_SEND = "Отправить";

    public static InlineKeyboardMarkup getKeyBoardButtonSend(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonButtonSend = new InlineKeyboardButton();
        inlineKeyboardButtonButtonSend.setText(BUTTON_SEND);
        inlineKeyboardButtonButtonSend.setCallbackData(COMMANDS.SEND.getCommand());

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonButtonSend);

        keyboardButtons.add(keyboardButtonRow1);

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;
    }
}
