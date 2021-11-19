package telegrambot.service.commandBot.receivers.utils;

/**
 * Вспомогательный класс для получения данных из сообщения типа String
 */
public class FindingDataUtil {

    /**
     * Получаем подстроку из сообщения от начала доп первого пробела
     * @param incomingMessage - пришедшее от бота сообщение
     * @return подстрока типа String
     */
    public static String findLineByIncomingMessage(String incomingMessage){
        return incomingMessage.substring(0,(incomingMessage.indexOf(" ")));
    }

    /**
     * Получаем значение типа int из сообщения (подстрока от пробела)
     * @param incomingMessage - пришедшее от бота сообщение
     * @return значение типа int
     */
    public static int findIdByIncomingMessage(String incomingMessage){
        return Integer.parseInt(incomingMessage.substring((incomingMessage.indexOf(" "))+1));
    }
}
