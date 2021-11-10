package telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telegrambot.entities.Comments;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.entities.Comments}
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer > {
}
