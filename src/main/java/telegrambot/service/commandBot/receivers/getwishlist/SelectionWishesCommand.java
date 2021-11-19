package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WishService;

import java.util.List;

@Service
public class SelectionWishesCommand implements CommandEditSendMessage {
    private final WishService wishService;
    private final TelegramUserService telegramUserService;

    @Autowired
    public SelectionWishesCommand(WishService wishService, TelegramUserService telegramUserService) {
        this.wishService = wishService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        String messageSelectionWishes = "Пожелания выбранного пользователя ";
        System.out.println("Внутри метода this.selectionWishes ");
        String incommingNameGO = update.getCallbackQuery().getData();
        long idPresenter = update.getCallbackQuery().getMessage().getChatId();
        System.out.println("incommingNameGO = "+ incommingNameGO);
        int id = FindingDataUtil.findIdByIncomingMessage(incommingNameGO);
        System.out.println("весь список пожеланий Вовы (должно быть 2 желания, они оба активны ");
        System.out.println("telegramUserService.getGiftOwner(incommingNameGO).getChatId() = " + telegramUserService.getGiftOwner(id).getChatId());
        System.out.println("");
        System.out.println("wishService.getInfoAnotherGiftsInlIdPresenter(telegramUserService.getGiftOwner(id).getChatId(), idPresenter)" + wishService.getInfoAnotherGiftsInlIdPresenter(telegramUserService.getGiftOwner(id).getChatId(), idPresenter));
        List<Gift> list = wishService.getInfoAnotherGiftsInlIdPresenter(telegramUserService.getGiftOwner(id).getChatId(), idPresenter);

        return SendMessageUtils.sendEditMessage(update, messageSelectionWishes,
                MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(list));
    }
}
