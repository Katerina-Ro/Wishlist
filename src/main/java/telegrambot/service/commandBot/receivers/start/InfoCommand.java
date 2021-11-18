package telegrambot.service.commandBot.receivers.start;

import lombok.Getter;
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
    private final String imageGreenBook = String.valueOf(Character.toChars(0x1F4D7));
    private final String imageWinkingFace = String.valueOf(Character.toChars(0x1F609));
    private final String imageMemo = String.valueOf(Character.toChars(0x1F4DD));
    private final String imageMagicWand = String.valueOf(Character.toChars(0x1FA84));

    private final String INFO_MESSAGE = "Что значат эти кнопки? " + imageGreenBook +
            "\nВсе, кто подключен к этому каналу, видят Ваши пожелания и смогут их исполнить, либо Вы сами " +
            imageWinkingFace + " можете исполнить чью-то мечту." +
            "\nНажимайте 'Добавить пожелание'" + imageMemo + ", чтобы сообщить свои желания боту, " +
            "а бот смог их показать другим." +
            "\nНажимайте 'Посмотреть список желаний', чтобы выбрать " + imageMagicWand
            + " чье-то желание либо просмотреть список своих желаний и изменить его.";

    public EditMessageText execute(Update update) {
        return new EditMessageText()
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setText(INFO_MESSAGE)
                .enableHtml(true)
                .setReplyMarkup(MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup(Buttons
                        .getKeyBoardButtonAddWish(), Buttons.getKeyBoardButtonGetWishList()));
    }
}
