package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
@Service
@Getter
public class AddCommand implements Command{
    private final String MESSAGE_ADD = "Заполните поля";

    @Override
    public SendMessage execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtils.getChatId(update).toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(MESSAGE_ADD);
        sendMessage.setReplyMarkup(Buttons.getKeyBoardStartMenu());

        //botConnect.execute(sendMessage);
        return sendMessage;
    }
}
