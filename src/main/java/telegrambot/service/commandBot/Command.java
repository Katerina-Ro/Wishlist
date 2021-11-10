package telegrambot.service.commandBot;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Command interface для обработки команд телеграм-бота
 */
public interface Command {


    EditMessageText execute(long chat_id, long message_id) throws TelegramApiException;
}
