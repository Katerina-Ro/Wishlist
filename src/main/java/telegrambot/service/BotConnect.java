package telegrambot.service;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.receivers.BotCommandReceiver;

/**
 * Класс для соединения с ботом
 */
@Component
@PropertySource(value = "classpath:botsecret.properties")
@Getter
@Setter
public class BotConnect extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    /*
    Аннотация @SneakyThrows может быть использована для бросания проверяемых исключений без их объявления в throws метода.
     */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        new BotCommandReceiver().getCommandResponse(update);

        /*получаем какое-то обновление из телеграммбота. Если это обновление является сообщением и является текстовым
         сообщением, то получаем этот текст и получаем идентификатор чата

        if (update.hasMessage() && update.getMessage().hasText()) {
            String textMessage = update.getMessage().getText();

            //long chat_id = update.getMessage().getChatId();
            botModelWishList.getCommandResponse(textMessage, update.getMessage().getFrom(), CommandUtils.getChatId(update));
                //
                sendMessage.enableHtml(true);
                /* За выбор форматирования при отправке сообщений отвечает аргумент parse_mode
                Вместо Enum-а можно задать parse_mode в виде обычной строки:
                await message.answer("Hello, *world*\!", parse_mode="MarkdownV2")

                sendMessage.setParseMode(ParseMode.HTML);
                sendMessage.setChatId(String.valueOf(chat_id));
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                //log.error("", ex);
                //SendMessage
                sendMessage = botModelWishList.handleNotFoundCommand();
                sendMessage.setChatId(String.valueOf(chat_id));
            }
        }
        else if(update.hasCallbackQuery()) {
            try {
                SendMessage message = botModelWishList.getCommandResponse(update.getCallbackQuery().getData(),
                        update.getCallbackQuery().getFrom(),
                        String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
                message.enableHtml(true);
                message.setParseMode(ParseMode.HTML);
                message.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
                execute(message);
            } catch (TelegramApiException e) {
                //log.error("", e);
            }
        } */
    }
}

