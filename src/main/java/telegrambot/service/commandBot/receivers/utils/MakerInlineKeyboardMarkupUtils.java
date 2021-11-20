package telegrambot.service.commandBot.receivers.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

import java.util.ArrayList;
import java.util.List;

public class MakerInlineKeyboardMarkupUtils {
    public static InlineKeyboardMarkup get3RowsInlineKeyboardMarkup(List<Gift> listGifts){
        InlineKeyboardMarkup inlineKeyboardMarkup4 = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for(Gift g: listGifts){
            List<InlineKeyboardButton> keyboardButton1List = new ArrayList<>(); // 1 строка
            COMMANDS.NAME_GIFT_OWNER.getCommand().append(" ").append(g.getGiftOwner().getChatId());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getGiftOwner().getName(),
            COMMANDS.NAME_GIFT_OWNER.getCommand()));
            COMMANDS.NAME_GIFT.getCommand().append(" ").append(g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getNameGift(),
                    COMMANDS.NAME_GIFT.getCommand()));
            COMMANDS.CHOOSE.getCommand().append(" ").append(g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getStatusGiftAnother()
                            .getStatusGift(), COMMANDS.CHOOSE.getCommand()));
            keyboardButtons.add(keyboardButton1List);
        }
        keyboardButtons.add(MakerInlineKeyboardMarkup
                .makeListInlineKeyboardButton(Buttons.getKeyBoardButtonGetWishList()));
        inlineKeyboardMarkup4.setKeyboard(keyboardButtons);
        //returnValue();
        return inlineKeyboardMarkup4;
    }

    public static InlineKeyboardMarkup get4RowsInlineKeyboardMarkup(List<Gift> listGifts){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for(Gift g: listGifts){
            List<InlineKeyboardButton> keyboardButton1List = new ArrayList<>(); // 1 строка

            COMMANDS.NAME_GIFT.getCommand().append(" ").append(g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getNameGift(),
                    COMMANDS.NAME_GIFT.getCommand()));
            COMMANDS.STATE_DB.getCommand().append(g.getStatusGiftOwn()).append(" ").append(g
                    .getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getStatusGiftOwn().getStatusGift(),
                    COMMANDS.STATE_DB.getCommand()));

            COMMANDS.CHANGE_WISH.getCommand().append(" ").append(g.getGiftId().toString());

            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard("Изменить пожелание ",
                    COMMANDS.CHANGE_WISH.getCommand()));

            COMMANDS.DELETE.getCommand().append(" ").append(g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard("Удалить пожелание ",
                    COMMANDS.DELETE.getCommand()));
            keyboardButtons.add(keyboardButton1List);
        }
        keyboardButtons.add(MakerInlineKeyboardMarkup
                .makeListInlineKeyboardButton(Buttons.getKeyBoardButtonBack()));
        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        //returnValue();
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getYesNoDeleteInlineKeyboardMarkup(int iDGift){
        COMMANDS.YES.getCommand().append(" ").append(iDGift);
        //Buttons.getKeyBoardYes();
        return MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup(Buttons.getKeyBoardYes(),
                Buttons.getKeyBoardNO());
    }

    public static InlineKeyboardMarkup getChangeStatusGiftOwnInlineKeyboardMarkup(int iDGift){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand().append(" ").append(iDGift);
               // ("Поменять " + iDGift);
        System.out.println("COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand()" + COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand());
        //Buttons.getKeyBoardButtonChangeStatusOwnWish();
        return MakerInlineKeyboardMarkup.get2x2InlineKeyboardMarkup(Buttons.getKeyBoardButtonChangeStatusOwnWish(),
                Buttons.getKeyBoardButtonAddWish(), Buttons.getKeyBoardButtonForYoureself(),
                Buttons.getKeyBoardBackToStart());
    }
/*
    private static void returnValue(){
        COMMANDS.YES.getCommand.substring("Да,удалить");
        COMMANDS.CHANGE_WISH.setCommand("Изменить");
        COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand().substring(9);
        COMMANDS.DELETE.setCommand("Удалить");
        COMMANDS.NAME_GIFT.setCommand("/ ");
        COMMANDS.STATE_DB.setCommand("ACTIVE");
        COMMANDS.CHANGE_WISH.setCommand("Изменить");
        COMMANDS.NAME_GIFT_OWNER.setCommand("Имя");
        COMMANDS.CHOOSE.setCommand("Выбрать");
    } */

}
