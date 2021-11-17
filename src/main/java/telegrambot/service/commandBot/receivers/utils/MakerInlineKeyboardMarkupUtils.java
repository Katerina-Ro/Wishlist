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
    public static InlineKeyboardMarkup get2RowsInlineKeyboardMarkup(List<Gift> listGifts){
        InlineKeyboardMarkup inlineKeyboardMarkup4 = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for(Gift g: listGifts){
            List<InlineKeyboardButton> keyboardButton1List = new ArrayList<>(); // 1 строка
            COMMANDS.NAME_GIFT.setCommand(g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getNameGift(),
                    COMMANDS.NAME_GIFT.getCommand()));
            COMMANDS.CHOOSE.setCommand("Выбран " + g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getStatusGiftAnother()
                            .getStatusGift(), COMMANDS.CHOOSE.getCommand()));
            keyboardButtons.add(keyboardButton1List);
        }
        keyboardButtons.add(MakerInlineKeyboardMarkup
                .makeListInlineKeyboardButton(Buttons.getKeyBoardButtonGetWishList()));
        inlineKeyboardMarkup4.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup4;
    }

    public static InlineKeyboardMarkup get4RowsInlineKeyboardMarkup(List<Gift> listGifts){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for(Gift g: listGifts){
            List<InlineKeyboardButton> keyboardButton1List = new ArrayList<>(); // 1 строка
            COMMANDS.NAME_GIFT.setCommand("Подробнее " + g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getNameGift(),
                    COMMANDS.NAME_GIFT.getCommand()));
            COMMANDS.STATE_DB.setCommand(g.getStatusGiftOwn() + " " + g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard(g.getStatusGiftOwn().getStatusGift(),
                    COMMANDS.STATE_DB.getCommand()));
            COMMANDS.CHANGE_WISH.setCommand("Изменить " + g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard("Изменить пожелание ",
                    COMMANDS.CHANGE_WISH.getCommand()));
            COMMANDS.DELETE.setCommand("Удалить " + g.getGiftId().toString());
            keyboardButton1List.add(MakerInlineKeyboardMarkup.getKeyBoard("Удалить пожелание ",
                    COMMANDS.DELETE.getCommand()));
            keyboardButtons.add(keyboardButton1List);
        }
        keyboardButtons.add(MakerInlineKeyboardMarkup
                .makeListInlineKeyboardButton(Buttons.getKeyBoardButtonBack()));
        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;
    }



    public static InlineKeyboardMarkup getYesNoDeleteInlineKeyboardMarkup(int iDGift){
        COMMANDS.YES.setCommand("Да,удалить " + iDGift);
        Buttons.getKeyBoardYes();
        return MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup(Buttons.getKeyBoardYes(),
                Buttons.getKeyBoardNO());
    }
}
