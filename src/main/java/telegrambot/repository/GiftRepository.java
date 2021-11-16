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
}
