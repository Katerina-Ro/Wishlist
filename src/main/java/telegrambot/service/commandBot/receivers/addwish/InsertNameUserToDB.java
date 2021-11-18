package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.start.StartCommand;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;

@Service
public class InsertNameUserToDB implements Command {
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK =
            String.valueOf(Character.toChars(0x2733));
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_NAME_ERROR_MESSAGE = "Такое имя уже есть у бота. Введите другое имя";
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Имя должно быть текстовым";
    @Getter
    private static final String NAME_WISH = "'Наименование подарка'" + IMAGE_EIGHT_SPOKED_ASTERISK;
    private final TelegramUserService telegramUserService;
    @Getter
    private final StartCommand startCommand;

    @Autowired
    public InsertNameUserToDB(TelegramUserService telegramUserService, StartCommand startCommand) {
        this.telegramUserService = telegramUserService;
        this.startCommand = startCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        SendMessage messageInsertNameUserToDBCommand = new SendMessage();
        long chatIdUser = update.getMessage().getChatId();
        if(CheckingInputLinesUtil.checkEmptyString(update.getMessage().getText()) &&
                CheckingInputLinesUtil.isLetters(update.getMessage().getText())) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            String inputName = update.getMessage().getText();
            if(!telegramUserService.existNameUserInDB(inputName)) {
                telegramUserService.createNameGiftOwner(inputName,
                        chatIdUser);
            } else {
                messageInsertNameUserToDBCommand = messageErrorName(update);
            }
            if(telegramUserService.getTelegramUserRepository().existsById(chatIdUser)) {
                startCommand.getNewGiftOwner().setName(telegramUserService
                        .getGiftOwner(chatIdUser).getName());
            } else {
                startCommand.getNewGiftOwner().setName(update.getMessage().getText());
            }
            messageInsertNameUserToDBCommand.setChatId(update.getMessage().getChatId())
                             .setText(NAME_WISH);
            messageInsertNameUserToDBCommand.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        } else {
            messageInsertNameUserToDBCommand = messageError(update);
        }
        return messageInsertNameUserToDBCommand;
    }

    private SendMessage messageError(Update update){
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageInsertNameUserToDBCommandError = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(INPUT_ERROR_MESSAGE);
        messageInsertNameUserToDBCommandError.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageInsertNameUserToDBCommandError;
    }

    private SendMessage messageErrorName(Update update){
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageInsertNameUserToDBCommandErrorName = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(INPUT_NAME_ERROR_MESSAGE);
        messageInsertNameUserToDBCommandErrorName.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageInsertNameUserToDBCommandErrorName;
    }
}
