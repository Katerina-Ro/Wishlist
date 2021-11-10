package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telegrambot.entities.Gift;

import java.util.List;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.entities.Gift}
 */
@Repository
public interface GiftRepository extends JpaRepository <Gift, Integer> {

    @Query(name = "findAllByChatId")
    List<Gift> findAllByChatId(Integer chat_id);
}
