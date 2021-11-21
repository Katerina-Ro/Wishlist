package telegrambot.service.commandBot.receivers.start;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.CommandEditSendMessage;
import telegrambot.service.commandBot.receivers.utils.SendMessageUtils;
import telegrambot.service.commandBot.receivers.utils.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.Buttons;

/**
 * Класс-Receiver команды "/info" {@link CommandEditSendMessage}
 */
@Service
public class InfoCommand implements CommandEditSendMessage {
    private static final String IMAGE_GREEN_BOOK = String.valueOf(Character.toChars(0x1F4D7));
    private static final String IMAGE_WINKING_FACE = String.valueOf(Character.toChars(0x1F609));
    private static final String IMAGE_MEMO = String.valueOf(Character.toChars(0x1F4DD));
    private static final String IMAGE_MAGIC_WAND = String.valueOf(Character.toChars(0x1FA84));

    private final String INFO_MESSAGE = "Что значат эти кнопки? " + IMAGE_GREEN_BOOK +
            "\nВсе, кто подключен к этому каналу, видят Ваши пожелания и смогут их исполнить, либо Вы сами " +
            IMAGE_WINKING_FACE + " можете исполнить чью-то мечту." +
            "\nНажимайте 'Добавить пожелание'" + IMAGE_MEMO + ", чтобы сообщить свои желания боту, " +
            "а бот смог их показать другим." +
            "\nНажимайте 'Посмотреть список желаний', чтобы выбрать " + IMAGE_MAGIC_WAND
            + " чье-то желание либо просмотреть список своих желаний и изменить его.";

    @Override
    public EditMessageText execute(Update update) {
        return SendMessageUtils.sendEditMessage(update, INFO_MESSAGE,MakerInlineKeyboardMarkup.get2x1InlineKeyboardMarkup(Buttons
                .getKeyBoardButtonAddWish(), Buttons.getKeyBoardButtonGetWishList()));
    }
}
