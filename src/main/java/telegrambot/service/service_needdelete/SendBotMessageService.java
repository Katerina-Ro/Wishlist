package telegrambot.service.service_needdelete;

/**
 * Сервис для отправки сообщений через телеграм-бот
 */
public interface SendBotMessageService {

    /**
     * Отправка сообщений через телеграм бот.
     *
     * @param chatId  provided chatId in which would be sent.
     * @param message provided message to be sent.
     */
    void sendMessage(Long chatId, String message);
}
