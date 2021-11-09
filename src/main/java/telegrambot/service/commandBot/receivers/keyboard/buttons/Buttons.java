package telegrambot.service.commandBot.receivers.keyboard.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.receivers.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

public class Buttons {
    private static final String BUTTON_ADD_WISH = "Добавить пожелание";
    private static final String BUTTON_BACK = "Назад";
    private static final String BUTTON_CHANGE_WISH = "Изменить желание";
    private static final String BUTTON_CHOOSE_THIS = "Выбрать";
    private static final String BUTTONDELETE = "Удалить";
    private static final String BUTTON_GET_WISHList = "Посмотреть список желаний";
    private static final String BUTTON_INFO = "О чем канал?";
    private static final String MORE_DETAILS = "Подробнее";
    private static final String BUTTON_SEND = "Отправить";
    private static final String YES_LABEL = "Да";

    public static InlineKeyboardButton getKeyBoardYes(boolean activeState){
        InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
        inlineKeyboardButtonYes.setText(YES_LABEL);
        inlineKeyboardButtonYes.setCallbackData(COMMANDS.YES.getCommand());
        return inlineKeyboardButtonYes;
    }

    public static  InlineKeyboardButton getKeyBoardButtonSend(){
        InlineKeyboardButton inlineKeyboardButtonButtonSend = new InlineKeyboardButton();
        inlineKeyboardButtonButtonSend.setText(BUTTON_SEND);
        inlineKeyboardButtonButtonSend.setCallbackData(COMMANDS.SEND.getCommand());
        return inlineKeyboardButtonButtonSend;
    }

    public static  InlineKeyboardButton getKeyBoardMoreDetails(){
        InlineKeyboardButton inlineKeyboardButtonMoreDetails = new InlineKeyboardButton();
        inlineKeyboardButtonMoreDetails.setText(MORE_DETAILS);
        inlineKeyboardButtonMoreDetails.setCallbackData(COMMANDS.MORE_DETAILS.getCommand());
        return inlineKeyboardButtonMoreDetails;
    }

    public static InlineKeyboardButton getKeyBoardButtonInfo(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_INFO);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.INFO.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public static InlineKeyboardButton getKeyBoardButtonGetWishList (){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_GET_WISHList);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.WISHLIST.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public static InlineKeyboardButton getKeyBoardDeleteWish(){
        InlineKeyboardButton inlineKeyboardButtonDeleteWish = new InlineKeyboardButton();
        inlineKeyboardButtonDeleteWish.setText(BUTTONDELETE);
        inlineKeyboardButtonDeleteWish.setCallbackData(COMMANDS.DELETE.getCommand());
        return inlineKeyboardButtonDeleteWish;
    }

    public static InlineKeyboardButton  getKeyBoardButtonChooseThis(String nameWish){
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

    public static InlineKeyboardButton getKeyBoardButtonChangeWish(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_CHANGE_WISH);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.CHANGE_WISH.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public static InlineKeyboardButton  getKeyBoardButtonBack(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_BACK);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.BACK.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public static InlineKeyboardButton getKeyBoardButtonAddWish(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_ADD_WISH);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.ADD_WISH.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public static InlineKeyboardMarkup getKeyBoardStartMenu(){
        return MakerInlineKeyboardMarkup.get2x2InlineKeyboardMarkup(getKeyBoardButtonInfo(),
                getKeyBoardButtonAddWish(), getKeyBoardButtonGetWishList(),
                getKeyBoardButtonBack());
    }
}
