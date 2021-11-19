package telegrambot.service.commandBot.receivers.changewishlist;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.WishService;

/**
 * Класс-Receiver команды "/changeWish" {@link Command}
 */
@Service
public class ChangeWishCommand implements Command {
    private static String nameWishFromDB;
    @Getter
    private final static String MESSAGE_CHANGE_WISH = "Наименование пожелания " + nameWishFromDB;
    private final WishService wishService;
    private Gift wish;


    @Autowired
    public ChangeWishCommand(WishService wishService) {
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {
        String incomingMessage = update.getCallbackQuery().getData();
        System.out.println("incomingMessage = " + incomingMessage);
        int idGift = FindingDataUtil.findIdByIncomingMessage(incomingMessage);
        System.out.println("idGift = " + idGift);
        wish = wishService.getInfoGiftById(idGift);
        nameWishFromDB = wish.getNameGift();
        return SendMessageUtils.sendMessage(update, nameWishFromDB, true);
    }
}
