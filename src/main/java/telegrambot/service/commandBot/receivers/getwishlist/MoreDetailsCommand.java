package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;
import telegrambot.service.entityservice.WishService;

/**
 * Класс-Receiver команды {@link COMMANDS.MORE_DETAILS} {@link CommandEditSendMessage}
 */
@Service
public class MoreDetailsCommand implements CommandEditSendMessage {
    private final WishService wishService;

    @Autowired
    public MoreDetailsCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        return SendMessageUtils.sendEditMessage(update,
                wishService.getInfoGiftById(FindingDataUtil.findIdByIncomingMessage(update.getCallbackQuery()
                        .getData())).toString(), MakerInlineKeyboardMarkup
                        .get1InlineKeyboardMarkup(Buttons.getKeyBoardButtonBack()));
    }
}
