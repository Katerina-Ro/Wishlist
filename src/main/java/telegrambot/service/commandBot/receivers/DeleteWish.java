package telegrambot.service.commandBot.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.BotConnect;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.utils.CommandUtils;
import telegrambot.service.commandBot.receivers.keyboard.buttons.ButtonYes;
import telegrambot.service.commandBot.receivers.keyboard.MakerInlineKeyboardMarkup;

public class DeleteWish implements Command {
    private final BotConnect botConnect;
    private final String INFO_DELETE_MESSAGE = "Удалить подарок из списка насовсем." +
            "Выберите пожелание, которое хотите удалить:";
    private boolean chooseField = false;

    private MakerInlineKeyboardMarkup makerInlineKeyboardMarkup;

    @Autowired
    public DeleteWish(BotConnect botConnect) {
        this.botConnect = botConnect;
    }

    @Autowired
    public void setMakerInlineKeyboardMarkup(MakerInlineKeyboardMarkup makerInlineKeyboardMarkup) {
        this.makerInlineKeyboardMarkup = makerInlineKeyboardMarkup;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException {
        boolean actionStateYes = false;
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(INFO_DELETE_MESSAGE);

        // получение списка подарка из базы по чат-ид (только название)
        // предполагается 2 поля: "Выбор" и второе поле в этой же строке, но во втором ряду, - строка из списка
        // подарков, который будет подгружен из БД. Пользователю нужно будет выбрать нужный поарок.
        // Подгружаться будет только список подарков самого пользователя.
        // Возможно сделаю по-другому: 1 столбец, видимый пользователю, - это список его подарков.
        // Пользователь выбтрает подарок для удаления нажатием на строку с подарком.
        // При активации кнопки выбора подарка (1 или второй вариант), будет активироваться кнопка "Удалить"

        if()
        System.out.println();
        sendMessage.setReplyMarkup(makerInlineKeyboardMarkup.get2x2InlineKeyboardMarkup(ButtonYes.getKeyBoardYes(),
                ButtonYes.getKeyBoardYes(), ButtonYes.getKeyBoardYes(), ButtonYes.getKeyBoardYes()));

        //botConnect.execute(sendMessage);
        return sendMessage;
    }
}
