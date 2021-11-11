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

    @Query(value = "INSERT into gift_owner (name, chat_id) VALUES(:Name, :chatId)", nativeQuery = true)
    default void saveNameAndId(String name, int chatId) {
        GiftOwner giftOwner = new GiftOwner();
        giftOwner.setName(name);
        giftOwner.setChatId(chatId);
        save(giftOwner);
    }
}
