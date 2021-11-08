package telegrambot.service.commandBot.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.service_needdelete.SendBotMessageService;
import telegrambot.service.commandBot.utils.CommandUtils;
import telegrambot.service.commandBot.receivers.keyboard.KeyboardMenu;

public class GetListWishCommand {
    private final SendBotMessageService sendBotMessageService;
    //private final TelegramUserService telegramUserService;
    private final String MESSAGE_GET_LIST_WISH = "Вы хотите получить свой список подарков или для другого человека?";

    @Autowired
    public GetListWishCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public SendMessage getWishList(Update update) {
        Long chatId = CommandUtils.getChatId(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(MESSAGE_GET_LIST_WISH);
        sendMessage.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        //botConnect.execute(sendMessage);

        /* тут кнопки
        Выдает 5 столбец построчно (каждое ФИ как отдельная кнопка для выбора либо выделить ФИ)
        Здесть кнокпи: Выбрать , Назад
         */

        return sendMessage;
    }

}
