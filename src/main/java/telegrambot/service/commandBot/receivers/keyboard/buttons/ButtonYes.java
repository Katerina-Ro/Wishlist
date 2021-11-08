package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.utils.COMMANDS;

public class ButtonYes {
    private static final String YES_LABEL = "Да";

    public static InlineKeyboardButton getKeyBoardYes(boolean activeState){
        InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
        inlineKeyboardButtonYes.setText(YES_LABEL);
        inlineKeyboardButtonYes.setCallbackData(COMMANDS.YES.getCommand());
        return inlineKeyboardButtonYes;
    }
}
