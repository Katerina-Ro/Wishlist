package telegrambot.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.BotConnect;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Реализация {@link SendBotMessageService}
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final BotConnect botConnect;

    @Autowired
    public SendBotMessageServiceImpl(BotConnect botConnect) {
        this.botConnect = botConnect;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        /* https://ru.stackoverflow.com/questions/712136/%D0%92-%D1%87%D1%91%D0%BC-%D0%BE%D1%82%D0%BB%D0%B8%D1%87%D0%B8%D0%B5-isblank-vs-isempty
        В чём отличие isBlank vs isEmpty?
        Т.е. разница в проверке пробела:
            StringUtils.isBlank(" ") = true
            StringUtils.isEmpty(" ") = false
         */
        if (isBlank(message)) return;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        /*
        try {
            botConnect.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }*/
    }
}
