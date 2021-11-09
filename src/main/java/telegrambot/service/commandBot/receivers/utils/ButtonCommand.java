package telegrambot.service.commandBot.receivers.utils;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;

import java.sql.SQLException;

public class ButtonCommand{
    private Command command;

    public ButtonCommand(Command action) {
        this.command = action;
    }

    public void startCommand(Update update) throws TelegramApiException, SQLException {
        command.execute(update);
    }
}
