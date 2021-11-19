package telegrambot.service.commandBot.receivers;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.addwish.AddCommand;
import telegrambot.service.commandBot.receivers.addwish.InsertNameGiftToDBCommand;
import telegrambot.service.commandBot.receivers.addwish.InsertNameUserToDBCommand;
import telegrambot.service.commandBot.receivers.addwish.InsertProductDescriptionToDBCommand;
import telegrambot.service.commandBot.receivers.changewishlist.ChangeWishCommand;
import telegrambot.service.commandBot.receivers.getwishlist.ChooseWishCommand;

/**
 * Класс для обработки ForceReplyMessage, возвращает ответ типа SendMessage
 */
@Service
@Getter
public class BotCommandForceReply {
    private final ImmutableMap<String, Command> commandMapForceReply;
    private final Command insertNameUserToDB;
    private final Command insertNameGiftToDB;
    private final Command insertProductDescriptionToDB;
    private final Command insertWebLink;
    private final Command chooseWishCommand;
    private final Command searchNameInDB;
    private final Command changeWishCommand;
    private final Command updateNameGiftDBCommand;

    @Autowired
    public BotCommandForceReply(@Qualifier("insertNameUserToDBCommand") Command insertNameUserToDB,
                                @Qualifier("insertNameGiftToDBCommand") Command insertNameGiftToDB,
                                @Qualifier("insertProductDescriptionToDBCommand") Command insertProductDescriptionToDB,
                                @Qualifier("insertWebLinkCommand") Command insertWebLink,
                                @Qualifier("chooseWishCommand") Command chooseWishCommand,
                                @Qualifier("searchNameInDBCommand") Command searchNameInDB,
                                @Qualifier("changeWishCommand") Command changeWishCommand,
                                @Qualifier("updateNameGiftDBCommand")Command updateNameGiftDBCommand) {
        this.insertNameUserToDB = insertNameUserToDB;
        this.insertNameGiftToDB = insertNameGiftToDB;
        this.insertProductDescriptionToDB = insertProductDescriptionToDB;
        this.insertWebLink = insertWebLink;
        this.chooseWishCommand = chooseWishCommand;
        this.searchNameInDB = searchNameInDB;
        this.changeWishCommand = changeWishCommand;
        this.updateNameGiftDBCommand = updateNameGiftDBCommand;
        this.commandMapForceReply = ImmutableMap.<String, Command>builder()
                .put(AddCommand.getMESSAGE_ADD(), this.insertNameUserToDB)
                .put(InsertNameUserToDBCommand.getNAME_WISH(), this.insertNameGiftToDB)
                .put(InsertNameUserToDBCommand.getINPUT_ERROR_MESSAGE(), this.insertNameUserToDB)
                .put(InsertNameGiftToDBCommand.getINPUT_ERROR_MESSAGE(), this.insertNameGiftToDB)
                .put(InsertNameUserToDBCommand.getINPUT_NAME_ERROR_MESSAGE(), this.insertNameGiftToDB)
                .put(InsertNameGiftToDBCommand.getPRODUCT_DESCRIPTION(), this.insertProductDescriptionToDB)
                .put(InsertProductDescriptionToDBCommand.getWEB_LINK(), this.insertWebLink)
                .put(COMMANDS.CHOOSE_WISH.getCommand(), this.chooseWishCommand)
                .put(ChooseWishCommand.getMESSAGE_CHOOSE_WISH_COMMAND(),  this.searchNameInDB)
                .put(COMMANDS.CHANGE_WISH.getCommand(), this.changeWishCommand)
                .put(ChangeWishCommand.getMESSAGE_CHANGE_WISH(), this.updateNameGiftDBCommand)
                .build();

    }
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapForceReply.get(commandIdentifier).execute(update));
    }
}
