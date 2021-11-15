package telegrambot.service.commandBot.receivers.start;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

/**
 * Класс-Receiver команды "/info" {@link Command}
 */
@Service
@Getter
public class InfoCommand implements CommandEditSendMessage {
    private final Buttons buttons;
    private final String imageGreenBook = String.valueOf(Character.toChars(0x1F4D7));
    private final String imageWinkingFace = String.valueOf(Character.toChars(0x1F609));
    private final String imageMemo = String.valueOf(Character.toChars(0x1F4DD));
    private final String imageMagicWand = String.valueOf(Character.toChars(0x1FA84));

    private final String INFO_MESSAGE = "Что значат эти кнопки? " + imageGreenBook +
            "\nВсе, кто подключен к этому каналу, видят твия пожелания и смогут их исполнить, либо ты сам " +
            imageWinkingFace + " можешь исполнить чью-то мечту." +
            "\nНажимай " + "<b>" + "'Добавить пожелание'" + "</b>" + imageMemo + ", чтобы другие" +
            " увидели твои желания." +
            "\nНажимай " + "<b>" + "'Посмотреть список желаний'" + "</b>" + ", чтобы выбрать " + imageMagicWand
            + " чье-то желание либо посмотреть список своих желаний." +
            "\nНажимай " + "<b>" + "'Изменить желание'" + "</b>" + ", если хочешь внести в него корректировки " +
            "либо удалить";

    @Autowired
    public InfoCommand(Buttons buttons) {
        this.buttons = buttons;
    }

    public EditMessageText execute(Update update) {
        return new EditMessageText()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(INFO_MESSAGE)
                .setReplyMarkup(MakerInlineKeyboardMarkup.get2x2x3InlineKeyboardMarkup(
                buttons.getKeyBoardButtonAddWish(), buttons.getKeyBoardButtonGetWishList(),
                buttons.getKeyBoardButtonChangeWish()));
    }
}
