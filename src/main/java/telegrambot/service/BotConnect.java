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
import telegrambot.service.commandBot.receivers.BotCommandReceiver;

/**
 * Класс для соединения с ботом
 */
@Service
@PropertySource(value = "classpath:botsecret.properties")
@Getter
@Setter
public class BotConnect extends TelegramLongPollingBot {

    private final BotCommandReceiver botCommandReceiver;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public BotConnect(BotCommandReceiver botCommandReceiver) {
        this.botCommandReceiver = botCommandReceiver;
    }

    /*
    Аннотация @SneakyThrows может быть использована для бросания проверяемых исключений без их объявления
    в throws метода.
     */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        botCommandReceiver.getCommandResponse(update);
    }
}

