package telegrambot.service.commandBot.receivers.commandGiftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.utils.CommandUtils;

import java.sql.SQLException;

@Service
public class DeleteGiftDB implements Command {

    private final GiftRepository giftRepository;
    private String nameWish;
    private String DELETE_DB = "Пожелание " + nameWish + " удалено";

    @Autowired
    public DeleteGiftDB(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public void setNameWish(String nameWish) {
        this.nameWish = nameWish;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException, SQLException {
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);

        giftRepository.deleteGift(update.getUpdateId(), nameWish);
        sendMessage.setText(DELETE_DB);
        return sendMessage;
    }
}
