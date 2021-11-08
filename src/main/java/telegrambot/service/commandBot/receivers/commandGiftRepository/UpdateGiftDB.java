package telegrambot.service.commandBot.receivers.commandGiftRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.utils.CommandUtils;

import java.sql.SQLException;

public class UpdateGiftDB implements Command {
    private final GiftRepository giftRepository;
    private final String UPDATE_GIFT_DB;

    private String nameWish;
    private String comment;
    private String description;
    private String webLink;

    {
        UPDATE_GIFT_DB = "Пожелание " + nameWish + " изменено";
    }

    @Autowired
    public UpdateGiftDB(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public void setNameWish(String nameWish) {
        this.nameWish = nameWish;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException, SQLException {
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);

        giftRepository.updateGift(chatId,nameWish,description,webLink);
        sendMessage.setText(UPDATE_GIFT_DB);
        return sendMessage;
    }
}
