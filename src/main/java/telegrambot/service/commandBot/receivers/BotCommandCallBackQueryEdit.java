package telegrambot.service.commandBot.receivers;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.COMMANDS;

@Service
@Getter
public class BotCommandCallBackQueryEdit {
    private final ImmutableMap<String, CommandEditSendMessage> commandMapCommandEdit;
    private final CommandEditSendMessage infoCommand;
    private final CommandEditSendMessage getWishList;
    private final CommandEditSendMessage ownWishList;
    private final CommandEditSendMessage moreDetailsCommand;
    private final CommandEditSendMessage deleteWishCommand;
    private final CommandEditSendMessage yesDeleteWishFromDB;
    private final CommandEditSendMessage changeStatusGiftOwn;
    private final CommandEditSendMessage anotherWishListCommand;
    private final CommandEditSendMessage selectionWishes;

    @Autowired
    public BotCommandCallBackQueryEdit(@Qualifier("infoCommand") CommandEditSendMessage infoCommand,
                                       @Qualifier("getListWishCommand") CommandEditSendMessage getWishList,
                                       @Qualifier("ownWishListCommand") CommandEditSendMessage ownWishList,
                                       @Qualifier("moreDetailsCommand") CommandEditSendMessage moreDetailsCommand,
                                       @Qualifier("deleteWishCommand") CommandEditSendMessage deleteWishCommand,
                                       @Qualifier("yesDeleteWishFromDB") CommandEditSendMessage yesDeleteWishFromDB,
                                       @Qualifier("changeStatusGiftOwn") CommandEditSendMessage changeStatusGiftOwn,
                                       @Qualifier("anotherWishListCommand") CommandEditSendMessage anotherWishListCommand,
                                       @Qualifier("selectionWishes") CommandEditSendMessage selectionWishes) {
        this.infoCommand = infoCommand;
        this.getWishList = getWishList;
        this.ownWishList = ownWishList;
        this.moreDetailsCommand = moreDetailsCommand;
        this.deleteWishCommand = deleteWishCommand;
        this.yesDeleteWishFromDB = yesDeleteWishFromDB;
        this.changeStatusGiftOwn = changeStatusGiftOwn;
        this.anotherWishListCommand = anotherWishListCommand;
        this.selectionWishes = selectionWishes;

        this.commandMapCommandEdit = ImmutableMap.<String, CommandEditSendMessage>builder()
                .put(COMMANDS.INFO.getCommand(), this.infoCommand)
                .put(COMMANDS.WISHLIST.getCommand(), this.getWishList)
                .put(COMMANDS.FOR_YOURESELF.getCommand(), this.ownWishList)
                .put(COMMANDS.FOR_ANOTHER.getCommand(), this.anotherWishListCommand)
                .put(COMMANDS.BACK.getCommand(), this.getWishList)
                .put(COMMANDS.DELETE.getCommand(), this.deleteWishCommand)
                .put(COMMANDS.YES.getCommand(), this.yesDeleteWishFromDB)
                .put(COMMANDS.NO.getCommand(), this.ownWishList)
                .put(COMMANDS.STATE_DB.getCommand(), this.changeStatusGiftOwn)
                .put(COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand(), this.changeStatusGiftOwn)
                .put(COMMANDS.STATE_DB_NOT_ACTIVE.getCommand(), this.changeStatusGiftOwn)
                .put(COMMANDS.NAME_GIFT_OWNER.getCommand(), this.selectionWishes)
                .build();
    }
    public EditMessageText findCommand(String commandIdentifier, Update update) {
        return (commandMapCommandEdit.getOrDefault(commandIdentifier, moreDetailsCommand).execute(update));
    }
}
