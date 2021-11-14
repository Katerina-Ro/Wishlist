package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.TelegramUserService;
import telegrambot.service.commandBot.Command;
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
        SendMessage messageAddCommand = new SendMessage();
         /* Math.toIntExact() Новый метод был добавлен с Java 8 для безопасного приведения к int.
           Выкинет ArithmeticException в случае переполнения.
         */
        if(CheckingInputLines.checkEmptyString(update.getMessage().getText()) &&
                CheckingInputLines.isLetters(update.getMessage().getText())) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            /*заносим пользователя в базу данных и отправляем ему новое поле для заполнения.
             Если какая-либо из операций не будет выполнена корректно, то @Transactional не даст внести
             изменения и откатит операцию назад
             */
            telegramUserService.createNameGiftOwner(update.getMessage().getText(),
                    Math.toIntExact(update.getMessage().getChatId()));
            startCommand.getNewGiftOwner().setName(update.getMessage().getText());
            messageAddCommand.setChatId(update.getMessage().getChatId())
                             .setText(NAME_WISH);
            messageAddCommand.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        } else {
            messageAddCommand = messageError(update);
        }
        return messageAddCommand;
    }

    private SendMessage messageError(Update update){
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageAddCommandError = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(INPUT_ERROR_MESSAGE);
        messageAddCommandError.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageAddCommandError;
    }
}
