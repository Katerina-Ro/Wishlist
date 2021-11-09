package telegrambot.service.commandBot.receivers.commandGiftRepository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.modelEntities.StatusGift.STATUS_GIFT;
import telegrambot.repository.GiftRepository;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

import java.sql.SQLException;

@Service
@Getter
public class UpdateStatusGift implements Command {
    private final GiftRepository giftRepository;
    private final String UPDATE_STATUS_GIFT;

    private long chat_idPresenter;
    private int idGift;
    private long chat_idGigtOwner;
    private STATUS_GIFT statusGift;
    private String nameWish;

    @Autowired
    public UpdateStatusGift(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
        UPDATE_STATUS_GIFT = "Статус пожелания " + nameWish + " изменен на " + statusGift;
    }

    public void setChat_idPresenter(long chat_idPresenter) {
        this.chat_idPresenter = chat_idPresenter;
    }

    public void setIdGift(int idGift) {
        this.idGift = idGift;
    }

    public void setChat_idGigtOwner(long chat_idGigtOwner) {
        this.chat_idGigtOwner = chat_idGigtOwner;
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

        //statusGift = giftRepository.updateStatusAnotherGift(chatId,idGift,chat_idGigtOwner);
        sendMessage.setText(UPDATE_STATUS_GIFT);
        return sendMessage;
    }
}
