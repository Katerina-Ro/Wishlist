package telegrambot.service.commandBot.receivers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;

/**
 * Класс-Receiver команды {@link COMMANDS.DELETE} {@link CommandEditSendMessage}
 */
@Service
public class DeleteWishCommand implements CommandEditSendMessage {
    private final WishService wishService;

    @Autowired
    public DeleteWishCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        String messageDeleteWishCommand;
        int idGift = FindingDataUtil.findIdByIncomingMessage(update.getCallbackQuery().getData());
        if (wishService.checkingGiftGivingStatus(idGift)){
            messageDeleteWishCommand = "Кто-то собирается выполнить это пожелание. Вы уверены, " +
                    "что хотите его удалить?";
        } else {
            messageDeleteWishCommand = "Вы уверены, что хотите удалить это пожелание?";
        }
        return SendMessageUtils.sendEditMessage(update, messageDeleteWishCommand,
                MakerInlineKeyboardMarkupUtils.getYesNoDeleteInlineKeyboardMarkup(idGift));
    }
}


