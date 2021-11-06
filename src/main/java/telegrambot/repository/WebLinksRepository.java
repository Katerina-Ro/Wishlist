package telegrambot.repository;

import org.springframework.stereotype.Repository;
import telegrambot.modelEntities.WebLinks;

import java.util.List;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.modelEntities.WebLinks}
 */
@Repository
public interface WebLinksRepository {

    /**
     * Получаем конкретную веб-ссылку
     * @idLinks номер ссылки, которую нужно получить
     * @return веб-ссылку
     */
    String getWeblink(int idLinks);

    /**
     * Получаем список всех веб-ссылок, привязанных к конкретному пожеланию
     * @nameGift наименование подарка, к которому нужно получить список веб-ссылок
     * @nameGiftOwner имя владельца подарка
     *@return список веб-ссылок
     */
    List<WebLinks> getListWeblinks(String nameGift, String nameGiftOwner);
}
