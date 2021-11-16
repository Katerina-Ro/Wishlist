package telegrambot.service.commandBot.receivers.changewishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.BotConnect;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.keyboard.buttons.ListButtonsNameGift;

@Service
public class DeleteWish implements Command {
    private final BotConnect botConnect;
    private final String INFO_DELETE_MESSAGE = "Удалить подарок из списка насовсем." +
            "Выберите пожелание, которое хотите удалить:";
    private boolean chooseField = false;

    private ListButtonsNameGift listButtonsNameGift;


    @Autowired
    public DeleteWish(BotConnect botConnect) {
        this.botConnect = botConnect;
    }

    @Autowired
    public void setListButtonsNameGift(ListButtonsNameGift listButtonsNameGift) {
        this.listButtonsNameGift = listButtonsNameGift;
    }


    @Override
    public SendMessage execute(Update update) {
        return null;
    }
}
