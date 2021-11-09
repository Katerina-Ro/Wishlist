package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

/**
 * Класс - стартовое меню
 */
@Service
@Getter
public class BotCommandReceiver {

    private InfoCommand infoCommand;
    private StartCommand startCommand;
    private AddCommand addCommand;
    private ChangeWishCommand changeWishCommand;
    private GetListWishCommand getWishList;

    @Autowired
    public void setInfoCommand(InfoCommand infoCommand) {
        this.infoCommand = infoCommand;
    }

    @Autowired
    public void setStartCommand(StartCommand startCommand) {
        this.startCommand = startCommand;
    }

    @Autowired
    public void setAddCommand(AddCommand addCommand) {
        this.addCommand = addCommand;
    }

    @Autowired
    public void setChangeWishCommand(ChangeWishCommand changeWishCommand) {
        this.changeWishCommand = changeWishCommand;
    }

    @Autowired
    public void setGetWishList(GetListWishCommand getWishList) {
        this.getWishList = getWishList;
    }

    public SendMessage getCommandResponse (Update update) throws TelegramApiException {
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

