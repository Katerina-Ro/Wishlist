package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.entities.Gift;
import telegrambot.service.entityservice.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

@Service

public class InsertNameGiftToDB implements Command {
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
    private final InsertNameUserToDB insertNameUserToDB;
    @Getter
    private Gift gift;

    @Autowired
    public InsertNameGiftToDB(WishService wishService, InsertNameUserToDB insertNameUserToDB) {
        this.wishService = wishService;
        this.insertNameUserToDB = insertNameUserToDB;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        SendMessage messageProductDescription = new SendMessage();
        String nameGift = update.getMessage().getText();
        long chatIdGiftOwner = update.getMessage().getChatId();

        System.out.println("chatIdGiftOwner = " + chatIdGiftOwner);

        if(CheckingInputLinesUtil.checkEmptyString(nameGift)) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            gift = wishService.createNameGift(nameGift, insertNameUserToDB.getStartCommand().getNewGiftOwner());
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
