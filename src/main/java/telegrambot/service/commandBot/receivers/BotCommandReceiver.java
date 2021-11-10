package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

/**
 * Класс - стартовое меню
 */
/*
@Service
@Getter
public class BotCommandReceiver {

    private final Command infoCommand;
    private final Command startCommand;
    private final Command addCommand;
    private final Command changeWishCommand;
    private final Command getWishList;

    @Autowired
    public BotCommandReceiver(@Qualifier ("infoCommand") Command infoCommand, @Qualifier("startCommand")
            Command startCommand, @Qualifier("addCommand")Command addCommand, @Qualifier("changeWishCommand")
                              Command changeWishCommand, @Qualifier("getListWishCommand")Command getWishList) {
        this.infoCommand = infoCommand;
        this.startCommand = startCommand;
        this.addCommand = addCommand;
        this.changeWishCommand = changeWishCommand;
        this.getWishList = getWishList;
    }

    public SendMessage getCommandResponse (Update update, long chat_id, long message_id, String answer) throws TelegramApiException {
        if (update.getMessage().getText().equals(COMMANDS.INFO.getCommand())) {
            return infoCommand.execute(update);
        }
        if (update.getMessage().getText().equals(COMMANDS.START.getCommand())) {
            return startCommand.execute(update);
        }
        if (update.getMessage().getText().equalsIgnoreCase(COMMANDS.ADD_WISH.getCommand())) {
            return addCommand.execute(update);
        }
        if (update.getMessage().getText().equalsIgnoreCase(COMMANDS.CHANGE_WISH.getCommand())) {
            return changeWishCommand.execute(update);
        }
        return getWishList.execute(update);
    }
}
*/
