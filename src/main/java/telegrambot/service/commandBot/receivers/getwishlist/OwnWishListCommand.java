package telegrambot.service.commandBot.receivers.getwishlist;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.ListButtonsNameGift;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.ListButtonsState;
import telegrambot.service.entityservice.WishService;

import java.util.Map;

@Service
@Getter
public class OwnWishListCommand implements CommandEditSendMessage {
    private final String MESSAGE_OWN_WISHLIST = "Ваш список пожеланий ";
    private final ListButtonsState listButtonsState;
    private final ListButtonsNameGift listButtonsNameGift;
    private final WishService wishService;

    @Autowired
    public OwnWishListCommand(ListButtonsState listButtonsState, ListButtonsNameGift listButtonsNameGift, WishService wishService) {
        this.listButtonsState = listButtonsState;
        this.listButtonsNameGift = listButtonsNameGift;
        this.wishService = wishService;
    }

    @Override
    public EditMessageText execute(Update update) {
        long chatIdUser = update.getCallbackQuery().getMessage().getChatId();
        Map<Integer, Gift> mapGifts = wishService.getInfoGifts(chatIdUser);

        return new EditMessageText()
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(MESSAGE_OWN_WISHLIST)
                .setChatId(chatIdUser)
                .setReplyMarkup(MakerInlineKeyboardMarkup.get2x2InlineKeyboardMarkup(mapGifts));
    }
}
