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
 * Класс-Receiver команд AddCommand.getMESSAGE_ADD(), InsertNameUserToDBCommand.getINPUT_ERROR_MESSAGE(),
 * InsertNameGiftToDBCommand.getINPUT_ERROR_MESSAGE() и COMMANDS.CHANGE_WISH.getCommand() {@link Command}
 */
@Service
public class InsertNameUserToDBCommand implements Command {
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK =
            String.valueOf(Character.toChars(0x2733));
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_NAME_ERROR_MESSAGE = "Такое имя уже есть у бота. Введите другое имя";
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Имя/ Наименование должно быть текстовым";
    private final TelegramUserService telegramUserService;
    @Getter
    private final StartCommand startCommand;
    private final WishService wishService;
    @Getter
    @Setter
    private static Gift giftFromDB;
    @Getter
    private static String nameWish = "'Наименование подарка'" + IMAGE_EIGHT_SPOKED_ASTERISK;

    @Autowired
    public InsertNameUserToDBCommand(TelegramUserService telegramUserService, StartCommand startCommand, WishService wishService) {
        this.telegramUserService = telegramUserService;
        this.startCommand = startCommand;
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update) {
        if (!update.hasCallbackQuery()) {
            System.out.println("это НЕ колбэк");
            System.out.println();
            System.out.println("в методе InsertNameUserToDBCommand ");
            long chatIdUser = update.getMessage().getChatId();
            // Проверяем строку на пустоту
            if (CheckingInputLinesUtil.checkEmptyString(update.getMessage().getText()) &&
                    CheckingInputLinesUtil.isLetters(update.getMessage().getText())) {
                String inputName = update.getMessage().getText();
                System.out.println("внутри блока if (CheckingInputLinesUtil.checkEmptyString(update.getMessage().getText()) &&\n" +
                        "                    CheckingInputLinesUtil.isLetters(update.getMessage().getText()))");
                System.out.println();
                // Если строка введена корректно, то проверяем, нет ли такого же имени в БД
                if (!telegramUserService.existNameUserInDB(inputName)) {
                    // Если такого имени в БД нет, то заносим введенное имя пользователя в БД
                    System.out.println();
                    System.out.println("внутри блока if (!telegramUserService.existNameUserInDB(inputName)) {");
                    telegramUserService.createNameGiftOwner(inputName,
                            chatIdUser);
                } else {
                    // Если такое имя есть, то просим подобрать другое имя
                    return messageErrorName(update);
                }
                // Если пользователь с таким именеем уже есть в БД, то получаем его имя из БД и
                // заносим в GiftOwner
                System.out.println();
                System.out.println();
                if (telegramUserService.getTelegramUserRepository().existsById(chatIdUser)) {
                    startCommand.getNewGiftOwner().setName(telegramUserService
                            .getGiftOwner(chatIdUser).getName());
                } else {
                    // Если пользователя нет в БД, то заносим его имя в GiftOwner
                    startCommand.getNewGiftOwner().setName(update.getMessage().getText());
                }
                return SendMessageUtils.sendMessage(update,nameWish, true);
            } else {
                // Если поле имени пустое, то сообщаем об ошибке и просим внести корректно
                return messageError(update);
            }
        } else{
            System.out.println("это колбэк");
            if (CheckingInputLinesUtil.checkEmptyString(update.getCallbackQuery().getData())) {
                System.out.println();
                String incomingMessage = update.getCallbackQuery().getData();
                System.out.println("incomingMessage = " + incomingMessage);
                int idGift = FindingDataUtil.findIdByIncomingMessage(incomingMessage);
                System.out.println("idGift = " + idGift);
                wishService.getInfoGiftById(idGift);
                giftFromDB = wishService.getInfoGiftById(idGift);
                String nameWishFromDB = giftFromDB.getNameGift();
                nameWish = nameWish + "\n"+ nameWishFromDB;
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
