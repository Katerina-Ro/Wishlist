package telegrambot.service.commandBot.receivers.changewishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.entities.StatusGift.ChangeStatusGiftImpl;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;

/**
 * Класс-Receiver команд {@link COMMANDS.STATE_DB} , {@link COMMANDS.CHANGE_STATUS_OWN_WISH}
 * и {@link COMMANDS.STATE_DB_NOT_ACTIVE} {@link CommandEditSendMessage}
 */
@Service
public class ChangeStatusGiftOwnCommand implements CommandEditSendMessage {
    private final WishService wishService;
    private final ChangeStatusGiftImpl changeStatusGift;

    @Autowired
    public ChangeStatusGiftOwnCommand(WishService wishService, ChangeStatusGiftImpl changeStatusGift1) {
        this.wishService = wishService;
        this.changeStatusGift = changeStatusGift1;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        Gift gift = wishService.getInfoGiftById(FindingDataUtil.findIdByIncomingMessage(update
                .getCallbackQuery().getData()));
        changeStatusGift.changeStatusGiftOwn(gift);
        wishService.updateStatusGift(gift);
        return SendMessageUtils.sendEditMessage(update, "Обновленный список пожеланий",
                MakerInlineKeyboardMarkupUtils.get4RowsInlineKeyboardMarkup(wishService.getInfoGifts(update
                        .getCallbackQuery().getMessage().getChatId())));
    }
}
