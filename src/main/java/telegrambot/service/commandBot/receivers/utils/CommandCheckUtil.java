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
        System.out.println("incomingMessage  = "+ incomingMessage);
        System.out.println("это выдает обрезка строки = " + incomingMessage.substring(0,
                (incomingMessage.indexOf(" "))));
        return incomingMessage.substring(0, (incomingMessage.indexOf(" ")));
    }

    /**
     * Сравниваем полученное от бота сообщение с COMMANDS.BUTTON_BACK_TO_START ("Назад, к главному меню")
     * @param update - сообщение, получаемое от бота
     * @return возвращает true, если полученное от бота сообщение (команда) соответствует типу Callback
     * COMMANDS.BUTTON_BACK_TO_START
     */
    public static boolean checkCommandCallBackEditBackToMainMenu(Update update) {

        System.out.println("внутри метода checkCommandCallBackEditBackToMainMenu");

        boolean checkCommandCallBackEditBackToMainMenu = false;


        System.out.println("сейчас начнется проверка update.hasCallbackQuery() && update.getCallbackQuery().getData().equalsIgnoreCase(C");
        if(update.getCallbackQuery().getData().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START.getCommand())){
            System.out.println("внутри if(update.getCallbackQuery().getData().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START.getCommand()))");
            checkCommandCallBackEditBackToMainMenu = true;
        }
        System.out.println("checkCommandBackToMainMenu = " + checkCommandCallBackEditBackToMainMenu);
        System.out.println();
        System.out.println("вышел из метода checkCommandCallBackEditBackToMainMenu");
        System.out.println();
        return checkCommandCallBackEditBackToMainMenu;
    }

    public static boolean checkCommandCallBackChangeWishStatusOwn(Update update) {
        System.out.println();
        System.out.println("внутри метода checkCommandCallBackChangeWishStatusOwn");

        boolean checkCommandCallBackChangeWishStatusOwn = false;

        System.out.println("сейчас начнется проверка update.getCallbackQuery().getData().equalsIgnoreCase(COMMANDS.CHANGE_STATUS_OWN_WISH.getCom");
        System.out.println("update.getCallbackQuery().getData() " + update.getCallbackQuery().getData());
        if (update.getCallbackQuery().getData().equalsIgnoreCase(COMMANDS.CHANGE_STATUS_OWN_WISH.getCommand())) {

            System.out.println("внутри update.getMessage().getReplyToMessage().getText().equalsIgnoreCase(COMMANDS\n" +
                    "                .BUTTON_BACK_TO_START.getCommand())");


            checkCommandCallBackChangeWishStatusOwn = true;
        }
        System.out.println();
        System.out.println("checkCommandBackToMainMenu = " + checkCommandCallBackChangeWishStatusOwn);
        System.out.println();
        System.out.println("вышел из метода checkCommandCallBackChangeWishStatusOwn");
        System.out.println();
        return checkCommandCallBackChangeWishStatusOwn;
    }

    /**
     * Сравниваем полученное от бота сообщение с COMMANDS.BUTTON_BACK_TO_START ("Назад, к главному меню")
     * @param update - сообщение, получаемое от бота
     * @return возвращает true, если полученное от бота сообщение (команда) соответствует типу Reply
     * COMMANDS.BUTTON_BACK_TO_START
     */
    public static boolean checkCommandReplyBackToMainMenu(Update update) {
        System.out.println();
        System.out.println("внутри метода checkCommandReplyBackToMainMenu");

        boolean checkCommandReplyBackToMainMenu = false;

        System.out.println("сейчас начнется проверка update.getMessage().isReply() update.getMessage().getReplyToMessage().getText().equalsIgnoreCase(COMMANDS\n" +
                "                .BUTTON_BACK_TO_START.getCommand())");

        System.out.println("update.getMessage().getReplyToMessage()\n" +
                "                .getText().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START.getCommand()) = " + update.getMessage().getReplyToMessage()
                .getText().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START.getCommand()));

        if (update.getMessage().getReplyToMessage().getText().equalsIgnoreCase(COMMANDS.BUTTON_BACK_TO_START.getCommand())) {

            System.out.println("внутри update.getMessage().getReplyToMessage().getText().equalsIgnoreCase(COMMANDS\n" +
                    "                .BUTTON_BACK_TO_START.getCommand())");


            checkCommandReplyBackToMainMenu = true;
        }
        System.out.println();
        System.out.println("checkCommandReplyBackToMainMenu = " + checkCommandReplyBackToMainMenu);
        System.out.println();
        System.out.println("вышел из метода checkCommandReplyBackToMainMenu");
        return checkCommandReplyBackToMainMenu;
    }
}
