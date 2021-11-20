package telegrambot.service.commandBot.receivers.utils.keyboard.buttons;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.COMMANDS;

@Getter
public class Buttons {
    private static final String BUTTON_ADD_WISH = "Добавить пожелание";
    private static final String BUTTON_ADD_MORE_WISH = "Добавить еще пожелание";
    private static final String BUTTON_ADD_NAME_USER_TO_DB = "Добавить имя";
    private static final String BUTTON_BACK = "Назад к выбору списка желаний";
    private static final String BUTTON_CHANGE_STATUS_OWN_WISH = "Поменять статус пожелания";
    private static final String BUTTON_CHANGE_WISH = "Изменить желание";
    private static final String BUTTON_CHOOSE_THIS = "Выбрать";
    private static final String BUTTON_DELETE = "Удалить";
    private static final String BUTTON_GET_WISHLIST = "Посмотреть список желаний";
    //private static final String BUTTON_GIVE_TO_OTHERS = "Дарю другим";
    private static final String BUTTON_INFO = "О чем канал?";
    private static final String BUTTON_MORE_DETAILS = "Подробнее";
    private static final String BUTTON_NAME_GIFT_OWNER = "Чье пожелание";
    private static final String BUTTON_BUTTON_SEND = "Отправить";
    private static final String BUTTON_YES_LABEL = "Да,удалить";
    private static final String BUTTON_NO_LABEL = "Нет,не удалять";
    private static final String BUTTON_FOR_YOURESELF = "Свой список пожеланий";
    private static final String BUTTON_FOR_ANOTHER = "Дарю другим";
    private static final String BUTTON_BACK_TO_START = "Назад, в главное меню";
    private static final String BUTTON_CHOOSE_WISH = "Выбрать пожелание";

    public static InlineKeyboardButton getKeyBoardBackToStart() {
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_BACK_TO_START, COMMANDS.BUTTON_BACK_TO_START.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardChooseWish() {
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_CHOOSE_WISH, COMMANDS.CHOOSE_WISH.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardYes(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_YES_LABEL, COMMANDS.YES.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardNO(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_NO_LABEL, COMMANDS.NO.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardButtonSend(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_BUTTON_SEND, COMMANDS.SEND.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardMoreDetails(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_MORE_DETAILS, COMMANDS.MORE_DETAILS.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardNameGiftOwner(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_NAME_GIFT_OWNER, COMMANDS.NAME_GIFT_OWNER.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardButtonInfo(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_INFO, COMMANDS.INFO.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardButtonGetWishList (){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_GET_WISHLIST, COMMANDS.WISHLIST.getCommand());
    }

    /*
    public static InlineKeyboardButton getKeyBoardButtonGiveToOthers (){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_GIVE_TO_OTHERS, COMMANDS.GIVE_TO_OTHERS.getCommand());
    } */

    public static InlineKeyboardButton getKeyBoardDeleteWish(){
        InlineKeyboardButton inlineKeyboardButtonDeleteWish = new InlineKeyboardButton();
        inlineKeyboardButtonDeleteWish.setText(BUTTON_DELETE);
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

    public static InlineKeyboardButton getKeyBoardButtonChangeStatusOwnWish(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_CHANGE_STATUS_OWN_WISH,COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardButtonChangeWish(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_CHANGE_WISH,COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand());
    }

    public static InlineKeyboardButton  getKeyBoardButtonBack(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_BACK, COMMANDS.BACK.getCommand());
    }

    public  static InlineKeyboardButton getKeyBoardButtonAddWish(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_ADD_WISH, COMMANDS.ADD_WISH.getCommand());
    }

    public  static InlineKeyboardButton getKeyBoardButtonAddMoreWish(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_ADD_MORE_WISH, COMMANDS.ADD_MORE_WISH.getCommand());
    }

    public  static InlineKeyboardButton getKeyBoardButtonAdd(){
        InlineKeyboardButton inlineKeyboardButtonButtonBack = new InlineKeyboardButton();
        inlineKeyboardButtonButtonBack.setText(BUTTON_ADD_NAME_USER_TO_DB);
        inlineKeyboardButtonButtonBack.setCallbackData(COMMANDS.ADD_NAME_USER_TO_DB.getCommand());
        return inlineKeyboardButtonButtonBack;
    }

    public static InlineKeyboardButton getKeyBoardButtonForYoureself(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_FOR_YOURESELF, COMMANDS.FOR_YOURESELF.getCommand());
    }

    public static InlineKeyboardButton getKeyBoardButtonForAnother(){
        return MakerInlineKeyboardMarkup.getKeyBoard(BUTTON_FOR_ANOTHER, COMMANDS.FOR_ANOTHER.getCommand());
    }

    public static InlineKeyboardMarkup getKeyBoardStartMenu(){
        return MakerInlineKeyboardMarkup.get2x2x3InlineKeyboardMarkup(getKeyBoardButtonInfo(),
                getKeyBoardButtonAddWish(), getKeyBoardButtonGetWishList());
    }
}
