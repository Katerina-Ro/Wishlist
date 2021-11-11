package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

@Service
@Getter
public class ButtonClick {
    private final Command infoCommand;
    private final Command addCommand;
    private final Command changeWishCommand;
    private final Command getWishList;

    @Autowired
    public ButtonClick(@Qualifier("infoCommand") Command infoCommand,
                       @Qualifier("addCommand")Command addCommand,
                       @Qualifier("changeWishCommand")Command changeWishCommand,
                       @Qualifier("getListWishCommand")Command getWishList) {
        this.infoCommand = infoCommand;
        this.addCommand = addCommand;
        this.changeWishCommand = changeWishCommand;
        this.getWishList = getWishList;
    }

    public SendMessage getCommandResponse (String message, long chat_id) throws TelegramApiException {
        if (message.equals(COMMANDS.INFO.getCommand())) {
            return infoCommand.execute(chat_id);
        }
        if (message.equalsIgnoreCase(COMMANDS.ADD_WISH.getCommand())) {
            return addCommand.execute(chat_id);
        }
        if (message.equalsIgnoreCase(COMMANDS.CHANGE_WISH.getCommand())) {
            return changeWishCommand.execute(chat_id);
        }
        return getWishList.execute(chat_id);
    }
}
