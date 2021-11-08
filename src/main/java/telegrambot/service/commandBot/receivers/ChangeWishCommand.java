package telegrambot.service.commandBot.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.service_needdelete.SendBotMessageService;
import telegrambot.service.commandBot.utils.CommandUtils;
import telegrambot.service.commandBot.receivers.keyboard.KeyboardMenu;

public class ChangeWishCommand {
    private final SendBotMessageService sendBotMessageService;
    //private final TelegramUserService telegramUserService;
    private final String MESSAGE_CHANGE_WISH = "Вы хотите поменять свое пожелание или выбрать/ отказаться " +
            "от подарка другому человеку?";

    @Autowired
    public ChangeWishCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public SendMessage changeWish(Update update) {
        Long chatId = CommandUtils.getChatId(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(MESSAGE_CHANGE_WISH);
        sendMessage.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        //botConnect.execute(sendMessage);

        /*
        Появляется две кнопки: Внести изменения в свой подарок
        Внести изменения в чужой подарок
         */

        return sendMessage;
    }
}
