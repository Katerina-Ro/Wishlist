package telegrambot.service.commandBot.receivers;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.COMMANDS;

/**
 * Класс - стартовое меню для обработки Message, возвращает ответ типа SendMessage
 */
@Service
public class BotCommandSendMessage {
    @Getter
    private final ImmutableMap<String, Command> commandMapSendMessage;
    @Getter
    private final Command startCommand;
    //private final Command insertNameUserToDB;

    // @Qualifier("insertNameUserToDB") Command insertNameUserToDB
    @Autowired
    public BotCommandSendMessage(@Qualifier("startCommand") Command startCommand) {
        this.startCommand = startCommand;

        // https://www-baeldung-com.translate.goog/java-immutable-maps?_x_tr_sl=en&_x_tr_tl=ru&_x_tr_hl=ru&_x_tr_pto=nui,sc
        // Иногда предпочтительнее запретить модификации  java.util.Map,  такие как совместное использование
        // данных только для чтения между потоками. Для этой цели мы можем использовать либо
        // немодифицируемую карту, либо неизменяемую карту.
        this.commandMapSendMessage = ImmutableMap.<String, Command>builder()
                .put(COMMANDS.START.getCommand(), startCommand)
                .build();
    }

    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapSendMessage.getOrDefault(commandIdentifier, startCommand).execute(update));
    }
}
