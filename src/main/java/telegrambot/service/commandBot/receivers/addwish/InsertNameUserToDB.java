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
import telegrambot.service.commandBot.receivers.utils.CheckingInputLines;

@Service
public class InsertNameUserToDB implements Command {
    @Getter
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK =
            String.valueOf(Character.toChars(0x2733));
    @Getter
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL =
            String.valueOf(Character.toChars(0x2757));
    @Getter
    private static final String INPUT_ERROR_MESSAGE =
            HEAVY_EXCLAMATION_MARK_SYMBOL + " Имя должно быть текстовым";
    @Getter
    private static final String NAME_WISH = "'Наименование подарка'" + IMAGE_EIGHT_SPOKED_ASTERISK;
    @Getter
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
        if(CheckingInputLines.checkEmptyString(update.getMessage().getText()) &&
                CheckingInputLines.isLetters(update.getMessage().getText())) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            /*заносим пользователя в базу данных и отправляем ему новое поле для заполнения.
             Если какая-либо из операций не будет выполнена корректно, то @Transactional не даст внести
             изменения и откатит операцию назад
             */
            telegramUserService.createNameGiftOwner(update.getMessage().getText(),
                    chatIdUser);
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
}
