package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telegrambot.entities.GiftOwner;

/**
 * {@link Repository} для работы с сущностью {@link GiftOwner}
 */
@Repository
public interface TelegramUserRepository extends JpaRepository<GiftOwner, Long> {
}
