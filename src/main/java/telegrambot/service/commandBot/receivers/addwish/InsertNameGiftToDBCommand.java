package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

/**
 * Класс-Receiver команд InsertNameUserToDBCommand.getNameWish() и
 * InsertNameUserToDBCommand.getINPUT_NAME_ERROR_MESSAGE() {@link Command}
 */
@Service
public class InsertNameGiftToDBCommand implements Command {
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Наименование подарка должно быть текстовым";
    @Getter
    private static final String PREV_PRODUCT_DESCRIPTION = "'Описание подарка' (поле может быть пустым, можете " +
            "поставить любой символ)";
    private final WishService wishService;
    @Getter
    private final InsertNameUserToDBCommand insertNameUserToDBCommand;
    @Getter
    @Setter
    private Gift gift;

    @Autowired
    public InsertNameGiftToDBCommand(WishService wishService, InsertNameUserToDBCommand insertNameUserToDBCommand) {
        this.wishService = wishService;
        this.insertNameUserToDBCommand = insertNameUserToDBCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        String nameGift = update.getMessage().getText();
        String productDescription;
        if(CheckingInputLinesUtil.checkEmptyString(nameGift)) {
            if (insertNameUserToDBCommand.getGiftFromDB() != null) {
                gift = wishService.createNameGift(nameGift, insertNameUserToDBCommand.getGiftFromDB()
                        .getGiftId(), insertNameUserToDBCommand.getGiftFromDB().getGiftOwner());
                String wishDescription = insertNameUserToDBCommand.getGiftFromDB().getDescriptionGift();
                productDescription = PREV_PRODUCT_DESCRIPTION + "\n" + wishDescription;
            } else {
                productDescription = PREV_PRODUCT_DESCRIPTION;
                gift = wishService.createNameGift(nameGift, insertNameUserToDBCommand.getStartCommand()
                        .getNewGiftOwner());
            }
            return SendMessageUtils.sendMessage(update, productDescription, true);
        } else {
            return  messageError(update);
        }
    }

    private SendMessage messageError(Update update){
        return SendMessageUtils.sendMessage(update,INPUT_ERROR_MESSAGE, true);
    }
}
