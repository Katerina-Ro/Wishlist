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
import telegrambot.service.commandBot.receivers.utils.keyboard.Buttons;
import telegrambot.service.entityservice.WishService;

import java.util.List;

/**
 * Класс-Receiver команд {@link COMMANDS.FOR_YOURESELF} и {@link COMMANDS.NO} {@link CommandEditSendMessage}
 */
@Service
public class OwnWishListCommand implements CommandEditSendMessage {
    private static final String IMAGE_CONFUSED_FACE = String.valueOf(Character.toChars(0x1F615));
    private static final String MESSAGE_OWN_WISHLIST = "Ваш список пожеланий ";
    private static final String MESSAGE_OWN_WISHLIST_IS_EMPTY = "Ваш список желаний пуст " +
            IMAGE_CONFUSED_FACE + ". Добавьте свои желания через кнопку 'Добавить пожелание'";
    private final WishService wishService;

    @Autowired
    public OwnWishListCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        List<Gift> listGifts = wishService.getInfoGifts(update.getCallbackQuery().getMessage().getChatId());
        if(wishService.getInfoGifts(update.getCallbackQuery().getMessage().getChatId()).isEmpty()) {
            return SendMessageUtils.sendEditMessage(update,
                    MESSAGE_OWN_WISHLIST_IS_EMPTY, Buttons.getKeyBoardStartMenu());
        }  else{
            return SendMessageUtils.sendEditMessage(update, MESSAGE_OWN_WISHLIST,
                    MakerInlineKeyboardMarkupUtils.get4RowsInlineKeyboardMarkup(listGifts));
        }
    }
}

