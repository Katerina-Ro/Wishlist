package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WishService;

import java.util.List;

/**
 * Класс-Receiver команды {@link COMMANDS.NAME_GIFT_OWNER} {@link CommandEditSendMessage}
 */
@Service
public class SelectionWishesCommand implements CommandEditSendMessage {
    private final WishService wishService;
    private final TelegramUserService telegramUserService;

    @Autowired
    public SelectionWishesCommand(WishService wishService, TelegramUserService telegramUserService) {
        this.wishService = wishService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        String messageSelectionWishes = "Пожелания выбранного пользователя ";
        // получаем id из пришедшего от бота сообщения
        int id = FindingDataUtil.findIdByIncomingMessage(update.getCallbackQuery().getData());
        // получаем список подарков по id
        List<Gift> list = wishService.getInfoAnotherGiftsInlIdPresenter(telegramUserService
                .getGiftOwner(id).getChatId(), update.getCallbackQuery().getMessage().getChatId());
        return SendMessageUtils.sendEditMessage(update, messageSelectionWishes,
                MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(list));
    }
}
