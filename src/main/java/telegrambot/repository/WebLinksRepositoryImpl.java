package telegrambot.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import telegrambot.modelEntities.WebLinks;
import telegrambot.repository.util.TelegramRepositoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class WebLinksRepositoryImpl implements WebLinksRepository {
    @Override
    public <S extends WebLinks> S save(S s) {
        return null;
    }

    @Override
    public <S extends WebLinks> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<WebLinks> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<WebLinks> findAll() {
        return null;
    }

    @Override
    public Iterable<WebLinks> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(WebLinks webLinks) {

    }

    @Override
    public void deleteAll(Iterable<? extends WebLinks> iterable) {

    }

    @Override
    public void deleteAll() {

    }


    /*


    @Override
    public String getWeblink(int idLinks) {
        String webLink = null;
        String sqlSelectwebLink = "SELECT Weblink FROM links WHERE idLinks = " + idLinks;
        ScrollableResults resultSelectwebLink = session.createSQLQuery(sqlSelectwebLink).scroll();
        while (resultSelectwebLink.next()) {
            webLink = resultSelectwebLink.getString(2);
        }
        return webLink;
    }

    @Override
    public List<WebLinks> getListWeblinks(String nameGift, String nameGiftOwner) {
        List<WebLinks> listWebLinks = new ArrayList<>();
        String sqlSelectlistWebLinks = "SELECT Weblink FROM links WHERE idGift = " +
                            telegramRepositoryUtil.getIdGift(nameGift, telegramRepositoryUtil.getChat_id(nameGiftOwner));
        ScrollableResults resultListWeblinks = session.createSQLQuery(sqlSelectlistWebLinks).scroll();
        while (resultListWeblinks.next()) {
            WebLinks weblink = new WebLinks();
            weblink.setUri_link(resultListWeblinks.getString(2));

            listWebLinks.add(weblink);
        }
        return listWebLinks;
    } */
}
