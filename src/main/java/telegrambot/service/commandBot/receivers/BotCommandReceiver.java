package telegrambot.service.commandBot.receivers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.service.utils.COMMANDS;
import telegrambot.service.utils.KeyboardMenu;

/**
 * Класс, описывающий возможности бота
 */
@Component
@Getter
@Setter
public class BotCommandReceiver {

    private StartCommand startCommand;
    private InfoCommand infoCommand;
    private AddCommand addCommand;
    //private ChangeWishCommand changeWishCommand;
    //private GetListWishCommand getListWishCommand;

    // ответ на выбранную кнопку
    public SendMessage getCommandResponse (Update update) throws TelegramApiException {
        if (update.getMessage().getText().equals(COMMANDS.INFO.getCommand())) {
            return infoCommand.execute(update);
        }
        if (update.getMessage().getText().equals(COMMANDS.START.getCommand())) {
            return startCommand.execute(update);
        }
        if (update.getMessage().getText().equalsIgnoreCase(COMMANDS.ADD_WISH.getCommand())) {
            return addCommand.execute(update);
        }
        if (update.getMessage().getText().equalsIgnoreCase(COMMANDS.CHANGE_WISH.getCommand())) {
            return changeWish(update);
        }
        if (update.getMessage().getText().equalsIgnoreCase(COMMANDS.WISHLIST.getCommand())) {
            return getWishList(update);
        }
        return handleNotFoundCommand();
    }

    public SendMessage handleNotFoundCommand() {
        SendMessage messageHandleNotFoundCommand = new SendMessage();
        messageHandleNotFoundCommand.setText("Выберите команду из списка");
        messageHandleNotFoundCommand.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        return messageHandleNotFoundCommand;
    }

    /*
    private SendMessage handleInfoCommand() {
        SendMessage message = new SendMessage();
        String image = new String(Character.toChars(0x1F381));
        message.setText("Это канал о самых сокровенных желаниях" + image + "Добавляйте свое пожелание, " +
                "и оно будет видно другим пользователям");
        message.setReplyMarkup(Keyboard.getKeyBoardMenu());
        return message;
    } */

    /*
    private SendMessage handleStartCommand() {
        SendMessage message = new SendMessage();
        message.setText("Доступные команды:");
        message.setReplyMarkup(Keyboard.getKeyBoardMenu());
        return message;
    } */

    private SendMessage addWish (String username, String id, String name, String chatId) throws TelegramApiException {
        SendMessage messageAddWish = new SendMessage();
        messageAddWish.setText("Выберите команду");
        messageAddWish.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        return messageAddWish;
    }

    private SendMessage changeWish(){
        SendMessage messageChangeWish = new SendMessage();
        messageChangeWish.setText("Здесь будет changeWish");
        messageChangeWish.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        return messageChangeWish;
    }

    private SendMessage getWishList(){
        SendMessage messageGetWishList = new SendMessage();
        messageGetWishList.setText("Здесь будет getWishList");
        messageGetWishList.setReplyMarkup(KeyboardMenu.getKeyBoardMenu());
        return messageGetWishList;
    }
}

