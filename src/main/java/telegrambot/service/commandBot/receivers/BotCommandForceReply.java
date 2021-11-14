package telegrambot.service.commandBot.receivers;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;

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

    @Autowired
    public BotCommandForceReply(@Qualifier("insertNameUserToDB") Command insertNameUserToDB,
                                @Qualifier("insertNameGiftToDB") Command insertNameGiftToDB,
                                @Qualifier("insertProductDescriptionToDB")Command insertProductDescriptionToDB) {
        this.insertNameUserToDB = insertNameUserToDB;
        this.insertNameGiftToDB = insertNameGiftToDB;
        this.insertProductDescriptionToDB = insertProductDescriptionToDB;

        this.commandMapForceReply = ImmutableMap.<String, Command>builder()
                .put(AddCommand.getMESSAGE_ADD(), this.insertNameUserToDB)
                .put(InsertNameUserToDB.getNAME_WISH(), this.insertNameGiftToDB)
                .put(InsertNameUserToDB.getINPUT_ERROR_MESSAGE(), this.insertNameUserToDB)
                .put(InsertNameGiftToDB.getINPUT_ERROR_MESSAGE(), this.insertNameGiftToDB)
                .put(InsertNameGiftToDB.getPRODUCT_DESCRIPTION(), this.insertProductDescriptionToDB)

                .build();

    }
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapForceReply.get(commandIdentifier).execute(update));
    }
}
