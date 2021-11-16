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

    @Autowired
    public BotCommandCallBackQueryEdit(@Qualifier("infoCommand") CommandEditSendMessage infoCommand,
                                       @Qualifier("getListWishCommand") CommandEditSendMessage getWishList,
                                       @Qualifier("ownWishListCommand") CommandEditSendMessage ownWishList,
                                       @Qualifier("moreDetailsCommand") CommandEditSendMessage moreDetailsCommand) {
        this.infoCommand = infoCommand;
        this.getWishList = getWishList;
        this.ownWishList = ownWishList;
        this.moreDetailsCommand = moreDetailsCommand;

        this.commandMapCommandEdit = ImmutableMap.<String, CommandEditSendMessage>builder()
                .put(COMMANDS.INFO.getCommand(), this.infoCommand)
                .put(COMMANDS.WISHLIST.getCommand(), this.getWishList)
                .put(COMMANDS.FOR_YOURESELF.getCommand(), this.ownWishList)
                //.put(serchUtils.getCommands(name), this.moreDetailsCommand)
                .build();
    }
    public EditMessageText findCommand(String commandIdentifier, Update update) {
        return (commandMapCommandEdit.getOrDefault(commandIdentifier, moreDetailsCommand).execute(update));
    }



}
