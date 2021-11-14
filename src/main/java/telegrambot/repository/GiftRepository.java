package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;

import java.util.List;

/**
 *  для работы с сущностью {@link telegrambot.entities.Gift}
 */
public interface GiftRepository extends JpaRepository <Gift, Integer> {

    //@Query(name = "findAllByChatId")
    // @Param ("ChatIdUser")
    @Query("SELECT g From Gift g WHERE g.giftOwner.chatId = :chatId")
    List<Gift> findAllWishesChatIdUser(@Param("chatId") Long chatIdUser);

    default void saveNameGift(String nameGift, GiftOwner giftOwner) {
        Gift gift = new Gift();
        gift.setNameGift(nameGift);
        gift.setGiftOwner(giftOwner);
        save(gift);
    }

    /*
    @Query("UPDATE Gift g SET g.descriptionGift = :descriptionGift WHERE g.nameGift = :nameGift")
    void saveGiftDescription(@Param("descriptionGift") String giftDescription,
                                     @Param("nameGift") String nameGift); */
}
