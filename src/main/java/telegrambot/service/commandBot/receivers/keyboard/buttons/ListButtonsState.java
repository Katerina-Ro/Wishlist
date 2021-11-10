package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ListButtonsState {
    private final GiftRepository giftRepository;

    @Autowired
    public ListButtonsState(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Collection<InlineKeyboardButton> getKeyBoardListButtonsNameGift(Update update) {
        List<InlineKeyboardButton> keyboardButtonRow = new ArrayList<>();
        for(int i = 1; i == giftRepository.findAllByChatId(update.getUpdateId()).size(); i++) {
            keyboardButtonRow.add(ButtonState.getKeyBoardButtonState());
        }
        return keyboardButtonRow;
    }

    static class ButtonState {
        public static InlineKeyboardButton getKeyBoardButtonState(){
            InlineKeyboardButton inlineKeyboardButtonButtonState = new InlineKeyboardButton();
            inlineKeyboardButtonButtonState.setText(" ");
            inlineKeyboardButtonButtonState.setCallbackData(COMMANDS.STATE.getCommand());
            return inlineKeyboardButtonButtonState;
        }
    }
}
