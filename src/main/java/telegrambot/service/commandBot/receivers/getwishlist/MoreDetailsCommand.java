package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;
import telegrambot.service.entityservice.WishService;

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
        String incomingMessage = update.getCallbackQuery().getData();
        String messageMoreDetailsCommand = wishService.getInfoGiftById(Integer.parseInt(incomingMessage
                .substring((incomingMessage.indexOf(" "))+1))).toString();
        return SendMessageUtils.sendEditMessage(update, messageMoreDetailsCommand,
                MakerInlineKeyboardMarkup.get1InlineKeyboardMarkup(Buttons.getKeyBoardButtonBack()));
    }
}
