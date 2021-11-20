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
    private final ImmutableMap<StringBuffer, Command> commandMapCommand;
    private final Command addCommand;
    private final Command insertNameUserToDB;
    private final Command chooseWishCommand;

    @Autowired
    public BotCommandCallbackQuery(@Qualifier("addCommand") Command addCommand,
                                   @Qualifier("insertNameUserToDBCommand") Command insertNameUserToDB,
                                   @Qualifier("chooseWishCommand") Command chooseWishCommand) {
        this.addCommand = addCommand;
        this.insertNameUserToDB = insertNameUserToDB;
        this.chooseWishCommand = chooseWishCommand;
        this.commandMapCommand = ImmutableMap.<StringBuffer, Command>builder()
                .put(COMMANDS.ADD_WISH.getCommand(), this.addCommand)
                .put(COMMANDS.CHOOSE_WISH.getCommand(), this.chooseWishCommand)
                .build();
    }
    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMapCommand.get(new StringBuffer(commandIdentifier)).execute(update));
    }
}
