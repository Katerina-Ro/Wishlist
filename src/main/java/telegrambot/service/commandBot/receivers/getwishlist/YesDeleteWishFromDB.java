
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
public class YesDeleteWishFromDB implements CommandEditSendMessage {
    private final WishService wishService;

    private static final String MESSAGE_YES_DELETE_WISH_COMMAND = "Ваше пожелание удалено";

    @Autowired
    public YesDeleteWishFromDB(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        System.out.println("внутри да, удалить");

        String incomingMessage = update.getCallbackQuery().getData();
        int idGift = Integer.parseInt(incomingMessage.substring((incomingMessage.indexOf(" "))+1));

        System.out.println("idGift " + idGift);


        wishService.deleteWishById(idGift);
        return SendMessageUtils.sendEditMessage(update, MESSAGE_YES_DELETE_WISH_COMMAND,
                MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup
                        (Buttons.getKeyBoardButtonForYoureself(),Buttons.getKeyBoardBackToStart()));
    }
}