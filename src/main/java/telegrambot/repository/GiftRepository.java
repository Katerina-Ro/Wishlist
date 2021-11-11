package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telegrambot.entities.Gift;

import java.util.List;

/**
 *  для работы с сущностью {@link telegrambot.entities.Gift}
 */

public interface GiftRepository extends JpaRepository <Gift, Integer> {

    @Query(name = "findAllByChatId")
    List<Gift> findAllByChatId(Integer chat_id);
}
