package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import telegrambot.entities.WebLinks;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.entities.WebLinks}
 */
@Repository
public interface WebLinksRepository extends JpaRepository<WebLinks, Integer> {
    @Query(value = "INSERT into wishlist.links (weblink, number_id) VALUES (:weblink, :number_id)", nativeQuery = true)
    void insertLink(@Param("weblink") String webLink, @Param("number_id")int idGift);
}