package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ListButtonsState {
    private final GiftRepository giftRepository;

    @Autowired
    public ListButtonsState(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Collection<InlineKeyboardButton> getKeyBoardListButtonsNameGift(Long chat_id) {
        List<InlineKeyboardButton> keyboardButtonRow = new ArrayList<>();
        for(int i = 1; i == giftRepository.findAllWishesChatIdUser(chat_id).size(); i++) {
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
