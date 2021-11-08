package telegrambot.service.commandBot.receivers.commandGiftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.utils.CommandUtils;

import java.sql.SQLException;

public class GetInfoGiftDB implements Command {
    private final GiftRepository giftRepository;
    private final String GET_INFO_GIFT_DB = "Список Ваших подарков:";

    @Autowired
    public GetInfoGiftDB(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException, SQLException {
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);

        giftRepository.getInfoGift(chatId);
        sendMessage.setText(GET_INFO_GIFT_DB);
        return sendMessage;
    }
}
