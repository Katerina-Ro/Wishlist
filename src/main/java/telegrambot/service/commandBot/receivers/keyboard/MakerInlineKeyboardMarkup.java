package telegrambot.service.commandBot.receivers.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Вспомогательный класс для формирования клавиатуры типа InlineKeyboardMarkup
 */
public class MakerInlineKeyboardMarkup {
    /** Получаем клавиатуру на 2 ряда, 1 строку
     * @param keyboardButtonRow1 - 1 ряд
     * @param keyboardButtonRow2 - 2 ряд
     * @return - клавиатуру
     */
    public static InlineKeyboardMarkup get2x1InlineKeyboardMarkup(InlineKeyboardButton keyboardButtonRow1,
                                                           InlineKeyboardButton keyboardButtonRow2) {
        return getInlineKeyboardMarkup(keyboardButtonRow1, keyboardButtonRow2,
                makeListInlineKeyboardButton(keyboardButtonRow1, keyboardButtonRow2));
    }

    private static InlineKeyboardMarkup getInlineKeyboardMarkup(InlineKeyboardButton keyboardButtonRow1,
                                                                InlineKeyboardButton keyboardButtonRow2,
                                                                List<InlineKeyboardButton> inlineKeyboardButtons) {
        InlineKeyboardMarkup inlineKeyboardMarkup1 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow1, keyboardButtonRow2));
        keyboardButtons.add(inlineKeyboardButtons);
        inlineKeyboardMarkup1.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup1;
    }

    /**
     * Получаем клавиатуру на 2 строки в 2 ряда
     * @param keyboardButtonRow1 - 1 (верхняя) кнопка 1 ряд
     * @param keyboardButtonRow2 - 1 (верхняя) кнопка 2 ряд
     * @return - клавиатура
     */
    public static InlineKeyboardMarkup get2x2InlineKeyboardMarkup(InlineKeyboardButton keyboardButtonRow1,
                                                           InlineKeyboardButton keyboardButtonRow2,
                                                           InlineKeyboardButton keyboardButtonRow3,
                                                           InlineKeyboardButton keyboardButtonRow4) {
        return getInlineKeyboardMarkup(keyboardButtonRow1, keyboardButtonRow2,
                makeListInlineKeyboardButton(keyboardButtonRow3, keyboardButtonRow4));
    }

    /**
     * Клавиатура на кнопки 3х типов
     * @param keyboardButtonRow1 - кнопка 1 тип 1 ряд
     * @param keyboardButtonRow2 - кнопка 2 тип 2 ряд
     * @param keyboardButtonRow3 - кнопка 3 тип 1 ряд
     * @return - клавиатура в 2 ряда, кнопки 3х типов
     */
    public static InlineKeyboardMarkup get2x2x3InlineKeyboardMarkup(InlineKeyboardButton keyboardButtonRow1,
                                                           InlineKeyboardButton keyboardButtonRow2,
                                                           InlineKeyboardButton keyboardButtonRow3){
        InlineKeyboardMarkup inlineKeyboardMarkup3 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow1, keyboardButtonRow2));
        keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow3));
        inlineKeyboardMarkup3.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup3;
    }

    /**
     * Получаем список кнопок - наименований подарков, подгружаемый из БД, и кнопки, отражающие состояние
     * подарков ("Активно"/ "Неактивно")
     * @param keyboardButtonRow1 - ряд кнопок, отражающих состояние ("Активно"/ "Неактивно")
     * @param keyboardButtonRow2 - ряд кнопок, подгружаемый из БД
     * @return возвращается клавиатура кнопок, размер которой зависит от количества подарков
     */
    public static InlineKeyboardMarkup get2x2InlineKeyboardMarkup(Collection<InlineKeyboardButton>
                                                                          keyboardButtonRow1,
                                                           Collection<InlineKeyboardButton> keyboardButtonRow2){
        InlineKeyboardMarkup inlineKeyboardMarkup4 = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for(InlineKeyboardButton buttonRow1: keyboardButtonRow1){
            for(InlineKeyboardButton buttonRow2: keyboardButtonRow2) {
                keyboardButtons.add(makeListInlineKeyboardButton(buttonRow1, buttonRow2));
            }
        }
        inlineKeyboardMarkup4.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup4;
    }

    /**
     * Клавиатура на кнопки 3х типов
     * @param keyboardButtonRow1 - список кнопкок 1 типа 1 ряд
     * @param keyboardButtonRow2 - список кнопок 2 типа 2 ряд
     * @param keyboardButtonRow3 - кнопка 3 тип 1 ряд
     * @return - клавиатура в 2 ряда, кнопки 3х типов
     */
    public static InlineKeyboardMarkup get2x2x3InlineKeyboardMarkup(Collection<InlineKeyboardButton>
                                                                            keyboardButtonRow1,
                                                           Collection<InlineKeyboardButton> keyboardButtonRow2,
                                                             InlineKeyboardButton keyboardButtonRow3) {
        InlineKeyboardMarkup inlineKeyboardMarkup5 = new InlineKeyboardMarkup();
        List <List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for(InlineKeyboardButton buttonRow1: keyboardButtonRow1){
            for(InlineKeyboardButton buttonRow2: keyboardButtonRow2) {
                keyboardButtons.add(makeListInlineKeyboardButton(buttonRow1, buttonRow2));
                keyboardButtons.add(makeListInlineKeyboardButton(keyboardButtonRow3));
            }
        }
        inlineKeyboardMarkup5.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup5;
    }

    /**
     * Вспомогательный метод для получаения списка кнопок типа InlineKeyboardButton с 2 элементами
     * @param keyboardButton1 - кнопка 1
     * @param keyboardButton2 - кнопка 2
     * @return список кнопок с 2 элементами
     */
    private static List<InlineKeyboardButton> makeListInlineKeyboardButton (InlineKeyboardButton keyboardButton1,
                                                                     InlineKeyboardButton keyboardButton2){
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        inlineKeyboardButtons.add(keyboardButton1);
        inlineKeyboardButtons.add(keyboardButton2);
        return  inlineKeyboardButtons;
    }

    /**
     * Вспомогательный метод для получаения списка кнопок типа InlineKeyboardButton с одним элементом
     * @param keyboardButton - кнопка 1
     * @return - список кнопок с 1 элементом
     */
    private static List<InlineKeyboardButton> makeListInlineKeyboardButton (InlineKeyboardButton keyboardButton){
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        inlineKeyboardButtons.add(keyboardButton);
        return  inlineKeyboardButtons;
    }
}
