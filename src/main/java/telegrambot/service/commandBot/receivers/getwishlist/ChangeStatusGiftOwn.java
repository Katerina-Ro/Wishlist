package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.entities.StatusGift.ChangeStatusGiftImpl;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;

@Service
public class ChangeStatusGiftOwn implements CommandEditSendMessage {
    private final WishService wishService;
    private final ChangeStatusGiftImpl changeStatusGift;

    @Autowired
    public ChangeStatusGiftOwn(WishService wishService, ChangeStatusGiftImpl changeStatusGift1) {
        this.wishService = wishService;
        this.changeStatusGift = changeStatusGift1;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        String incomingMessage = update.getCallbackQuery().getData();
        Gift gift = wishService.getInfoGiftById(Integer.parseInt(incomingMessage
                .substring((incomingMessage.indexOf(" "))+1)));
        changeStatusGift.changeStatusGiftOwn(gift);
        wishService.updateStatusGift(gift);
        return SendMessageUtils.sendEditMessage(update, "Обновленный список пожеланий",
                MakerInlineKeyboardMarkupUtils.get4RowsInlineKeyboardMarkup(wishService.getInfoGifts(update
                        .getCallbackQuery().getMessage().getChatId())));
    }
}
