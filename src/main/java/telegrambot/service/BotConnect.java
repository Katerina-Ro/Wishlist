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
import telegrambot.service.commandBot.receivers.InfoCommand;
import telegrambot.service.commandBot.receivers.StartCommand;

/**
 * Класс для соединения с ботом
 */
@Service
@PropertySource(value = "classpath:botsecret.properties")
@Getter
@Setter
public class BotConnect extends TelegramLongPollingBot {

  //  private final BotCommandReceiver botCommandReceiver;


ButtonClick buttonClick;
    private final StartCommand startCommand;



    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public BotConnect(StartCommand startCommand) {

        this.startCommand = startCommand;

    }

    @Autowired
    public void setButtonClick(ButtonClick buttonClick) {
        this.buttonClick = buttonClick;
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
            String message_call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            try {
                execute(buttonClick.getCommandResponse(message_call_data,chat_id,message_id));

            } catch (TelegramApiException e) {
                e.printStackTrace();

            }
        }
    }
}

