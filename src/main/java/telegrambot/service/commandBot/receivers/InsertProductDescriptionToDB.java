package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.WishService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLines;

@Service
public class InsertProductDescriptionToDB implements Command {
    @Getter
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Наименование подарка должно быть текстовым";
    @Getter
    private static final String WEB_LINK = "'Ссылка на сайт' \n" +
            "(поле может быть пустым, можете поставить любой символ)";
    private final WishService wishService;
    @Getter
    private final InsertNameGiftToDB insertNameGiftToDB;

    @Autowired
    public InsertProductDescriptionToDB(WishService wishService, InsertNameGiftToDB insertNameGiftToDB) {
        this.wishService = wishService;
        this.insertNameGiftToDB = insertNameGiftToDB;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        SendMessage messageWebLink = new SendMessage();
        String giftDescription = update.getMessage().getText();

        System.out.println("описание пожелания " + giftDescription);

        if(CheckingInputLines.checkEmptyString(giftDescription)) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            wishService.createWish(giftDescription, insertNameGiftToDB.getGift());
            messageWebLink.setChatId(update.getMessage().getChatId())
                    .setText(WEB_LINK);
            messageWebLink.setReplyMarkup(forceReplyKeyboard.setSelective(true));
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
