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
    private static String productDescription = "'Описание подарка' (поле может быть пустым, можете " +
            "поставить любой символ)";
    private final WishService wishService;
    @Getter
    private final InsertNameUserToDBCommand insertNameUserToDBCommand;
    @Getter
    @Setter
    private static Gift gift;

    @Autowired
    public InsertNameGiftToDBCommand(WishService wishService, InsertNameUserToDBCommand insertNameUserToDBCommand) {
        this.wishService = wishService;
        this.insertNameUserToDBCommand = insertNameUserToDBCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        System.out.println("внутри InsertNameGiftToDBCommand ");


        String nameGift = update.getMessage().getText();
        long chatIdGiftOwner = update.getMessage().getChatId();

        System.out.println("chatIdGiftOwner = " + chatIdGiftOwner);
        System.out.println();
        System.out.println("productDescription до = " +productDescription);
        if(CheckingInputLinesUtil.checkEmptyString(nameGift)) {
            if (InsertNameUserToDBCommand.getGiftFromDB() != null) {
                System.out.println("InsertNameUserToDBCommand.getGiftFromDB() не пустой = " +InsertNameUserToDBCommand.getGiftFromDB());
                System.out.println();
                System.out.println("InsertNameUserToDBCommand.getGiftFromDB().getGiftOwner() = " + InsertNameUserToDBCommand.getGiftFromDB().getGiftOwner());
                System.out.println();
                System.out.println("nameGift, которое вписываем в базу, = " + nameGift);
                gift = wishService.createNameGift(nameGift, InsertNameUserToDBCommand.getGiftFromDB()
                        .getGiftId(), InsertNameUserToDBCommand.getGiftFromDB().getGiftOwner());
                String wishDescription = InsertNameUserToDBCommand.getGiftFromDB().getDescriptionGift();
                productDescription = productDescription + "\n" + wishDescription;
            } else {
                gift = wishService.createNameGift(nameGift, insertNameUserToDBCommand.getStartCommand().getNewGiftOwner());
            }
            System.out.println("productDescription после = " +productDescription);
            return SendMessageUtils.sendMessage(update, productDescription , true);
        } else {
            return  messageError(update);
        }
    }

    private SendMessage messageError(Update update){
        return SendMessageUtils.sendMessage(update,INPUT_ERROR_MESSAGE, true);
    }
}
