package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.commandBot.Command;

/**
 * Класс-Receiver команды "Добавить пожелание" {@link Command}
 */
@Service
public class AddCommand implements Command{
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK = String.valueOf(Character
            .toChars(0x2733));
    private static final String NAME_USER = "'Твое имя'" + IMAGE_EIGHT_SPOKED_ASTERISK +
            "(нужно, чтобы тот, кто захочет сделать тебе подарок, смог идентифицировать тебя)";
    @Getter
    private static final String MESSAGE_ADD = "Поля, отмеченные " + IMAGE_EIGHT_SPOKED_ASTERISK +
            ", обязательны для заполнения. " + NAME_USER;
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
        if (telegramUserService.containsNameUserInDB(update.getCallbackQuery().getMessage().getChatId())) {
            return SendMessageUtils.sendMessage(update, NAME_WISH, true);
        } else {
            return SendMessageUtils.sendMessage(update, MESSAGE_ADD, true);
        }
    }
}
