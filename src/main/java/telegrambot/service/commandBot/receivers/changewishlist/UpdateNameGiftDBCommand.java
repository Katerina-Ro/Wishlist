package telegrambot.service.commandBot.receivers.changewishlist;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WishService;
/*
@Service
public class UpdateNameGiftDBCommand implements Command {
    @Getter
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE = HEAVY_EXCLAMATION_MARK_SYMBOL +
             " Наименование подарка должно быть текстовым";
    private static String wishDescriptionFromDB;
    @Getter
    private static final String PRODUCT_DESCRIPTION = "'Описание подарка' \n" + wishDescriptionFromDB;
    @Getter
    private final WishService wishService;
    private final TelegramUserService telegramUserService;

    @Autowired
    public UpdateNameGiftDBCommand(WishService wishService, TelegramUserService telegramUserService) {
        this.wishService = wishService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update) {
        String incomingMessage = update.getCallbackQuery().getData();
        System.out.println("incomingMessage = " + incomingMessage);
        int idGift = FindingDataUtil.findIdByIncomingMessage(incomingMessage);
        System.out.println("idGift = " + idGift);
        long idUser = update.getMessage().getChatId();
        String newNameGift = FindingDataUtil.findLineByIncomingMessage(incomingMessage);
        if(CheckingInputLinesUtil.checkEmptyString(newNameGift)) {
            wishService.createNameGift(newNameGift, telegramUserService.getGiftOwner(idUser));
            wishDescriptionFromDB = ChangeWishCommand.getWish().getDescriptionGift();
            return SendMessageUtils.sendMessage(update, PRODUCT_DESCRIPTION, true);
        } else{
            return messageError(update);
        }
       // return SendMessageUtils.sendMessage(update, wishDescriptionFromDB, true);
    }

    private SendMessage messageError(Update update){
        return SendMessageUtils.sendMessage(update, INPUT_ERROR_MESSAGE, true);

        /*
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageProductDescriptionError = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(INPUT_ERROR_MESSAGE);
        messageProductDescriptionError.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageProductDescriptionError;
    }
} */
