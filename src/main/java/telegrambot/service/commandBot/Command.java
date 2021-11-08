package telegrambot.service.commandBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

/**
 * Command interface для обработки команд телеграм-бота
 */
public interface Command {
    /**
     * https://docs.oracle.com/javase/1.5.0/docs/tooldocs/windows/javadoc.html#%7B@link%7D
     * С помощью @link можно ссылаться на Javadoc другого класса или члена класса (см. документацию Javadoc для @link).
     * При создании Javadoc вставляется ссылка на правую страницу + якорь. Это позволяет создавать перекрестные ссылки
     * на другие части Javadoc.
     *
     * @param update provided {@link Update} object with all the needed data for command.
     */
    SendMessage execute(Update update) throws TelegramApiException, SQLException;
}
