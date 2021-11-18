package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WishService;

import java.util.List;

@Service
public class SelectionWishes implements CommandEditSendMessage {
    private final WishService wishService;
    private final TelegramUserService telegramUserService;

    @Autowired
    public SelectionWishes(WishService wishService, TelegramUserService telegramUserService) {
        this.wishService = wishService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        String messageSelectionWishes = "Пожелания выбранного пользователя ";
        System.out.println("Внутри метода this.selectionWishes ");
        String incommingNameGO = update.getCallbackQuery().getData();
        System.out.println("incommingNameGO = "+ incommingNameGO);
        System.out.println("telegramUserService.getGiftOwner(incommingNameGO).getChatId() = " + telegramUserService.getGiftOwner(incommingNameGO).getChatId());
        List<Gift> list = wishService.getInfoAnotherGifts(telegramUserService.getGiftOwner(incommingNameGO).getChatId());

        return SendMessageUtils.sendEditMessage(update, messageSelectionWishes, MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(list));
    }
}
