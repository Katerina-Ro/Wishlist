package telegrambot.service.commandBot.receivers.getwishlist;

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

@Service
public class SearchNameInDBCommand implements Command {
    private static final String NOT_EXIST_ERROR_MESSAGE = "У такого пользователя нет бота WishList." +
            "Выберите другого пользователя";
    private static final String NOT_EXIST_WISH_ERROR_MESSAGE = "У выбранного пользователя нет " +
            "добавленных пожеланий либо все пожелания разобраны";
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

        if(CheckingInputLinesUtil.checkEmptyString(nameSearchUser)){
            if(telegramUserService.existNameUserInDB(nameSearchUser) && !nameSearchUser
                    .equals(telegramUserService.getNameUser(chatIdPresenter))) {
                System.out.println("");
                System.out.println("внутри проверки if(telegramUserService.existNameUserInDB(nameSearchUser) && !nameSearchUser\n" +
                        "                    .equals(telegramUserService.getNameUser(chatIdPresenter))) ");
                System.out.println("");
                GiftOwner go = telegramUserService.getGiftOwner(nameSearchUser);
                long idUser = go.getChatId();
                System.out.println("long idUser = go.getChatId() = " + go.getChatId());
                List<Gift> list = wishService.getInfoAnotherGifts(idUser);
                System.out.println("");

                System.out.println("list" + list);
                System.out.println("");

                long chatIdUser = update.getMessage().getChatId();

                if(list.isEmpty()){
                    sendMessageSearchNameInDB = new SendMessage()
                            .enableHtml(true)
                            .setChatId(chatIdUser)
                            .setText(NOT_EXIST_WISH_ERROR_MESSAGE)
                            .setReplyMarkup(Buttons.getKeyBoardStartMenu());
                } else {
                        System.out.println();
                        System.out.println(chatIdUser);
                    sendMessageSearchNameInDB = new SendMessage()
                            .enableHtml(true)
                            .setChatId(chatIdUser)
                            .setText(ANOTHER_WISHLIST)
                            .setReplyMarkup(MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(list));


                /*
                SendMessageUtils.sendMessage(update, ANOTHER_WISHLIST, false).setReplyMarkup(
                        MakerInlineKeyboardMarkupUtils.get3RowsInlineKeyboardMarkup(list)); */
                }
            } else if (nameSearchUser.equals(telegramUserService.getNameUser(chatIdPresenter))){
                long chatIdUser = update.getMessage().getChatId();
                    System.out.println();
                    System.out.println("chatIdUser в ФорсРеплай = "+chatIdUser);
                sendMessageSearchNameInDB = new SendMessage()
                        .enableHtml(true)
                        .setChatId(chatIdUser)
                        .setText(CHOSE_MYSELF_ERROR_MESSAGE);
                System.out.println(CHOSE_MYSELF_ERROR_MESSAGE);
                ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
                //sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
                forceReplyKeyboard.setSelective(true);
                sendMessageSearchNameInDB.setReplyMarkup(MakerInlineKeyboardMarkup.get1InlineKeyboardMarkup(Buttons
                .getKeyBoardButtonForYoureself()));
               /*
                SendMessageUtils.sendMessage(update, CHOSE_MYSELF_ERROR_MESSAGE, false)
                .setReplyMarkup(MakerInlineKeyboardMarkup.get1InlineKeyboardMarkup(
                        Buttons.getKeyBoardButtonForYoureself())); */
            } else {
                sendMessageSearchNameInDB = messageErrorNotExist(update);
            }
        } else {
            sendMessageSearchNameInDB = messageErrorIncorrectName(update);
        }
        return sendMessageSearchNameInDB;
    }

    private SendMessage messageErrorIncorrectName(Update update){
        return SendMessageUtils.sendMessage(update, INCORRECT_NAME_ENTERED_ERROR_MESSAGE,true);
    }
    
    private SendMessage messageErrorNotExist(Update update){
        return SendMessageUtils.sendMessage(update, NOT_EXIST_ERROR_MESSAGE, true);
    }
}
