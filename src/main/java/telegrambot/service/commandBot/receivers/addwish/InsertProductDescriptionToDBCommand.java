package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.entities.WebLinks;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

/**
 * Класс-Receiver команды InsertNameGiftToDBCommand.getProductDescription() {@link Command}
 */
@Service
public class InsertProductDescriptionToDBCommand implements Command {
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Наименование подарка должно быть текстовым";
    @Getter
    private static String webLink = "'Ссылка на сайт' (поле может быть пустым, можете поставить любой " +
            "символ)";
    private final WishService wishService;

    @Autowired
    public InsertProductDescriptionToDBCommand(WishService wishService, InsertNameGiftToDBCommand insertNameGiftToDBCommand) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        String giftDescription = update.getMessage().getText();
        if(CheckingInputLinesUtil.checkEmptyString(giftDescription)) {

            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            // вписываем описание в БД
            System.out.println();
            System.out.println("InsertNameGiftToDBCommand.getGift(), который вписываем в БД, но пока без " +
                    "дескрипшина, = "+InsertNameGiftToDBCommand.getGift());
            System.out.println();
            wishService.createDescriptionWish(giftDescription, InsertNameGiftToDBCommand.getGift());

            System.out.println("если сейчас InsertNameGiftToDBCommand.getGift() нулевой, то все должно работать, есл  нет, то нужно его занулить");

            if (InsertNameUserToDBCommand.getGiftFromDB() != null) {
                System.out.println("InsertNameUserToDBCommand.getGiftFromDB(), который не из базы" + InsertNameUserToDBCommand.getGiftFromDB());
                WebLinks webLinkFromDB = InsertNameUserToDBCommand.getGiftFromDB().getLink();
                webLink = webLink + "\n" + webLinkFromDB;
            }
            return SendMessageUtils.sendMessage(update, webLink, true);
        } else {
            return messageError(update);
        }
    }

    private SendMessage messageError(Update update){
        return SendMessageUtils.sendMessage(update, INPUT_ERROR_MESSAGE, true);
    }
}
