package telegrambot.service.commandBot.receivers.addwish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.WebLinks;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WebLinkService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.entityservice.WishService;

/**
 * Класс-Receiver команды InsertProductDescriptionToDBCommand.getWebLink() {@link Command}
 */
@Service
public class InsertWebLinkCommand implements Command {
    private static final String SPARKLES =  String.valueOf(Character.toChars(0x2728));
    private static final String STATUS_WISH_GIFT_OWNER = "Ваше пожаление записано " + SPARKLES +
            " Его видят все, у кого активен этот бот. \nВы можете переслать ссылку на этот бот другим, " +
            "чтобы они увидели список Ваших пожеланий либо сделать пожелание неактивным на какое-то время " +
            "(если пожелание неактивно, то его никто, кроме Вас не видит";
    private final WebLinkService webLinkService;
    private final WishService wishService;

    @Autowired
    public InsertWebLinkCommand(WebLinkService webLinkService, WishService wishService) {
        this.webLinkService = webLinkService;
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        WebLinks link = webLinkService.saveWebLink(update.getMessage().getText(),
                InsertNameGiftToDBCommand.getGift());
        InsertNameGiftToDBCommand.getGift().setLink(link);
        wishService.save(InsertNameGiftToDBCommand.getGift());
        int idGift = InsertNameGiftToDBCommand.getGift().getGiftId();

        // ??
        InsertNameGiftToDBCommand.setGift(null);

        System.out.println("вышел из метода webLinkService.saveWebLink(update.getMessage().getText(");

        return SendMessageUtils.sendMessage(update, STATUS_WISH_GIFT_OWNER, false)
                .setReplyMarkup(MakerInlineKeyboardMarkupUtils.getChangeStatusGiftOwnInlineKeyboardMarkup(idGift));
    }
}
