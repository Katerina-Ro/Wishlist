package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import telegrambot.entities.Gift;
import telegrambot.entities.WebLinks;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.entities.WebLinks}
 */
@Repository
public interface WebLinksRepository extends JpaRepository<WebLinks, Integer> {

    @Query("SELECT w FROM WebLinks w WHERE w.gift = :gift")
    WebLinks findByGift(@Param("gift")Gift gift);
}