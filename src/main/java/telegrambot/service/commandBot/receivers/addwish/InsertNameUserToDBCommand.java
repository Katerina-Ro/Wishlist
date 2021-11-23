package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.start.StartCommand;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;
import telegrambot.service.entityservice.WishService;
/**
 * Класс-Receiver команд {@link AddCommand.getMESSAGE_ADD()}, {@link InsertNameUserToDBCommand.getINPUT_ERROR_MESSAGE()},
 * {@link InsertNameGiftToDBCommand.getINPUT_ERROR_MESSAGE()} и {@link COMMANDS.CHANGE_WISH.getCommand()} {@link Command}
 */
@Service
public class InsertNameUserToDBCommand implements Command {
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK = String.valueOf(Character.toChars(0x2733));
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL = String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_NAME_ERROR_MESSAGE = "Такое имя уже есть у бота. Введите другое имя";
    @Getter
    private static final String INPUT_ERROR_MESSAGE = HEAVY_EXCLAMATION_MARK_SYMBOL +
            " Имя/ Наименование должно быть текстовым";
    @Getter
    private static final String PREV_NAME_WISH = "'Наименование подарка'" + IMAGE_EIGHT_SPOKED_ASTERISK;
    private final TelegramUserService telegramUserService;
    @Getter
    private final StartCommand startCommand;
    private final WishService wishService;
    @Getter
    @Setter
    private Gift giftFromDB;

    @Autowired
    public InsertNameUserToDBCommand(TelegramUserService telegramUserService, StartCommand startCommand,
                                     WishService wishService) {
        this.telegramUserService = telegramUserService;
        this.startCommand = startCommand;
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update) {
        if (!update.hasCallbackQuery()) {
            long chatIdUser = update.getMessage().getChatId();
            String inputName = update.getMessage().getText();
            if (telegramUserService.existNameUserInDB(inputName)){
                startCommand.getNewGiftOwner().setName(telegramUserService.getGiftOwner(chatIdUser)
                        .getName());
            }
            if (CheckingInputLinesUtil.checkEmptyString(update.getMessage().getText()) &&
                    CheckingInputLinesUtil.isLetters(update.getMessage().getText())) {
                if (!telegramUserService.existNameUserInDB(inputName)) {
                    telegramUserService.createNameGiftOwner(inputName, chatIdUser);
                    startCommand.getNewGiftOwner().setName(telegramUserService.getGiftOwner(chatIdUser)
                            .getName());
                    return SendMessageUtils.sendMessage(update,PREV_NAME_WISH, true);
                } else {
                    return messageErrorName(update);
                }
            } else {
                return messageError(update);
            }
        } else {
            if (CheckingInputLinesUtil.checkEmptyString(update.getCallbackQuery().getData())) {
                int idGift = FindingDataUtil.findIdByIncomingMessage(update.getCallbackQuery().getData());
                wishService.getInfoGiftById(idGift);
                giftFromDB = wishService.getInfoGiftById(idGift);
                String nameWishFromDB = giftFromDB.getNameGift();
                String nameWish = PREV_NAME_WISH + "\n"+ nameWishFromDB;
                return SendMessageUtils.sendMessage(update,nameWish, true);
            } else {
                return messageError(update);
            }
        }
    }

    private SendMessage messageError(Update update){
        return SendMessageUtils.sendMessage(update,INPUT_ERROR_MESSAGE, true);
    }

    private SendMessage messageErrorName(Update update){
        return SendMessageUtils.sendMessage(update,INPUT_NAME_ERROR_MESSAGE, true);
    }
}
