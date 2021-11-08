package telegrambot.service.commandBot.receivers;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.service_needdelete.SendBotMessageService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.utils.CommandUtils;
import telegrambot.service.commandBot.receivers.keyboard.KeyboardMenu;

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
@Service
public class AddCommand{
    private final SendBotMessageService sendBotMessageService;
    //private final TelegramUserService telegramUserService;
    private final String MESSAGE_ADD = "Заполните поля";


    @Autowired
    public AddCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    /*
    @Autowired
    public AddCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    } */

    public SendMessage addWish(Update update) {
        Long chatId = CommandUtils.getChatId(update);

        //if (isBlank(update.getMessage().getText())) return null;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(MESSAGE_ADD);
        sendMessage.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        //botConnect.execute(sendMessage);

        return sendMessage;
    }
}
