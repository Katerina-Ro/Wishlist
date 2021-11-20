package telegrambot.service.commandBot.receivers.start;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

/**
 * Класс-Receiver неизвестной команды {@link CommandEditSendMessage}
 */
@Service
public class UnknownCommand implements CommandEditSendMessage {
    private static final String MESSAGE_UNKNOWNCOMMAND = "Введите текст либо выберите одну из " +
            "предложенных кнопок";

    @Override
    public EditMessageText execute(Update update) {
        return SendMessageUtils.sendEditMessage (update, MESSAGE_UNKNOWNCOMMAND,
                Buttons.getKeyBoardStartMenu());
    }
}
