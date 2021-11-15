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

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
@Service
public class AddCommand implements Command{
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK = String.valueOf(Character
            .toChars(0x2733));
    private static final String EMPTY_LINE = " \n";
    //StringBuffer???
    private static final String NAME_USER = "'Твое имя'" + IMAGE_EIGHT_SPOKED_ASTERISK + "\n " +
            "(нужно, чтобы тот, кто захочет сделать тебе подарок, смог идентифицировать тебя)";
    @Getter
    private static final String MESSAGE_ADD = "Заполни поля \nПоля, отмеченные " +
            IMAGE_EIGHT_SPOKED_ASTERISK + ", обязательны для заполнения. \n" + EMPTY_LINE + NAME_USER;
    @Getter
    private static final String NAME_WISH = "'Наименование подарка'" + IMAGE_EIGHT_SPOKED_ASTERISK;
    @Getter
    private final TelegramUserService telegramUserService;

    @Autowired
    public AddCommand(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update) {
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        long chatIdUser = update.getCallbackQuery().getMessage().getChatId();
        SendMessage messageAddCommand = new SendMessage()
                .setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setChatId(chatIdUser);
        if(telegramUserService.getTelegramUserRepository().existsById(chatIdUser)){
            messageAddCommand.setText(NAME_WISH);
        } else {
            messageAddCommand.setText(MESSAGE_ADD);
        }
        messageAddCommand.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        return messageAddCommand;
    }
}
