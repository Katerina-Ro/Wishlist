package telegrambot.service.commandBot.receivers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

@Service
public class GetListWishCommand implements Command {
    private final String MESSAGE_GET_LIST_WISH = "Вы хотите получить свой список подарков или для другого " +
            "человека?";

    @Override
    public SendMessage execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtils.getChatId(update).toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(MESSAGE_GET_LIST_WISH);
        sendMessage.setReplyMarkup(Buttons.getKeyBoardStartMenu());
        //botConnect.execute(sendMessage);

        /* тут кнопки
        Выдает 5 столбец построчно (каждое ФИ как отдельная кнопка для выбора либо выделить ФИ)
        Здесть кнокпи: Выбрать , Назад
         */

        return sendMessage;
    }
}
