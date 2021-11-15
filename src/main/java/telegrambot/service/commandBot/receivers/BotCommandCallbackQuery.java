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

@Service
@Getter
public class BotCommandCallbackQuery {
    private final ImmutableMap<String, Command> commandMapCommand;
    private final Command addCommand;
    private final Command changeWishCommand;
    private final Command insertNameUserToDB;//Добавить имя

    @Autowired
    public BotCommandCallbackQuery(@Qualifier("addCommand") Command addCommand,
                                   @Qualifier("changeWishCommand") Command changeWishCommand,
                                   @Qualifier("insertNameUserToDB") Command insertNameUserToDB) {
        this.addCommand = addCommand;
        this.changeWishCommand = changeWishCommand;
        this.insertNameUserToDB = insertNameUserToDB;
        this.commandMapCommand = ImmutableMap.<String, Command>builder()
                .put(COMMANDS.ADD_WISH.getCommand(), this.addCommand)
                .put(COMMANDS.CHANGE_WISH.getCommand(), this.changeWishCommand)
                .build();
    }

    // .put(COMMANDS.START.getCommand(), infoCommand)
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapCommand.get(commandIdentifier).execute(update));
    }
}
