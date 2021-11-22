package telegrambot.service.commandBot.receivers.getwishlist;

import lombok.Getter;
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
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;
import telegrambot.service.entityservice.WishService;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-Receiver команды {@link COMMANDS.FOR_ANOTHER} {@link CommandEditSendMessage}
 */
@Service
@Getter
public class AnotherWishListCommand implements CommandEditSendMessage {
    private static final String IMAGE_CONFETTI_BALL = String.valueOf(Character.toChars(0x1F38A));
    private static final String IMAGE_CONFUSED_FACE = String.valueOf(Character.toChars(0x1F615));
    private static final String MESSAGE_ANOTHER_WISHLIST = "Выбранные Вами пожелания " + IMAGE_CONFETTI_BALL;
    private static final String MESSAGE_ANOTHER_WISHLIST_IS_EMPTY = "Вы еще не выбрали ни одного пожелания " +
            "для исполнения " + IMAGE_CONFUSED_FACE;
    private final WishService wishService;
    private List<Gift> listGifts = new ArrayList<>();

    @Autowired
    public AnotherWishListCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        listGifts = wishService.getListWishAnother(update.getCallbackQuery().getMessage().getChatId());
        if(listGifts.isEmpty()) {
            return SendMessageUtils.sendEditMessage(update,
                    MESSAGE_ANOTHER_WISHLIST_IS_EMPTY, MakerInlineKeyboardMarkup
                    .get1InlineKeyboardMarkup(Buttons.getKeyBoardChooseWish()));
        }  else{
            return SendMessageUtils.sendEditMessage(update,MESSAGE_ANOTHER_WISHLIST,
                    MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(listGifts));
        }
    }
}
