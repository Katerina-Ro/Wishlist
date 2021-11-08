package telegrambot.repository;

import org.springframework.stereotype.Repository;
import telegrambot.modelEntities.Gift;
import telegrambot.modelEntities.StatusGift.STATUS_GIFT;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.Collection;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.modelEntities.Gift}
 */
@Repository
public interface GiftRepository {

    /**
     * Участник может внести свое пожелание по подарку
     * @param name - имя владельца подарка
     * @param nameGift - наименование подарка (обязательное поле)
     * @param chat_id - номер chat_id участника - владельца подарка (обязательное поле)
     * @param webLink - ссылка на сайт
     * @param comment - комментарий владельца подарка
     * @param giftStatus - параметр привязан к номеру телефона, если его выбрать, то информацию о подарке сможет увидеть только владелец подарка и админ
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void createGift (String name, String nameGift, long chat_id, @Nullable String webLink, @Nullable String comment, boolean giftStatus) throws SQLException;

    /**
     * Вносим изменения в информацию о подарке
     * @param chat_id - номер chat_id владельца подарка (обязательное поле)
     * @param nameGift - наименование подарка (обязательное поле)
     * @param comment - комментарий
     * @param webLink - ссылка на сайт
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void updateGift (long chat_id, String nameGift, String comment, String webLink) throws SQLException;

    /**
     * Меняет статус подарка
     * @param chat_idPresenter - номер chat_id участника, меняющего статус
     * @param idGift - номер подарка, у которого меняют статус
     * @param chat_idGigtOwner -
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    String updateStatusAnotherGift (long chat_idPresenter, int idGift, long chat_idGigtOwner) throws SQLException;

    /**
     * Получаем информацию о подарках, привязанных к номеру телефона участника
     * @param chat_id - номер chat_id участника
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    Collection<Gift> getInfoGift (long chat_id) throws SQLException;

    /**
     * Владелец подарка может удалить свой подарок из базы
     * @param chat_id - номер chat_id владельца подарка
     * @param nameGift - наименование подарка
     * @throws SQLException - если нет соединения с БД, то будет выброшено исключение
     */
    void deleteGift (long chat_id, String nameGift) throws SQLException;
}
