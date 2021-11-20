package telegrambot.service.commandBot.receivers.changewishlist;

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

/**
 * Класс-Receiver команды "Выбрать" {@link CommandEditSendMessage}
 */
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
        GiftOwner giftOwner = telegramUserService.getGiftOwner(update.getCallbackQuery()
                .getMessage().getChatId());
        Gift gift = wishService.getInfoGiftById(FindingDataUtil.findIdByIncomingMessage(update
                .getCallbackQuery().getData()));
        changeStatusGift.changeStatusGiftAnother(gift, giftOwner);
        wishService.updateStatusGift(gift);
        return SendMessageUtils.sendEditMessage(update, "Обновленный список 'Дарю другим'",
                MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(wishService
                        .getListWishAnother(update.getCallbackQuery().getMessage().getChatId())));
    }
}
