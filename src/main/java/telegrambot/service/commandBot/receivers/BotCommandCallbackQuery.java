package telegrambot.service.commandBot.receivers;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.Command;
import telegrambot.service.commandBot.receivers.utils.COMMANDS;

@Service
@Getter
public class BotCommandCallbackQuery {

    private final ImmutableMap<String, Command> commandMap;

    private final Command infoCommand;
    private final Command addCommand;
    private final Command changeWishCommand;
    private final Command getWishList;
    private final Command insertNameUserToDB;//Добавить имя


    @Autowired
    public BotCommandCallbackQuery(@Qualifier("startCommand") Command startCommand,
                                   @Qualifier("infoCommand") Command infoCommand,
                                   @Qualifier("addCommand") Command addCommand,
                                   @Qualifier("changeWishCommand") Command changeWishCommand,
                                   @Qualifier("getListWishCommand") Command getWishList,
                                   @Qualifier("insertNameUserToDB")Command insertNameUserToDB) {

        this.infoCommand = infoCommand;
        this.addCommand = addCommand;
        this.changeWishCommand = changeWishCommand;
        this.getWishList = getWishList;
        this.insertNameUserToDB = insertNameUserToDB;


        // https://www-baeldung-com.translate.goog/java-immutable-maps?_x_tr_sl=en&_x_tr_tl=ru&_x_tr_hl=ru&_x_tr_pto=nui,sc
        // Иногда предпочтительнее запретить модификации  java.util.Map,  такие как совместное использование
        // данных только для чтения между потоками. Для этой цели мы можем использовать либо
        // немодифицируемую карту, либо неизменяемую карту.
        this.commandMap = ImmutableMap.<String, Command>builder()
                .put(COMMANDS.ADD_WISH.getCommand(), addCommand)
                .put(COMMANDS.INFO.getCommand(), infoCommand)
                .put(COMMANDS.CHANGE_WISH.getCommand(), changeWishCommand)
                .put(COMMANDS.START.getCommand(), infoCommand)
                .build();

    }

    public SendMessage findCommand(String commandIdentifier, Update update) {
        return (commandMap.get(commandIdentifier).execute(update));
    }

    /*
    public SendMessage getCommandResponse (String message, long chat_id) throws TelegramApiException {
        if (message.equals(COMMANDS.INFO.getCommand())) {
            return infoCommand.execute(chat_id);
        }
        if (message.equalsIgnoreCase(COMMANDS.ADD_WISH.getCommand())) {
            return addCommand.execute(chat_id);
        }
        if (message.equalsIgnoreCase(COMMANDS.CHANGE_WISH.getCommand())) {
            return changeWishCommand.execute(chat_id);
        }
        return getWishList.execute(chat_id);
    }
    */


}
