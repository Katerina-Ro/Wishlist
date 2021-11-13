package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telegrambot.entities.GiftOwner;

/**
 * {@link Repository} для работы с сущностью {@link GiftOwner}
 */
@Repository
public interface TelegramUserRepository extends JpaRepository<GiftOwner, Long> {

    default void saveIdUser(long chat_id) {
        GiftOwner giftOwner = new GiftOwner();
        giftOwner.setChatId((int) chat_id);
        save(giftOwner);
    }

    @Query(value = "UPDATE gift_owner SET name_user = :name_user WHERE chat_id = :chat_id", nativeQuery = true)
    default void saveNameUser(String name_user, int chat_id) {
        GiftOwner giftOwner = new GiftOwner();
        giftOwner.setName(name_user);
        giftOwner.setChatId(chat_id);
        save(giftOwner);
    }
}
