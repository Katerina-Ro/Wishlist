package telegrambot.service.commandBot.receivers.utils;

import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.COMMANDS;

/**
 * Вспомогательный класс для проверки и получения команды
 */
public class CommandCheckUtil {
    /**
     * Получаем строку с командой после обрезки строки до первого пробела
     * @param update - сообщение, поступающее от бота
     * @return - срока, которая соответствует COMMANDS
     */
    public static String whichCommand(Update update) {
        String incomingMessage = update.getCallbackQuery().getData();

        System.out.println("whichCommand, String incomingMessage = " + incomingMessage);
        System.out.println("результат = " +incomingMessage.substring(0, (incomingMessage.indexOf(" "))));
        return incomingMessage.substring(0, (incomingMessage.indexOf(" ")));
    }

    /**
     * Сравниваем полученное от бота сообщение с COMMANDS.BUTTON_BACK_TO_START ("Назад, к главному меню")
     * @param update - сообщение, получаемое от бота
     * @return возвращает true, если полученное от бота сообщение (команда) соответствует типу Callback
     * COMMANDS.BUTTON_BACK_TO_START
     */
    public static boolean checkCommandCallBackEditBackToMainMenu(Update update) {
        boolean checkCommandCallBackEditBackToMainMenu = false;
        if(update.getCallbackQuery().getData().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START.getCommand()
                .toString())){
            checkCommandCallBackEditBackToMainMenu = true;
        }
        return checkCommandCallBackEditBackToMainMenu;
    }

    /**
     * Сравниваем полученное от бота сообщение с COMMANDS.BUTTON_BACK_TO_START ("Назад, к главному меню")
     * @param update - сообщение, получаемое от бота
     * @return возвращает true, если полученное от бота сообщение (команда) соответствует типу Reply
     * COMMANDS.BUTTON_BACK_TO_START
     */
    public static boolean checkCommandReplyBackToMainMenu(Update update) {
        boolean checkCommandReplyBackToMainMenu = false;
        if (update.getMessage().getReplyToMessage().getText().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START
                .getCommand().toString())) {
            checkCommandReplyBackToMainMenu = true;
        }
        return checkCommandReplyBackToMainMenu;
    }

    /**
     * Сравниваем полученное от бота сообщение с COMMANDS.CHANGE_WISH ("Изменить")
     * @param update - сообщение, получаемое от бота
     * @return возвращает true, если полученное от бота сообщение (команда) соответствует типу Callback
     * COMMANDS.CHANGE_WISH
     */
    public static boolean checkCommandCallBackEditChangeWish(Update update) {
        boolean checkCommandCallBackEditChangeWish = false;
        if(update.hasCallbackQuery()) {
            String command = FindingDataUtil.findLineByIncomingMessage(update.getCallbackQuery().getData());
            if (command.equalsIgnoreCase(COMMANDS.CHANGE_WISH.getCommand().toString())) {
                checkCommandCallBackEditChangeWish = true;
            }
        }
        return checkCommandCallBackEditChangeWish;
    }
}
