package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;

/**
 * Класс, описывающий возможности бота
 */
@Service
@Getter
@Setter
public class BotCommandReceiver {

    private Command command;

    @Autowired
    public BotCommandReceiver(Command action) {
        this.command = action;
    }

    public void click(Update update) throws TelegramApiException {
        command.execute(update);
    }
}

