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
@Getter
public class BotCommandSendMessage {
    private final ImmutableMap<String, Command> commandMapSendMessage;
    private final Command startCommand;

    @Autowired
    public BotCommandSendMessage(@Qualifier("startCommand") Command startCommand) {
        this.startCommand = startCommand;
        this.commandMapSendMessage = ImmutableMap.<String, Command>builder()
                .put(COMMANDS.START.getCommand().toString(), startCommand)
                .put(COMMANDS.BUTTON_BACK_TO_START.getCommand().toString(), startCommand)
                .build();
    }

    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapSendMessage.get(commandIdentifier).execute(update));
    }
}
