package telegrambot.service.commandBot.receivers.changewishlist;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;

/**
 * Класс-Receiver команды "/changeWish" {@link Command}
 */
@Service
@Getter
public class ChangeWishCommand implements Command {
    private final String MESSAGE_CHANGE_WISH = "Вы хотите поменять свое пожелание или выбрать/ отказаться " +
            "от подарка другому человеку?";


    @Override
    public SendMessage execute(Update update)  {
        return null;
    }
}
