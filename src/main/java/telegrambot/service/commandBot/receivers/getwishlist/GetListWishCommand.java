package telegrambot.service.commandBot.receivers.getwishlist;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

/**
 * Класс-Receiver команды "Посмотреть список желаний" {@link CommandEditSendMessage}
 */
@Service
public class GetListWishCommand implements CommandEditSendMessage {
    @Override
    @Transactional
    public EditMessageText execute(Update update) {
       String message = "Вы хотите получить свой список подарков или для другого " +
                "человека?";
        return SendMessageUtils.sendEditMessage(update,message, MakerInlineKeyboardMarkup.get2x2InlineKeyboardMarkup
                (Buttons.getKeyBoardButtonForYoureself(),Buttons.getKeyBoardButtonForAnother(),
                        Buttons.getKeyBoardChooseWish(), Buttons.getKeyBoardBackToStart()));
    }
}



