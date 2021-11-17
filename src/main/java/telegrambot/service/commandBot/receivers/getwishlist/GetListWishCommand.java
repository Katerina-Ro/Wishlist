package telegrambot.service.commandBot.receivers.getwishlist;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

@Service
@Getter
public class GetListWishCommand implements CommandEditSendMessage {
    private final String MESSAGE_GET_LIST_WISH = "Вы хотите получить свой список подарков или для другого " +
            "человека?";

    @Override
    @Transactional
    public EditMessageText execute(Update update) {
        return new EditMessageText()
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(MESSAGE_GET_LIST_WISH)
                .setChatId( update.getCallbackQuery().getMessage().getChatId())
                .setReplyMarkup(MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup
                (Buttons.getKeyBoardButtonForYoureself(),Buttons.getKeyBoardButtonForAnother()));
    }
}



