package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.entityservice.WebLinkService;
import telegrambot.service.commandBot.Command;

@Service
public class InsertWebLink implements Command {
    private static final String SPARKLES =  String.valueOf(Character.toChars(0x2728));
    // кнопка сделать неактивным
    // кнопка внести еще пожелание
    // кнопка главное меню

    @Getter
    private static final String STATUS_WISH_GIFT_OWNER = "Ваше пожаление записано " + SPARKLES +
            " Его видят все, у кого активен этот бот. \nВы можете переслать ссылку на этот бот другим, " +
            "чтобы они увидели список Ваших пожеланий либо сделать пожелание неактивным на какое-то время " +
            "(если пожелание неактивно, то его никто, кроме Вас не видит";

    @Getter
    private final WebLinkService webLinkService;
    @Getter
    private final InsertProductDescriptionToDB insertProductDescriptionToDB;

    @Autowired
    public InsertWebLink(WebLinkService webLinkService, InsertProductDescriptionToDB insertProductDescriptionToDB) {
        this.webLinkService = webLinkService;
        this.insertProductDescriptionToDB = insertProductDescriptionToDB;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        SendMessage messageWebLink = new SendMessage();
        String webLink = update.getMessage().getText();
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();

        System.out.println("подарок до внесения ссылки "+insertProductDescriptionToDB.getInsertNameGiftToDB().getGift());

        webLinkService.saveWebLink(webLink, insertProductDescriptionToDB.getInsertNameGiftToDB().getGift());
       // messageWebLink.setChatId(update.getMessage().getChatId())
         //       .setText(WEB_LINK);
        messageWebLink.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageWebLink;
    }
}
