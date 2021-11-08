package telegrambot.service.commandBot.utils;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Вспомогательный класс для классов-команд
 */
public class CommandUtils {

    /**
     * Получить chatId из объекта {@link Update}.
     *
     * @param update из {@link Update}
     * @return chatID из объекта {@link Update}.
     */
    public static Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    /**
     * Получить текст сообщения из объекта {@link Update}.
     *
     * @param update из {@link Update}
     * @return текст сообщения из объекта {@link Update}.
     */
    public static String getMessage(Update update) {
        return update.getMessage().getText();
    }
}