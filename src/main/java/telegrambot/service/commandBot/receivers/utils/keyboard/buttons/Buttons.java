package telegrambot.service.commandBot.receivers.utils.keyboard.buttons;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.COMMANDS;

@Service
@Getter
public class Buttons {
    private final String BUTTON_ADD_WISH = "Добавить пожелание";
    private final String BUTTON_ADD_NAME_USER_TO_DB = "Добавить имя";
    private static final String BUTTON_BACK = "Назад";
    private static final String BUTTON_CHANGE_WISH = "Изменить желание";
    private static final String BUTTON_CHOOSE_THIS = "Выбрать";
    private static final String BUTTON_DELETE = "Удалить";
    private static final String BUTTON_GET_WISHLIST = "Посмотреть список желаний";
    private static final String BUTTON_INFO = "О чем канал?";
    private static final String BUTTON_MORE_DETAILS = "Подробнее";
    private static final String BUTTON_BUTTON_SEND = "Отправить";
    private static final String BUTTON_YES_LABEL = "Да";
    private static final String BUTTON_FOR_YOURESELF = "Свой список пожеланий";
    private static final String BUTTON_FOR_ANOTHER = "Для другого человека";

    public InlineKeyboardButton getKeyBoardYes(boolean activeState){
        InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
        inlineKeyboardButtonYes.setText(BUTTON_YES_LABEL);
        inlineKeyboardButtonYes.setCallbackData(COMMANDS.YES.getCommand());
        return inlineKeyboardButtonYes;
    }

    public InlineKeyboardButton getKeyBoardButtonSend(){
        InlineKeyboardButton inlineKeyboardButtonButtonSend = new InlineKeyboardButton();
        inlineKeyboardButtonButtonSend.setText(BUTTON_BUTTON_SEND);
        inlineKeyboardButtonButtonSend.setCallbackData(COMMANDS.SEND.getCommand());
        return inlineKeyboardButtonButtonSend;
    }

    public InlineKeyboardButton getKeyBoardMoreDetails(){
        InlineKeyboardButton inlineKeyboardButtonMoreDetails = new InlineKeyboardButton();
        inlineKeyboardButtonMoreDetails.setText(BUTTON_MORE_DETAILS);
        inlineKeyboardButtonMoreDetails.setCallbackData(COMMANDS.MORE_DETAILS.getCommand());
        return inlineKeyboardButtonMoreDetails;
    }

    public InlineKeyboardButton getKeyBoardButtonInfo(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_INFO);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.INFO.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public InlineKeyboardButton getKeyBoardButtonGetWishList (){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_GET_WISHLIST);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.WISHLIST.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public InlineKeyboardButton getKeyBoardDeleteWish(){
        InlineKeyboardButton inlineKeyboardButtonDeleteWish = new InlineKeyboardButton();
        inlineKeyboardButtonDeleteWish.setText(BUTTON_DELETE);
        inlineKeyboardButtonDeleteWish.setCallbackData(COMMANDS.DELETE.getCommand());
        return inlineKeyboardButtonDeleteWish;
    }

    public InlineKeyboardButton  getKeyBoardButtonChooseThis(String nameWish){
        InlineKeyboardButton inlineKeyboardButtonButtonChooseThis = new InlineKeyboardButton();
        inlineKeyboardButtonButtonChooseThis.setText(BUTTON_CHOOSE_THIS);
        inlineKeyboardButtonButtonChooseThis.setCallbackData(COMMANDS.CHOOSE.getCommand());

        InlineKeyboardButton inlineKeyboardButtonButtonWishFromDB = new InlineKeyboardButton();
        inlineKeyboardButtonButtonWishFromDB.setText(nameWish);

        /*
        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add (inlineKeyboardButtonButtonChooseThis);
        List<InlineKeyboardButton> keyboardButtonRow2 = new ArrayList<>();
        keyboardButtonRow2.add (inlineKeyboardButtonButtonWishFromDB);


         */
        return inlineKeyboardButtonButtonChooseThis;
    }

    public InlineKeyboardButton getKeyBoardButtonChangeWish(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_CHANGE_WISH);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.CHANGE_WISH.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public InlineKeyboardButton  getKeyBoardButtonBack(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_BACK);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.BACK.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public  InlineKeyboardButton getKeyBoardButtonAddWish(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_ADD_WISH);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.ADD_WISH.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public  InlineKeyboardButton getKeyBoardButtonAdd(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_ADD_NAME_USER_TO_DB);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.ADD_NAME_USER_TO_DB.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public InlineKeyboardButton getKeyBoardButtonForYoureself(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_FOR_YOURESELF);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.FOR_YOURESELF.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public InlineKeyboardButton getKeyBoardButtonForAnother(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_FOR_ANOTHER);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.FOR_ANOTHER.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public InlineKeyboardMarkup getKeyBoardStartMenu(){
        return MakerInlineKeyboardMarkup.get2x2InlineKeyboardMarkup(getKeyBoardButtonInfo(),
                getKeyBoardButtonAddWish(), getKeyBoardButtonGetWishList(),
                getKeyBoardButtonChangeWish());
    }
}
