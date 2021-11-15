package telegrambot.service.commandBot.receivers.utils.keyboard.buttons;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.entityservice.WishService;

import java.util.Map;

@Service
@Getter
public class ListButtonsState {
    public static InlineKeyboardButton getKeyBoardButtonState(String statusGift) {
        InlineKeyboardButton inlineKeyboardButtonButtonState = new InlineKeyboardButton();
        inlineKeyboardButtonButtonState.setText(statusGift);
        inlineKeyboardButtonButtonState.setCallbackData(COMMANDS.STATE_DB.getCommand());
        return inlineKeyboardButtonButtonState;
    }
}

        /*
    public List<InlineKeyboardButton> getKeyBoardListButtonsStateGift(Long chat_id) {
        List<InlineKeyboardButton> keyboardButtonRow = new ArrayList<>();
        for(int i = 0; i <=  wishService.getGiftRepository().findAllWishesChatIdUser(chat_id).size(); i++) {

            System.out.println(" внутри цикла состояние подарка i= " + i);

            keyboardButtonRow.add(ButtonState.getKeyBoardButtonState());
        }
        return keyboardButtonRow;
    }

    /*
    static class ButtonState {
        public static InlineKeyboardButton getKeyBoardButtonState(){
            InlineKeyboardButton inlineKeyboardButtonButtonState = new InlineKeyboardButton();
            inlineKeyboardButtonButtonState.setText("Активно");
            inlineKeyboardButtonButtonState.setCallbackData(COMMANDS.STATE_DB.getCommand());
            return inlineKeyboardButtonButtonState;
        } */

