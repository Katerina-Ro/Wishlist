package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.entities.WebLinks;
import telegrambot.service.entityservice.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

import java.util.List;

@Service
public class InsertProductDescriptionToDBCommand implements Command {
    @Getter
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Наименование подарка должно быть текстовым";
    @Getter
    private static String webLink = "'Ссылка на сайт' (поле может быть пустым, можете поставить любой " +
            "символ)";
    @Getter
    private final WishService wishService;
    @Getter
    private final InsertNameGiftToDBCommand insertNameGiftToDBCommand;

    @Autowired
    public InsertProductDescriptionToDBCommand(WishService wishService, InsertNameGiftToDBCommand insertNameGiftToDBCommand) {
        this.wishService = wishService;
        this.insertNameGiftToDBCommand = insertNameGiftToDBCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        SendMessage messageWebLink = new SendMessage();
        String giftDescription = update.getMessage().getText();
        if(CheckingInputLinesUtil.checkEmptyString(giftDescription)) {

            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            // вписываем описание в БД
            System.out.println();
            System.out.println("InsertNameGiftToDBCommand.getGift(), который вписываем в БД, но пока без " +
                    "дескрипшина, = "+InsertNameGiftToDBCommand.getGift());
            System.out.println();
            wishService.createDescriptionWish(giftDescription, InsertNameGiftToDBCommand.getGift());
            InsertNameGiftToDBCommand.getGift().setDescriptionGift(giftDescription);

            if (InsertNameUserToDBCommand.getGiftFromDB() != null) {
                WebLinks webLinkFromDB = InsertNameUserToDBCommand.getGiftFromDB().getLink();
                webLink = webLink + "\n" + webLinkFromDB;
            }
            messageWebLink.setChatId(update.getMessage().getChatId())
                    .setText(webLink)
                    .setReplyMarkup(forceReplyKeyboard.setSelective(true));
        } else {
            messageWebLink = messageError(update);
        }
        return messageWebLink;
    }

    private SendMessage messageError(Update update){
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageWebLinkError = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(INPUT_ERROR_MESSAGE);
        messageWebLinkError.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageWebLinkError;
    }
}
