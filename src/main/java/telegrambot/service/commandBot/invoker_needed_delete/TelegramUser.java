package telegrambot.service.commandBot.invoker_needed_delete;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;

/**
 * Класс-Invocer
 */
@Service
public class TelegramUser {

    Command start;
    Command info;
    Command addGift;
    Command chooseWish;
    Command changeWish;

    void startBot(Update update) throws TelegramApiException {
        start.execute( update);
    }

    void infoBot(Update update) throws TelegramApiException {
        info.execute(update);
    }

    void addGift(Update update) throws TelegramApiException {
        addGift.execute(update);
    }

    void chooseWish (Update update) throws TelegramApiException {
        chooseWish.execute(update);
    }

    void changeWish (Update update) throws TelegramApiException {
        changeWish.execute(update);
    }
}
