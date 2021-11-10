package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telegrambot.entities.WebLinks;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.entities.WebLinks}
 */
@Repository
public interface WebLinksRepository extends JpaRepository<WebLinks, Integer> {

}