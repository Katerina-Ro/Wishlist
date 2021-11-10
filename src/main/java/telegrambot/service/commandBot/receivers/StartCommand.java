package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

/**
 * Класс-Receiver команды "/start" {@link Command}
 */
@Service
@Getter
public class StartCommand {

    private final ButtonClick buttonClick;

    private final String START_MESSAGE = "Привет " + new String(Character.toChars(0x1F44B))
            + " \n " +
            "Это канал для составления списка желаний. " +
            "Нажимай кнопку 'Добавить пожелание', чтобы написать свое пожелание, " +
            "либо через 'Список подарков' выбери подарок для конкретного человека.";

    @Autowired
    public StartCommand(ButtonClick buttonClick) {
        this.buttonClick = buttonClick;
    }

    public SendMessage execute(Update update) throws TelegramApiException {
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(START_MESSAGE);
        sendMessage.setReplyMarkup(Buttons.getKeyBoardStartMenu());

        return sendMessage;
    }
}