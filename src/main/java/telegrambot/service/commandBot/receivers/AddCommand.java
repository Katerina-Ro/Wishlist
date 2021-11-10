package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
@Service
@Getter
public class AddCommand implements Command{
    private final String MESSAGE_ADD = "Заполните поля";

    @Override
    public EditMessageText execute(long chat_id, long message_id) throws TelegramApiException {
        EditMessageText new_message = new EditMessageText()
                .setChatId(chat_id)
                .setMessageId((int) message_id)
                .setText("");

        return new_message;
    }
}
