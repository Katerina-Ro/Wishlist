package telegrambot.service.commandBot.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.BotConnect;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.keyboard.buttons.Buttons;
import telegrambot.service.commandBot.receivers.keyboard.buttons.ListButtonsNameGift;
import telegrambot.service.commandBot.receivers.keyboard.buttons.ListButtonsState;
import telegrambot.service.commandBot.receivers.utils.CommandUtils;
import telegrambot.service.commandBot.receivers.keyboard.MakerInlineKeyboardMarkup;

import java.sql.SQLException;

@Service
public class DeleteWish implements Command {
    private final BotConnect botConnect;
    private final String INFO_DELETE_MESSAGE = "Удалить подарок из списка насовсем." +
            "Выберите пожелание, которое хотите удалить:";
    private boolean chooseField = false;

    private ListButtonsNameGift listButtonsNameGift;
    private ListButtonsState listButtonsState;

    @Autowired
    public DeleteWish(BotConnect botConnect) {
        this.botConnect = botConnect;
    }

    @Autowired
    public void setListButtonsNameGift(ListButtonsNameGift listButtonsNameGift) {
        this.listButtonsNameGift = listButtonsNameGift;
    }

    @Autowired
    public void setListButtonsState(ListButtonsState listButtonsState) {
        this.listButtonsState = listButtonsState;
    }

    @Override
    public SendMessage execute(Update update) throws TelegramApiException, SQLException {
        boolean actionStateYes = false;
        Long chatId = CommandUtils.getChatId(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(INFO_DELETE_MESSAGE);

        System.out.println();
        sendMessage.setReplyMarkup(MakerInlineKeyboardMarkup.get2x2x3InlineKeyboardMarkup
                (listButtonsNameGift.getKeyBoardListButtonsNameGift(update),
                        listButtonsNameGift.getKeyBoardListButtonsNameGift(update),
                        Buttons.getKeyBoardButtonBack()));





        //botConnect.execute(sendMessage);
        return sendMessage;
    }
}
