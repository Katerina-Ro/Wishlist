package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.TelegramUserService;
import telegrambot.service.commandBot.receivers.keyboard.MakerInlineKeyboardMarkup;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.ListButtonsNameGift;
import telegrambot.service.commandBot.receivers.keyboard.buttons.ListButtonsState;

/**
 * Класс-Receiver команды "/addWish" {@link Command}
 */
@Service
@Getter
public class AddCommand implements Command{
    private static final String IMAGE_EIGHT_SPOKED_ASTERISK = String.valueOf(Character.toChars(0x2733));
    private static final String EMPTY_LINE = " \n";
    private static final String NAME_USER = "<u>" + "'Твое имя'" + "</u> " + IMAGE_EIGHT_SPOKED_ASTERISK + "\n " +
            "(нужно, чтобы тот, " +
            "кто захочет сделать тебе подарок, смог идентифицировать тебя)";
    private static final String MESSAGE_ADD = "Заполни поля \nПоля, отмеченные " + IMAGE_EIGHT_SPOKED_ASTERISK +
            ", обязательны для заполнения. \n" + EMPTY_LINE + NAME_USER;
    private static final String HEAVY_EXCLAMATION_MARK_SYMBOL = String.valueOf(Character.toChars(0x2757));
    private static final String INPUT_ERROR_MESSAGE = HEAVY_EXCLAMATION_MARK_SYMBOL +
            "Имя должно быть текстовым ";
    private static final String NAME_WISH = "<u>" + "'Наименование подарка'" + "</u> " +
            IMAGE_EIGHT_SPOKED_ASTERISK;
    private final Buttons buttons;
    private final ListButtonsState listButtonsState;
    private final ListButtonsNameGift listButtonsNameGift;
    private final TelegramUserService telegramUserService;

    @Autowired
    public AddCommand(Buttons buttons, ListButtonsState listButtonsState,
                      ListButtonsNameGift listButtonsNameGift, TelegramUserService telegramUserService) {
        this.buttons = buttons;
        this.listButtonsState = listButtonsState;
        this.listButtonsNameGift = listButtonsNameGift;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public SendMessage execute(long chat_id) throws TelegramApiException {
        SendMessage messageAddCommand = new SendMessage()
                .setChatId(chat_id)
                .setText(MESSAGE_ADD);
        messageAddCommand.enableHtml(true);
        messageAddCommand.setReplyMarkup(MakerInlineKeyboardMarkup.makeListReplyKeyboardButtonAfterInlineButton());
        return messageAddCommand;
    }

    @Transactional
    public SendMessage insertNameWish(Update update) {
        SendMessage messageAddCommand = new SendMessage();
        if(checkEmptyString(update.getCallbackQuery().getData())) {
        /* Math.toIntExact() Новый метод был добавлен с Java 8 для безопасного приведения к int.
           Выкинет ArithmeticExceptionв случае переполнения.
         */
            Integer chatIdCallBack = Math.toIntExact(update.getCallbackQuery().getMessage().getChatId());

            /*заносим пользователя в базу данных и отправляем ему новое поле для заполнения.
             Если какая-либо из операций не будет выполнена корректно, то @Transactional не даст внести
             изменения и откатит операцию назад
             */
            telegramUserService.createGiftOwner(update.getCallbackQuery().getData(), chatIdCallBack);
            messageAddCommand.setChatId(String.valueOf(chatIdCallBack))
                              .setText(NAME_WISH);
            messageAddCommand.enableHtml(true);
            messageAddCommand.setReplyMarkup(MakerInlineKeyboardMarkup.makeListReplyKeyboardButtonAfterInlineButton());
        } else {
            messageError(update);
        }
        return messageAddCommand;
    }

    public EditMessageText executEditMessageText (long chat_id, long message_id) {
        EditMessageText editMessageText = new EditMessageText()
                .setChatId(chat_id)
                .setMessageId((int) message_id)
                .setText(MESSAGE_ADD);
        editMessageText.enableHtml(true);
        return editMessageText;
    }

    public boolean checkEmptyString(String string) {
        return string !=null && string.trim().isEmpty();
    }

    public SendMessage messageError(Update update){
        SendMessage messageAddCommandError = new SendMessage()
                .setChatId(String.valueOf(update.getCallbackQuery()))
                .setText(INPUT_ERROR_MESSAGE);
        messageAddCommandError.enableHtml(true);
        messageAddCommandError.setReplyMarkup(MakerInlineKeyboardMarkup.makeListReplyKeyboardButtonAfterInlineButton());
        return messageAddCommandError;
    }
}
