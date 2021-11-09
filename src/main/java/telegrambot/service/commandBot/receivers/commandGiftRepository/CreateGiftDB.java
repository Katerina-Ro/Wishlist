package telegrambot.service.commandBot.receivers.commandGiftRepository;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

import java.sql.SQLException;

@Service
@Getter
@Setter
public class CreateGiftDB implements Command {
    private final GiftRepository giftRepository;
    private String nameGiftOwner;
    private String nameWish;
    private String webLink;
    private String description;
    private boolean giftStatus = true;
    private String CREATE_GIFT_DB = "Пожелание " + nameWish + " добавлено в список Ваших пожеланий" +
            new String(Character.toChars(0x1FA84));

    @Autowired
    public CreateGiftDB(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public void setNameGiftOwner(String nameGiftOwner) {
        this.nameGiftOwner = nameGiftOwner;
    }

    public void setNameWish(String nameWish) {
        this.nameWish = nameWish;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGiftStatus(boolean giftStatus) {
        this.giftStatus = giftStatus;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException, SQLException {
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);

        giftRepository.createGift(nameGiftOwner, nameWish, chatId, webLink, description, giftStatus);
        sendMessage.setText(CREATE_GIFT_DB);
        return sendMessage;
    }
}
