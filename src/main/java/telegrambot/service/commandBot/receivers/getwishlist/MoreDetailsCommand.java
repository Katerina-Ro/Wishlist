package telegrambot.service.commandBot.receivers.getwishlist;

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

@Service
public class MoreDetailsCommand implements CommandEditSendMessage {
    private final WishService wishService;
    private final OwnWishListCommand ownWishListCommand;
    private String receivedName;
    private String giftDescription;
    private String giftStatusGiving;
    private String giftStatusGiftOwner;
    private String webLink;
    private static Gift gift;

    private static String MESSAGE_MORE_DETAILS_COMMAND;

    @Autowired
    public MoreDetailsCommand(WishService wishService, OwnWishListCommand ownWishListCommand) {
        this.wishService = wishService;
        this.ownWishListCommand = ownWishListCommand;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        System.out.println("внутри меоада подробнее");

        EditMessageText editMessageOwnWishListCommand = new EditMessageText();
        long chatIdUser = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        System.out.println(update.getCallbackQuery());
        System.out.println("update.getCallbackQuery().getData() в MoreDetailsCommand = " + update.getCallbackQuery().getData());
        System.out.println();

        gift = wishService.getGift(update.getCallbackQuery().getData(),ownWishListCommand.getListGifts());
        System.out.println(gift);
        MESSAGE_MORE_DETAILS_COMMAND = "Подробно о пожелании \n" + gift.toString();
        //wishService.getGiftRepository().findById();

        editMessageOwnWishListCommand.setMessageId(messageId);
        editMessageOwnWishListCommand.setText(MESSAGE_MORE_DETAILS_COMMAND);
        editMessageOwnWishListCommand.setChatId(chatIdUser);
        editMessageOwnWishListCommand.enableHtml(true);
        editMessageOwnWishListCommand.setReplyMarkup(MakerInlineKeyboardMarkup.get1InlineKeyboardMarkup(
                Buttons.getKeyBoardButtonBack()));
        return editMessageOwnWishListCommand;
    }
}
