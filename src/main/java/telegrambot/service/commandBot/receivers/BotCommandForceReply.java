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

    @Autowired
    public BotCommandForceReply(@Qualifier("insertNameUserToDB") Command insertNameUserToDB) {
        this.insertNameUserToDB = insertNameUserToDB;

        this.commandMapForceReply = ImmutableMap.<String, Command>builder()
                .put(AddCommand.getMESSAGE_ADD(), insertNameUserToDB)
                .put(InsertNameUserToDB.getINPUT_ERROR_MESSAGE(), insertNameUserToDB)
                .build();
    }
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapForceReply.get(commandIdentifier).execute(update));
    }
}
