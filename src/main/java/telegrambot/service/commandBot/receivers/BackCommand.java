package telegrambot.service.commandBot.receivers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;

@Service
public class BackCommand implements Command {

    @Override
    public EditMessageText execute(long chat_id, long message_id) {
        return null;
    }
}
