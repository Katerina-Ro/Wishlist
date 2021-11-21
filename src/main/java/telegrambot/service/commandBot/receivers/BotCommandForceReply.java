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
import telegrambot.service.commandBot.receivers.getwishlist.ChooseWishCommand;
import telegrambot.service.commandBot.receivers.getwishlist.SearchNameInDBCommand;

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
    private final Command searchNameInDB;

    @Autowired
    public BotCommandForceReply(@Qualifier("insertNameUserToDBCommand") Command insertNameUserToDB,
                                @Qualifier("insertNameGiftToDBCommand") Command insertNameGiftToDB,
                                @Qualifier("insertProductDescriptionToDBCommand") Command insertProductDescriptionToDB,
                                @Qualifier("insertWebLinkCommand") Command insertWebLink,
                                @Qualifier("searchNameInDBCommand") Command searchNameInDB){
        this.insertNameUserToDB = insertNameUserToDB;
        this.insertNameGiftToDB = insertNameGiftToDB;
        this.insertProductDescriptionToDB = insertProductDescriptionToDB;
        this.insertWebLink = insertWebLink;
        this.searchNameInDB = searchNameInDB;
        this.commandMapForceReply = ImmutableMap.<String, Command>builder()
                .put(AddCommand.getMESSAGE_ADD(), this.insertNameUserToDB)
                .put(AddCommand.getNAME_WISH(), this.insertNameGiftToDB)
                .put(InsertNameUserToDBCommand.getPREV_NAME_WISH(), this.insertNameGiftToDB)
                .put(InsertNameUserToDBCommand.getINPUT_ERROR_MESSAGE(), this.insertNameUserToDB)
                .put(InsertNameUserToDBCommand.getINPUT_NAME_ERROR_MESSAGE(), this.insertNameGiftToDB)
                .put(InsertNameGiftToDBCommand.getINPUT_ERROR_MESSAGE(), this.insertNameUserToDB)
                .put(InsertNameGiftToDBCommand.getPREV_PRODUCT_DESCRIPTION(), this.insertProductDescriptionToDB)
                .put(InsertProductDescriptionToDBCommand.getPREV_WEB_LINK(), this.insertWebLink)
                .put(InsertProductDescriptionToDBCommand.getINPUT_ERROR_MESSAGE(), this.insertProductDescriptionToDB)
                .put(ChooseWishCommand.getMESSAGE_CHOOSE_WISH_COMMAND(),  this.searchNameInDB)
                .put(COMMANDS.CHANGE_WISH.getCommand(), this.insertNameUserToDB)
                .put(SearchNameInDBCommand.getNOT_EXIST_ERROR_MESSAGE(), this.searchNameInDB)
                .put(SearchNameInDBCommand.getNOT_EXIST_WISH_ERROR_MESSAGE(), this.searchNameInDB)
                .put(SearchNameInDBCommand.getINCORRECT_NAME_ENTERED_ERROR_MESSAGE(), this.searchNameInDB)
                .build();
    }
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapForceReply.get(commandIdentifier).execute(update));
    }
}
