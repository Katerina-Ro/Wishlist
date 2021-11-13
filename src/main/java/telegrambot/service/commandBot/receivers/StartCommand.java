package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.TelegramUserService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;


/**
 * Стартовый класс
 */
@Service
@Getter
public class StartCommand implements Command {
    private final Buttons buttons;
    private final String imageGiftBox = String.valueOf(Character.toChars(0x1F381));
    private final String imageWavingHand = String.valueOf(Character.toChars(0x1F44B));
    private final String START_MESSAGE = "Привет " + imageWavingHand + " \nЭто канал для составления списка " +
            "желаний " + imageGiftBox;

    private long numberUser;
    private int phone;

    private final TelegramUserService telegramUserService;

    @Autowired
    public StartCommand(Buttons buttons, TelegramUserService telegramUserService) {
        this.buttons = buttons;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public SendMessage execute(Update update)  {
        numberUser = update.getMessage().getChatId();
        telegramUserService.createIdUserToDB(numberUser);
        return new SendMessage()
        .setChatId(numberUser)
        .enableHtml(true)
        .setText(START_MESSAGE)
        .setReplyMarkup(buttons.getKeyBoardStartMenu());
    }
}