package telegrambot.service.commandBot.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.BotConnect;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.utils.CommandUtils;
import telegrambot.service.commandBot.receivers.keyboard.KeyboardMenu;

/**
 * Класс-Receiver команды "/info" {@link Command}
 */
@Component
public class InfoCommand implements Command{

    private final BotConnect botConnect;
    private final String INFO_MESSAGE = "Это инструкция INFO " + new String(Character.toChars(0x1F4D7));

    @Autowired
    public InfoCommand(BotConnect botConnect) {
        this.botConnect = botConnect;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException {
        Long chatId = CommandUtils.getChatId(update);
        KeyboardMenu.getKeyBoardMenu();

        /* https://ru.stackoverflow.com/questions/712136/%D0%92-%D1%87%D1%91%D0%BC-%D0%BE%D1%82%D0%BB%D0%B8%D1%87%D0%B8%D0%B5-isblank-vs-isempty
        В чём отличие isBlank vs isEmpty?
        Т.е. разница в проверке пробела:
            StringUtils.isBlank(" ") = true
            StringUtils.isEmpty(" ") = false
         */
        //if (isBlank(update.getMessage().getText())) return null;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(INFO_MESSAGE);
        sendMessage.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        //botConnect.execute(sendMessage);
        return sendMessage;
    }
}
