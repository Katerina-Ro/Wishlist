package telegrambot.service;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import telegrambot.service.commandBot.receivers.*;

/**
 * Класс для соединения с ботом
 */
@Service
@PropertySource(value = "classpath:botsecret.properties")
@Getter
public class BotConnect extends TelegramLongPollingBot {
    private final BotCommandSendMessage botCommandSendMessage;
    private final BotCommandCallbackQuery botCommandCallbackQuery;
    private final BotCommandForceReply botCommandForceReply;

    @Setter
    @Value("${bot.name}")
    private String botUsername;
    @Setter
    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public BotConnect(BotCommandSendMessage botCommandSendMessage,
                      BotCommandCallbackQuery botCommandCallbackQuery,
                      BotCommandForceReply botCommandForceReply) {
        this.botCommandSendMessage = botCommandSendMessage;
        this.botCommandCallbackQuery = botCommandCallbackQuery;
        this.botCommandForceReply = botCommandForceReply;
    }
    /*
        Аннотация @SneakyThrows может быть использована для бросания проверяемых исключений без их объявления
        в throws метода.
         */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage()!= null && update.hasMessage()) {
            if (update.getMessage().isReply()){
                /*
                if(update.getMessage().getReplyToMessage().getText().equals(AddCommand.getMESSAGE_ADD())) {
                    telegramUserService.createNameGiftOwner(update.getMessage().getText(),
                            Math.toIntExact(update.getMessage().getChatId()));
                } */
                String s = update.getMessage().getReplyToMessage().getText();
                String g =InsertNameUserToDB.getINPUT_ERROR_MESSAGE();
                System.out.println("update.getMessage().getReplyToMessage().getText() " + update.getMessage().getReplyToMessage().getText());
                System.out.println("InsertNameUserToDB.getINPUT_ERROR_MESSAGE() " + InsertNameUserToDB.getINPUT_ERROR_MESSAGE());
                System.out.println(s.equalsIgnoreCase(g));
                execute(botCommandForceReply.findCommand(update.getMessage().getReplyToMessage().getText(),
                        update));
            } else {
                execute(botCommandSendMessage.findCommand(update.getMessage().getText(), update));
            }
        } else if (update.hasCallbackQuery()) {
            execute(botCommandCallbackQuery.findCommand(update.getCallbackQuery().getData(), update));
        } else {
               System.out.println("Нажмите на любую из предложенных кнопок");
           }
    }
}

