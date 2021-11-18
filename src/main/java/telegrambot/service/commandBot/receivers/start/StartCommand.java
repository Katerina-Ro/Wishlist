package telegrambot.service.commandBot.receivers.start;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.GiftOwner;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;


/**
 * Стартовый класс
 */
@Service
@Getter
public class StartCommand implements Command {
    private final String imageGiftBox = String.valueOf(Character.toChars(0x1F381));
    private final String imageWavingHand = String.valueOf(Character.toChars(0x1F44B));
    private final String START_MESSAGE = "Привет " + imageWavingHand + " \nЭто бот для составления списка " +
            "желаний " + imageGiftBox;

    private long numberUser;
    @Getter
    private final GiftOwner newGiftOwner;

    private final TelegramUserService telegramUserService;

    @Autowired
    public StartCommand(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
        this.newGiftOwner = new GiftOwner();
    }

    @Override
    public SendMessage execute(Update update)  {
        if (update.hasCallbackQuery()){
           numberUser = update.getCallbackQuery().getMessage().getChatId();
        } else {
            numberUser = update.getMessage().getChatId();
        }
        if(!telegramUserService.getTelegramUserRepository().existsById(numberUser)){
            telegramUserService.createIdUserToDB(numberUser);
        }
        newGiftOwner.setChatId(numberUser);
        return new SendMessage()
                .setChatId(numberUser)
                .enableHtml(true)
                .setText(START_MESSAGE)
                .setReplyMarkup(Buttons.getKeyBoardStartMenu());
    }
}