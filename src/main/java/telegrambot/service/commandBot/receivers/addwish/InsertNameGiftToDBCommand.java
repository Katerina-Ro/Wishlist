package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

@Service
public class InsertNameGiftToDBCommand implements Command {
    @Getter
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Наименование подарка должно быть текстовым";
    @Getter
    private static final String PRODUCT_DESCRIPTION = "'Описание подарка' \n" +
            "(поле может быть пустым, можете поставить любой символ)";
    @Getter
    private final WishService wishService;
    @Getter
    private final InsertNameUserToDBCommand insertNameUserToDBCommand;
    @Getter
    private Gift gift;

    @Autowired
    public InsertNameGiftToDBCommand(WishService wishService, InsertNameUserToDBCommand insertNameUserToDBCommand) {
        this.wishService = wishService;
        this.insertNameUserToDBCommand = insertNameUserToDBCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        /*
        String incomingMessage = update.getCallbackQuery().getData();
        System.out.println("incomingMessage = " + incomingMessage);
        int idGift = FindingDataUtil.findIdByIncomingMessage(incomingMessage);
        System.out.println("idGift = " + idGift);
        wish = wishService.getInfoGiftById(idGift);
        nameWishFromDB = wish.getNameGift();
        return SendMessageUtils.sendMessage(update, nameWishFromDB, true);
        */


        SendMessage messageProductDescription = new SendMessage();
        String nameGift = update.getMessage().getText();
        long chatIdGiftOwner = update.getMessage().getChatId();

        System.out.println("chatIdGiftOwner = " + chatIdGiftOwner);

        if(CheckingInputLinesUtil.checkEmptyString(nameGift)) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            gift = wishService.createNameGift(nameGift, insertNameUserToDBCommand.getStartCommand().getNewGiftOwner());
            messageProductDescription.setChatId(chatIdGiftOwner)
                    .setText(PRODUCT_DESCRIPTION);
            messageProductDescription.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        } else {
            messageProductDescription = messageError(update);
        }
        return messageProductDescription;
    }

    private SendMessage messageError(Update update){
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageProductDescriptionError = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(INPUT_ERROR_MESSAGE);
        messageProductDescriptionError.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageProductDescriptionError;
    }
}
