package telegrambot.service.commandBot.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.service.SendBotMessageService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.utils.CommandUtils;
import telegrambot.service.utils.KeyboardMenu;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
public class AddCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    @Autowired
    public AddCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public SendMessage execute(Update update) {
        Long chatId = CommandUtils.getChatId(update);

        if (isBlank(update.getMessage().getText())) return null;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(START_MESSAGE);
        sendMessage.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        botConnect.execute(sendMessage);

        return sendMessage;
    }
}
