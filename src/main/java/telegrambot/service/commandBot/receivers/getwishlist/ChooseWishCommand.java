package telegrambot.service.commandBot.receivers.getwishlist;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;

/**
 * Класс-Receiver команды {@link COMMANDS.CHOOSE_WISH} {@link Command}
 */
@Service
public class ChooseWishCommand implements Command {
    @Getter
    private static final String MESSAGE_CHOOSE_WISH_COMMAND = "Напишите имя пользователя, чей список " +
            "пожеланий хотите просмотреть";

    @Override
    @Transactional
    public SendMessage execute(Update update) {
        return SendMessageUtils.sendMessage(update, MESSAGE_CHOOSE_WISH_COMMAND, true);
    }
}
