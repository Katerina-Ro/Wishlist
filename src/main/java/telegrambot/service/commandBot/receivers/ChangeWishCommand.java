package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

/**
 * Класс-Receiver команды "/changeWish" {@link Command}
 */
@Service
@Getter
public class ChangeWishCommand implements Command {
    private final String MESSAGE_CHANGE_WISH = "Вы хотите поменять свое пожелание или выбрать/ отказаться " +
            "от подарка другому человеку?";


    @Override
    public SendMessage execute(long chat_id) throws TelegramApiException {
        return null;
    }
}
