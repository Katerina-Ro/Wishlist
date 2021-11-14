package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.commandBot.Command;

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
@Service
public class AddCommand implements Command{
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK = String.valueOf(Character.toChars(0x2733));
    private static final String EMPTY_LINE = " \n";
    //StringBuffer???
    private static final String NAME_USER = "'Твое имя'" + IMAGE_EIGHT_SPOKED_ASTERISK + "\n " +
            "(нужно, чтобы тот, кто захочет сделать тебе подарок, смог идентифицировать тебя)";
    @Getter
    private static final String MESSAGE_ADD = "Заполни поля \nПоля, отмеченные " + IMAGE_EIGHT_SPOKED_ASTERISK +
            ", обязательны для заполнения. \n" + EMPTY_LINE + NAME_USER;

    @Override
    public SendMessage execute(Update update) {
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        SendMessage messageAddCommand = new SendMessage()
                .setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(MESSAGE_ADD);
        messageAddCommand.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageAddCommand;
    }
}
