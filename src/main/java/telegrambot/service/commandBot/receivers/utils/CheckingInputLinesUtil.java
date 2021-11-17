package telegrambot.service.commandBot.receivers.utils;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Вспомогательный класс для проверки введенных пользователем строк на пустоту, null, символы
 */
public class CheckingInputLinesUtil {
    /**
     * Проверяем, введена ли строка и нет ли пробелов
     * @param string - строка, введенная пользователем
     * @return возвращаеся true, если введена строка и нет пробелов
     */
    public static boolean checkEmptyString(String string) {
        return string !=null && !string.trim().isEmpty();
    }

    /**
     * Проверяем, не содержит ли строка символы
     * @param line - строка, введенная пользователем
     * @return возвращаеся true, если строка состоит из букв английского или русского алфавита
     */
    public static boolean isLetters(String line) {
        return line.matches("[а-яА-Я]+") || line.matches("[a-zA-Z]+");
    }

    /**
     * Получаем строку с командой после обрезки строки до первого пробела
     * @param update - сообщение, поступающее от бота
     * @return - срока, которая соответствует COMMANDS
     */
    public static String whichCommand(Update update) {
        String incomingMessage = update.getCallbackQuery().getData();
        System.out.println("incomingMessage  = "+ incomingMessage);
        System.out.println("это выдает обрезка строки = " + incomingMessage.substring(0, (incomingMessage.indexOf(" "))));
        return incomingMessage.substring(0, (incomingMessage.indexOf(" ")));
    }
}
