package telegrambot.service.commandBot.receivers.getwishlist;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CheckingInputLinesUtil;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WishService;

import java.util.List;

/**
 * Класс-Receiver команды ChooseWishCommand.getMESSAGE_CHOOSE_WISH_COMMAND() {@link Command}
 */
@Service
public class SearchNameInDBCommand implements Command {
    @Getter
    private static final String NOT_EXIST_ERROR_MESSAGE = "У такого пользователя нет бота WishList." +
            "Выберите другого пользователя";
    @Getter
    private static final String NOT_EXIST_WISH_ERROR_MESSAGE = "У выбранного пользователя нет " +
            "добавленных пожеланий либо все пожелания разобраны";
    @Getter
    private static final String INCORRECT_NAME_ENTERED_ERROR_MESSAGE = "Некорректно введено имя";
    private static final String CHOSE_MYSELF_ERROR_MESSAGE = "Чтобы просмотреть свой список пожеланий," +
            "перейдите 'Посмотреть список желаний' - 'Свой список пожеланий'";
    private static final String ANOTHER_WISHLIST = "Список пожеланий выбранного пользователя";
    private final TelegramUserService telegramUserService;
    private final WishService wishService;

    @Autowired
    public SearchNameInDBCommand(TelegramUserService telegramUserService, WishService wishService) {
        this.telegramUserService = telegramUserService;
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update) {
        SendMessage sendMessageSearchNameInDB;
        String nameSearchUser = update.getMessage().getText();
        long chatIdPresenter = update.getMessage().getChatId();

        if(CheckingInputLinesUtil.checkEmptyString(nameSearchUser) && CheckingInputLinesUtil
                .isLetters(nameSearchUser)){
            if(telegramUserService.existNameUserInDB(nameSearchUser) && !nameSearchUser
                    .equals(telegramUserService.getNameUser(chatIdPresenter))) {
                // Получаем список пожеланий по введенному пользователем имени
                List<Gift> list = wishService.getInfoAnotherGifts(telegramUserService
                        .getGiftOwner(nameSearchUser).getChatId());
                if(list.isEmpty()){
                    // Если у запрошенного человека список пожеланий пуст, то отправляем
                    // соответствующее сообщение
                    return SendMessageUtils.sendMessage(update, NOT_EXIST_WISH_ERROR_MESSAGE,false)
                            .setReplyMarkup(Buttons.getKeyBoardStartMenu());
                } else {
                    // В противном случае отправляем список пожеланий выбранного человека
                   return SendMessageUtils.sendMessage(update, ANOTHER_WISHLIST, false)
                           .setReplyMarkup(MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(list));
                }
            } else if (nameSearchUser.equals(telegramUserService.getNameUser(chatIdPresenter))){
               // Если пользователь ввел свое имя, то направляем его на OwnWishListCommand через кнопку
               // Buttons.getKeyBoardButtonForYoureself()
                return SendMessageUtils.sendMessage(update, CHOSE_MYSELF_ERROR_MESSAGE,true)
                        .setReplyMarkup(MakerInlineKeyboardMarkup.get1InlineKeyboardMarkup(Buttons
                        .getKeyBoardButtonForYoureself()));
            } else {
                return messageErrorNotExist(update);
            }
        } else {
            return messageErrorIncorrectName(update);
        }
    }

    private SendMessage messageErrorIncorrectName(Update update){
        return SendMessageUtils.sendMessage(update, INCORRECT_NAME_ENTERED_ERROR_MESSAGE,true);
    }

    private SendMessage messageErrorNotExist(Update update){
        return SendMessageUtils.sendMessage(update, NOT_EXIST_ERROR_MESSAGE, true);
    }
}
