package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.BotConnect;
import telegrambot.service.service.SendBotMessageService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.utils.CommandUtils;
import telegrambot.service.utils.KeyboardMenu;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Класс-Receiver команды "/start" {@link Command}
 */
@Component
@Getter
public class StartCommand implements Command {

    private final BotConnect botConnect;
    private final SendBotMessageService sendBotMessageService;

   private String START_MESSAGE = "Привет " + new String(Character.toChars(0x1F44B))
            + " \n " +
            "Это канал для составления списка желаний. " +
            "Нажимай Добавить пожелание, либо выбери подарок конкретному человеку.";

   @Autowired
    public StartCommand(SendBotMessageService sendBotMessageService, BotConnect botConnect) {
       this.sendBotMessageService = sendBotMessageService;
       this.botConnect = botConnect;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException {
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