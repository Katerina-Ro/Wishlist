package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.modelEntities.Gift;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ListButtonsNameGift {
    private final GiftRepository giftRepository;

    @Autowired
    public ListButtonsNameGift(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Collection<InlineKeyboardButton> getKeyBoardListButtonsNameGift(Update update) throws SQLException {
        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        List<Gift> listGift = (List<Gift>) giftRepository.getInfoGift(update.getUpdateId());
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
