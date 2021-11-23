package telegrambot.service.commandBot.receivers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.Buttons;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.entityservice.WishService;

/**
 * Класс-Receiver команды {@link COMMANDS.YES} {@link CommandEditSendMessage}
 */
@Service
public class YesDeleteWishFromDBCommand implements CommandEditSendMessage {
    private final WishService wishService;
    private static final String MESSAGE_YES_DELETE_WISH_COMMAND = "Ваше пожелание удалено";

    @Autowired
    public YesDeleteWishFromDBCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        wishService.deleteWishById(FindingDataUtil.findIdByIncomingMessage(update.getCallbackQuery()
                .getData()));
        return SendMessageUtils.sendEditMessage(update, MESSAGE_YES_DELETE_WISH_COMMAND,
                MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup
                        (Buttons.getKeyBoardButtonForYoureself(),Buttons.getKeyBoardBackToStart()));
    }
}