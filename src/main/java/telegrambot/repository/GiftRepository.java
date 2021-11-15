package telegrambot.repository;

import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 *  для работы с сущностью {@link telegrambot.entities.Gift}
 */
public interface GiftRepository extends JpaRepository <Gift, Integer> {

    //@Query(name = "findAllByChatId")
    // @Param ("ChatIdUser")
    @Query("SELECT g From Gift g WHERE g.giftOwner.chatId = :chatId")
    Map<Integer, Gift> findAllWishesChatIdUser(@Param("chatId") Long chatIdUser);

    default Gift saveNameGift(String nameGift, GiftOwner giftOwner) {
        Gift gift = new Gift();
        gift.setNameGift(nameGift);
        gift.setGiftOwner(giftOwner);
        return save(gift);
    }
/*
    default void saveDescriptionGift(String descriptionGift, GiftOwner giftOwner) {
        gift.setNameGift(nameGift);
        gift.setGiftOwner(giftOwner);
        save(gift);
    } */

    /*
    @Query("UPDATE Gift g SET g.descriptionGift = :descriptionGift WHERE g.nameGift = :nameGift")
    void saveGiftDescription(@Param("descriptionGift") String giftDescription,
                                     @Param("nameGift") String nameGift); */
}
