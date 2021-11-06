package telegrambot.service.commandBot.invoker;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;

/**
 * Класс-Invocer
 */
@Component
public class TelegramUser {

    Command start;
    Command info;
    Command addGift;
    Command chooseWish;
    Command changeWish;

    void startBot(Update update){
        start.execute( update);
    }

    void infoBot(Update update){
        info.execute(update);
    }

    void addGift(Update update){
        addGift.execute(update);
    }

    void chooseWish (Update update){
        chooseWish.execute(update);
    }

    void changeWish (Update update){
        changeWish.execute(update);
    }
}
