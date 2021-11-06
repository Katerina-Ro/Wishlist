package telegrambot.repository;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.modelEntities.Comments}
 */
@Repository
public interface CommentsRepository {

    /**
     * Вносим комментарий к подарку
     * @param chat_id - номер chat_id комментатора
     * @param comment - комментарий
     * @param idGift - номер подарка, который комментируем
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void createComment (long chat_id, String comment, int idGift) throws SQLException;

    /**
     * Изменяем (обновляем) комментарий к подарку
     * @param chat_id - номер chat_id комментатора
     * @param comment - комментарий
     * @param idGift - номер подарка, который комментируем
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void upDateComment (long chat_id, String comment, int idGift) throws SQLException;

    /**
     * Владелец подарка может удалить свой комментарий к нему
     * @param chat_id - номер chat_id владельца подарка
     * @param idGift - номер подарка
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void deleteComment (long chat_id, int idGift) throws SQLException;
}
