package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

/**
 * Стартовый класс
 */
@Service
@Getter
public class StartCommand {
    private final Buttons buttons;
    private final String imageGiftBox = String.valueOf(Character.toChars(0x1F381));
    private final String imageWavingHand = String.valueOf(Character.toChars(0x1F44B));

    private final String START_MESSAGE = "Привет " + imageWavingHand + " \nЭто канал для составления списка " +
            "желаний " + imageGiftBox;

    @Autowired
    public StartCommand(Buttons buttons) {

        this.buttons = buttons;
    }

    public SendMessage execute(Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtils.getChatId(update).toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(START_MESSAGE);
        sendMessage.setReplyMarkup(buttons.getKeyBoardStartMenu());
        return sendMessage;
    }
}