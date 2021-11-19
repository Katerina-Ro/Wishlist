package telegrambot.service.commandBot.receivers.changewishlist;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.commandBot.Command;
import telegrambot.service.entityservice.WishService;

@Service
public class UpdateNameGiftDBCommand implements Command {
    @Getter
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE = HEAVY_EXCLAMATION_MARK_SYMBOL +
             " Наименование подарка должно быть текстовым";
    @Getter
    private static final String PRODUCT_DESCRIPTION = "'Описание подарка' \n" +
            "(поле может быть пустым, можете поставить любой символ)";
    @Getter
    private final WishService wishService;

    @Autowired
    public UpdateNameGiftDBCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update) {

        return null;
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
