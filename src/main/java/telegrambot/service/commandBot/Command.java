package telegrambot.service.commandBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Command interface для обработки команд телеграм-бота
 */
public interface Command {
    SendMessage execute(long chat_id) throws TelegramApiException;
}
