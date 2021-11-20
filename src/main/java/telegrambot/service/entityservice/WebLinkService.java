package telegrambot.service.entityservice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.Gift;
import telegrambot.entities.WebLinks;
import telegrambot.repository.WebLinksRepository;

@Service
public class WebLinkService {
    @Getter
    private final WebLinksRepository webLinksRepository;

    @Autowired
    public WebLinkService(WebLinksRepository webLinksRepository) {
        this.webLinksRepository = webLinksRepository;
    }

    public WebLinks saveWebLink (String link, Gift gift){
        WebLinks webLink = new WebLinks();
        webLink.setWebLink(link);
        webLink.setGift(gift);
        webLinksRepository.save(webLink);
        return webLink;
    }
}
