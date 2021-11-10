package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.entities.Gift;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ListButtonsNameGift {
    private final GiftRepository giftRepository;

    @Autowired
    public ListButtonsNameGift(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Collection<InlineKeyboardButton> getKeyBoardListButtonsNameGift(Update update){
        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        List<Gift> listGift = giftRepository.findAllByChatId(update.getUpdateId());
        for(Gift g: listGift){
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
