package telegrambot.service.commandBot.receivers.utils.keyboard.buttons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.entityservice.WishService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListButtonsNameGift {
    private final WishService wishService;

    @Autowired
    public ListButtonsNameGift(WishService wishService) {
        this.wishService = wishService;
    }
// giftRepository.findAllWishesChatIdUser(chat_id)
    public List<InlineKeyboardButton> getKeyBoardListButtonsNameGift(Long chat_id){
        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        for(Gift g: wishService.getGiftRepository().findAllByGiftOwnerChatId(chat_id)){
            keyboardButtonRow1.add(ButtonNameGift.getKeyBoardButtonNameGift(g.getNameGift()));
        }
        return keyboardButtonRow1;

    }

    static class ButtonNameGift {
        public static InlineKeyboardButton getKeyBoardButtonNameGift(String nameGift){
            InlineKeyboardButton inlineKeyboardButtonButtonNameGift = new InlineKeyboardButton();
            inlineKeyboardButtonButtonNameGift.setText(nameGift);
            inlineKeyboardButtonButtonNameGift.setCallbackData(COMMANDS.NAME_GIFT.getCommand());
            return inlineKeyboardButtonButtonNameGift;
        }
    }
}
