package telegrambot.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import telegrambot.modelEntities.WebLinks;
import telegrambot.repository.util.TelegramRepositoryUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WebLinksRepositoryImpl implements WebLinksRepository{

    @Autowired
    @Setter
    private TelegramRepositoryUtil telegramRepositoryUtil;

    private Session session;

    @Autowired
    public Session setSession (SessionFactory sessionFactory) {
        assert sessionFactory != null;
        return this.session = sessionFactory.getCurrentSession();
    }

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
    }
}
