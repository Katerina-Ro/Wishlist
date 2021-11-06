package telegrambot.repository;

import org.springframework.stereotype.Repository;
import telegrambot.modelEntities.TelegramUser;

import java.sql.SQLException;
import java.util.List;

/**
 * {@link Repository} для работы с сущностью {@link TelegramUser}
 */
@Repository
public interface TelegramUserRepository {
    /**
     * Получаем список активных пользователей
     * @return список
     */
    List<TelegramUser> findAllByActiveTrue();

    /**
     * Получаем список удаленных пользователей
     * @return список
     */
    List<TelegramUser> findAllByActiveFalse();

    /**
     * Создаем и добавляем участника
     * @param name - имя участника
     * @param chat_id - номер телефона
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void createPerson(String name, long chat_id) throws SQLException;



    /**
     * Удаляем номер телефона delete или update???????
     * @param chat_id - номер телефона, который нужно удалить
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void deleteGiftOwner (long chat_id) throws SQLException;
}
