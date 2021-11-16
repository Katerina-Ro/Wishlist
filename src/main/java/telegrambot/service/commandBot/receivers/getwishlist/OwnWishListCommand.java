package telegrambot.service.commandBot.receivers.getwishlist;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;
import telegrambot.service.entityservice.WishService;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class OwnWishListCommand implements CommandEditSendMessage {
    private static final String IMAGE_CONFUSED_FACE = String.valueOf(Character.toChars(0x1F615));
    private static final String MESSAGE_OWN_WISHLIST = "Ваш список пожеланий ";
    private static final String MESSAGE_OWN_WISHLIST_IS_EMPTY = "Ваш список желаний пуст " +
            IMAGE_CONFUSED_FACE + ". Добавьте свои желания через кнопку 'Добавить пожелание'";
    private final WishService wishService;
    private List<Gift> listGifts = new ArrayList<>();

    @Autowired
    public OwnWishListCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        EditMessageText editMessageOwnWishListCommand = new EditMessageText();
        long chatIdUser = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        listGifts = wishService.getInfoGifts(chatIdUser);
        System.out.println("update.getCallbackQuery().getData() в OwnWishListCommand = " + update.getCallbackQuery().getData());
        if(wishService.getInfoGifts(chatIdUser).isEmpty()) {
            editMessageOwnWishListCommand.setMessageId(messageId);
            editMessageOwnWishListCommand.setText(MESSAGE_OWN_WISHLIST_IS_EMPTY);
            editMessageOwnWishListCommand.setChatId(chatIdUser);
            editMessageOwnWishListCommand.enableHtml(true);
            editMessageOwnWishListCommand.setReplyMarkup(Buttons.getKeyBoardStartMenu());
        } else{

            editMessageOwnWishListCommand.setMessageId(messageId);
            editMessageOwnWishListCommand.setText(MESSAGE_OWN_WISHLIST);
            editMessageOwnWishListCommand.setChatId(chatIdUser);
            editMessageOwnWishListCommand.enableHtml(true);
            editMessageOwnWishListCommand.setReplyMarkup(MakerInlineKeyboardMarkup
                    .get2x2InlineKeyboardMarkup(listGifts));
    }
         return editMessageOwnWishListCommand;
    }
}
