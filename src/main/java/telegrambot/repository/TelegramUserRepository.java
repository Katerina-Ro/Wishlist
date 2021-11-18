package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import telegrambot.entities.GiftOwner;

import java.util.List;

/**
 * {@link Repository} для работы с сущностью {@link GiftOwner}
 */
@Repository
public interface TelegramUserRepository extends JpaRepository<GiftOwner, Long> {
    @Query("SELECT g.name FROM GiftOwner g")
    List<String> findAllName();

    @Query("SELECT g FROM GiftOwner g WHERE g.name = :name")
    GiftOwner getGiftOwner(@Param("name")String name);
}
