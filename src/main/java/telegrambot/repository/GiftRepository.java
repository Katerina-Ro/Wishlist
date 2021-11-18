package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telegrambot.entities.Gift;
import telegrambot.entities.StatusGift.STATUS_GIFT;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *  для работы с сущностью {@link telegrambot.entities.Gift}
 */
public interface GiftRepository extends JpaRepository <Gift, Integer> {

    @Query("SELECT g From Gift g WHERE g.giftOwner.chatId = :giftOwner")
    List<Gift> findAllByGiftOwnerChatId(@Param("giftOwner")Long chatIdUser);

    @Query("SELECT g From Gift g WHERE g.giftPresenter.chatId = :giftPresenter")
    List<Gift> findAllByGiftAnotherChatId(@Param("giftPresenter")Long chatIdUser);

    @Query("SELECT g From Gift g WHERE g.giftId = :giftId")
    Gift findByGiftId(@Param("giftId")int idGift);

    @Query("SELECT g From Gift g WHERE g.giftOwner.chatId = :giftOwner AND g.statusGiftOwn = 'ACTIVE' " +
            "AND g.statusGiftAnother = 'NOT_ACTIVE'")
    List<Gift> findAllByChatIdByStatusGift(@Param("giftOwner")Long chatIdUser);
}
