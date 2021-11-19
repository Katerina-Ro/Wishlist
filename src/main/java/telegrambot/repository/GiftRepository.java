package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telegrambot.entities.Gift;

import java.util.List;

/**
 *  для работы с сущностью {@link telegrambot.entities.Gift}
 */
public interface GiftRepository extends JpaRepository <Gift, Integer> {

    @Query("SELECT g From Gift g WHERE g.giftOwner.chatId = :giftOwner")
    List<Gift> findAllByGiftOwnerChatId(@Param("giftOwner")Long chatIdUser);

    @Query("SELECT g From Gift g WHERE g.giftOwner.chatId = :giftOwner AND g.statusGiftOwn = 'ACTIVE'" +
            "AND g.statusGiftAnother = 'NOT_ACTIVE'")
    List<Gift> findAllByGiftOwnerChatIdByStatusGift(@Param("giftOwner")Long chatIdUser);

    @Query("SELECT g From Gift g WHERE g.giftPresenter.chatId = :giftPresenter")
    List<Gift> findAllByGiftAnotherChatId(@Param("giftPresenter")Long chatIdUser);

    @Query("SELECT g From Gift g WHERE g.giftId = :giftId")
    Gift findByGiftId(@Param("giftId")int idGift);






    @Query(value = "SELECT * From gift WHERE chat_id = :giftOwner AND gift_status_gift_owner = 'ACTIVE' " +
            "AND gift_status_giving = 'NOT_ACTIVE' AND (SELECT * From gift WHERE gift_status_giving = 'ACTIVE' AND " +
            "gift_presenter_id = :giftPresenter)", nativeQuery = true)
    List<Gift> findAllByChatIdByStatusGift(@Param("giftOwner")long chatIdUser,
                                           @Param("giftPresenter")long chatIdPresenter);
}
