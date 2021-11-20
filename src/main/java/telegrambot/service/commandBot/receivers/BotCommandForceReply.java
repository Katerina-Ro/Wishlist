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
    private final ImmutableMap<StringBuffer, Command> commandMapForceReply;
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
        this.commandMapForceReply = ImmutableMap.<StringBuffer, Command>builder()
                .put(new StringBuffer(AddCommand.getMESSAGE_ADD()), this.insertNameUserToDB)
                .put(new StringBuffer(InsertNameUserToDBCommand.getNameWish()), this.insertNameGiftToDB)
                .put(new StringBuffer(InsertNameUserToDBCommand.getINPUT_ERROR_MESSAGE()), this.insertNameUserToDB)
                .put(new StringBuffer(InsertNameUserToDBCommand.getINPUT_NAME_ERROR_MESSAGE()), this.insertNameGiftToDB)
                .put(new StringBuffer(InsertNameGiftToDBCommand.getINPUT_ERROR_MESSAGE()), this.insertNameUserToDB)
                .put(new StringBuffer(InsertNameGiftToDBCommand.getProductDescription()), this.insertProductDescriptionToDB)
                .put(new StringBuffer(InsertProductDescriptionToDBCommand.getWebLink()), this.insertWebLink)
                .put(new StringBuffer(ChooseWishCommand.getMESSAGE_CHOOSE_WISH_COMMAND()),  this.searchNameInDB)
                .put(COMMANDS.CHANGE_WISH.getCommand(), this.insertNameUserToDB)
                .put(new StringBuffer(SearchNameInDBCommand.getNOT_EXIST_ERROR_MESSAGE()), this.searchNameInDB)
                .put(new StringBuffer(SearchNameInDBCommand.getNOT_EXIST_WISH_ERROR_MESSAGE()), this.searchNameInDB)
                .put(new StringBuffer(SearchNameInDBCommand.getINCORRECT_NAME_ENTERED_ERROR_MESSAGE()), this.searchNameInDB)
                .build();
    }
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapForceReply.get(new StringBuffer(commandIdentifier)).execute(update));
    }
}
