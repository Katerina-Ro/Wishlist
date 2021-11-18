package telegrambot.service.entityservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.Gift;
import telegrambot.entities.WebLinks;
import telegrambot.repository.WebLinksRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebLinkService {
    private final WebLinksRepository webLinksRepository;

    @Autowired
    public WebLinkService(WebLinksRepository webLinksRepository) {
        this.webLinksRepository = webLinksRepository;
    }

    public void saveWebLink (String link, Gift gift){
        System.out.println("вошел в метод saveWebLink (String link, Gift gift)");
        WebLinks webLink = new WebLinks();
        webLink.setWebLink(link);
        /*
        List<WebLinks> listLinks = new ArrayList<>();
        listLinks.add(webLink);
        gift.setLinksList(listLinks); */
        System.out.println("webLink = " + webLink);

        //webLinksRepository.save(webLink);
        System.out.println("типа сохранил ссылку " + webLinksRepository.save(webLink));
    }
}
