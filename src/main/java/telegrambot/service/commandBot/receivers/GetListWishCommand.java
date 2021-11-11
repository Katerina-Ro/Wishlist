package telegrambot.service.commandBot.receivers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

@Service
public class GetListWishCommand implements Command {
    private final String MESSAGE_GET_LIST_WISH = "Вы хотите получить свой список подарков или для другого " +
            "человека?";

    @Override
    public SendMessage execute(long chat_id) throws TelegramApiException {
        return null;
    }






        /* тут кнопки
        Выдает 5 столбец построчно (каждое ФИ как отдельная кнопка для выбора либо выделить ФИ)
        Здесть кнокпи: Выбрать , Назад
         */



}
