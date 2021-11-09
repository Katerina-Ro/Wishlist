package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

/**
 * Класс-Receiver команды "/changeWish" {@link Command}
 */
@Service
@Getter
public class ChangeWishCommand implements Command {
    private final String MESSAGE_CHANGE_WISH = "Вы хотите поменять свое пожелание или выбрать/ отказаться " +
            "от подарка другому человеку?";

    @Override
    public SendMessage execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtils.getChatId(update).toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(MESSAGE_CHANGE_WISH);
        sendMessage.setReplyMarkup(Buttons.getKeyBoardStartMenu());
        //botConnect.execute(sendMessage);

        /*
        Появляется две кнопки: Внести изменения в свой подарок
        Внести изменения в чужой подарок
         */

        return sendMessage;
    }
}
