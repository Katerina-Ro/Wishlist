package telegrambot.service.commandBot.receivers.start;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.GiftOwner;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

/**
 * Стартовый класс для обработки сообщения {@link COMMANDS.START} - "/start" {@link Command}
 */
@Service
@Getter
public class StartCommand implements Command {
    private static final String IMAGE_GIFT_BOX = String.valueOf(Character.toChars(0x1F381));
    private static final String IMAGE_WAVING_HAND = String.valueOf(Character.toChars(0x1F44B));
    private static final String START_MESSAGE = "Привет " + IMAGE_WAVING_HAND + " \nЭто бот для составления списка " +
            "желаний " + IMAGE_GIFT_BOX;
    private final TelegramUserService telegramUserService;
    @Getter
    @Setter
    private GiftOwner newGiftOwner = new GiftOwner();

    @Autowired
    public StartCommand(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
    }

    @Override
    public SendMessage execute(Update update)  {
        long numberUser;
        if (update.hasCallbackQuery()){
           numberUser = update.getCallbackQuery().getMessage().getChatId();
        } else {
            numberUser = update.getMessage().getChatId();
        }
        if(!telegramUserService.getTelegramUserRepository().existsById(numberUser)){
            telegramUserService.createIdUserToDB(numberUser);
        }
        newGiftOwner.setChatId(numberUser);
        return SendMessageUtils.sendMessage(update,START_MESSAGE, false)
                .setReplyMarkup(Buttons.getKeyBoardStartMenu());
    }
}