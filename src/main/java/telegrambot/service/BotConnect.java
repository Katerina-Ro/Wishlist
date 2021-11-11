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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import telegrambot.service.commandBot.receivers.ButtonClick;
import telegrambot.service.commandBot.receivers.StartCommand;

/**
 * Класс для соединения с ботом
 */
@Service
@PropertySource(value = "classpath:botsecret.properties")
@Getter
public class BotConnect extends TelegramLongPollingBot {
    private final ButtonClick buttonClick;
    private final StartCommand startCommand;

    @Setter
    @Value("${bot.name}")
    private String botUsername;
    @Setter
    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public BotConnect(ButtonClick buttonClick, StartCommand startCommand) {
        this.buttonClick = buttonClick;
        this.startCommand = startCommand;
    }
    /*
        Аннотация @SneakyThrows может быть использована для бросания проверяемых исключений без их объявления
        в throws метода.
         */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage()!= null && update.getMessage().hasText()) {
            execute(startCommand.execute(update));
        }
        else if (update.hasCallbackQuery()) {
            String messageCallbackQuery = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            try {
                execute(buttonClick.getCommandResponse(messageCallbackQuery,chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

