package telegrambot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import telegrambot.modelEntities.WebLinks;

import java.util.Optional;

/**
 * {@link Repository} для работы с сущностью {@link telegrambot.modelEntities.WebLinks}
 */
@Repository
public interface WebLinksRepository extends CrudRepository<WebLinks, Integer> {

    <S extends WebLinks> S save(S s);

    <S extends WebLinks> Iterable<S> saveAll(Iterable<S> iterable);

    @Query(name = "findLinksWithId")
    Optional<WebLinks> findById(Integer integer);

    boolean existsById(Integer integer);

    @Query(name = "findAllLinks")
    Iterable<WebLinks> findAll();

    Iterable<WebLinks> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer integer);

    void delete(WebLinks webLinks);

    void deleteAll(Iterable<? extends WebLinks> iterable);

    void deleteAll();
}