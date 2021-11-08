package telegrambot.service.commandBot.receivers.keyboard;

import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class MakerInlineKeyboardMarkup {

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


    public InlineKeyboardMarkup get2x1InlineKeyboardMarkup(InlineKeyboardButton keyboardButtonRow1,
                                                        InlineKeyboardButton keyboardButtonRow2) {
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow1, keyboardButtonRow2));
        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow1, keyboardButtonRow2));

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);

        return inlineKeyboardMarkup;
    }

    /**
     * 2 строки в 2 ряда
     * @param keyboardButtonRow1 - 1 (верхняя) кнопка 1 ряд
     * @param keyboardButtonRow2 - 1 (верхняя) кнопка 2 ряд
     * @return
     */
    public InlineKeyboardMarkup get2x2InlineKeyboardMarkup(InlineKeyboardButton keyboardButtonRow1,
                                                        InlineKeyboardButton keyboardButtonRow2,
                                                        InlineKeyboardButton keyboardButtonRow3,
                                                        InlineKeyboardButton keyboardButtonRow4) {
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow1, keyboardButtonRow2));
        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow3, keyboardButtonRow4));



        inlineKeyboardMarkup.setKeyboard(keyboardButtons);

        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> makeListInlineKeyboardButton (InlineKeyboardButton keyboardButton1,
                                                                     InlineKeyboardButton keyboardButton2){
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        inlineKeyboardButtons.add(keyboardButton1);
        inlineKeyboardButtons.add(keyboardButton2);

        return  inlineKeyboardButtons;
    }

}
