package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;
import telegrambot.entities.StatusGift.ChangeStatusGiftImpl;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WishService;

@Service
public class ChangeStatusGiftAnotherCommand implements CommandEditSendMessage {
    private final WishService wishService;
    private final ChangeStatusGiftImpl changeStatusGift;
    private final TelegramUserService telegramUserService;

    @Autowired
    public ChangeStatusGiftAnotherCommand(WishService wishService, ChangeStatusGiftImpl changeStatusGift1, TelegramUserService telegramUserService) {
        this.wishService = wishService;
        this.changeStatusGift = changeStatusGift1;
        this.telegramUserService = telegramUserService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        long idPresenter = update.getCallbackQuery().getMessage().getChatId();
        System.out.println("idPresenter = " + idPresenter);
        System.out.println("");
        GiftOwner giftOwner = telegramUserService.getGiftOwner(idPresenter);
        System.out.println("presenter = " + giftOwner);
        System.out.println("");
        Gift gift = wishService.getInfoGiftById(FindingDataUtil.findIdByIncomingMessage(update.getCallbackQuery().getData()));
        System.out.println("gift " + gift);
        System.out.println("сейчас начнется changeStatusGift.changeStatusGiftAnother(gift)");
        System.out.println("статус до смены "+ gift.getStatusGiftAnother());
        changeStatusGift.changeStatusGiftAnother(gift, giftOwner);
        System.out.println("статус после смены " + gift.getStatusGiftAnother());
        System.out.println("");
        System.out.println("сейчас подргуизт статус из бд");
        wishService.updateStatusGift(gift);
        System.out.println("статус из бд "+ wishService.getInfoGiftById(gift.getGiftId()));
        System.out.println("");
        return SendMessageUtils.sendEditMessage(update, "Обновленный список 'Дарю другим'",
                MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(wishService
                        .getListWishAnother(update.getCallbackQuery().getMessage().getChatId())));
    }
}
