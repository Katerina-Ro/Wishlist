package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.WebLinks;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

/**
 * Класс-Receiver команды {@link InsertNameGiftToDBCommand.getProductDescription()} {@link Command}
 */
@Service
public class InsertProductDescriptionToDBCommand implements Command {
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL = String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE = HEAVY_EXCLAMATION_MARK_SYMBOL +
            " Строка не может быть пустой";
    @Getter
    private static final String PREV_WEB_LINK = "'Ссылка на сайт' (поле может быть пустым, можете поставить любой " +
            "символ)";
    private final WishService wishService;
    private final InsertNameGiftToDBCommand insertNameGiftToDBCommand;
    private final InsertNameUserToDBCommand insertNameUserToDBCommand;

    @Autowired
    public InsertProductDescriptionToDBCommand(WishService wishService, InsertNameGiftToDBCommand insertNameGiftToDBCommand, InsertNameUserToDBCommand insertNameUserToDBCommand) {
        this.wishService = wishService;
        this.insertNameGiftToDBCommand = insertNameGiftToDBCommand;
        this.insertNameUserToDBCommand = insertNameUserToDBCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        String giftDescription = update.getMessage().getText();
        if(CheckingInputLinesUtil.checkEmptyString(giftDescription)) {
            wishService.createDescriptionWish(giftDescription, insertNameGiftToDBCommand.getGift());
            String webLink = PREV_WEB_LINK;
            if (insertNameUserToDBCommand.getGiftFromDB() != null) {
                WebLinks webLinkFromDB = insertNameUserToDBCommand.getGiftFromDB().getLink();
                webLink = PREV_WEB_LINK + "\n" + webLinkFromDB;
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
