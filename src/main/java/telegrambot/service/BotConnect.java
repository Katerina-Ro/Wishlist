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
import telegrambot.service.commandBot.receivers.*;
import telegrambot.service.commandBot.receivers.utils.CommandCheckUtil;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;

/**
 * Класс для соединения с ботом и получения от него сообщения
 */
@Service
@PropertySource(value = "classpath:botsecret.properties")
@Getter
public class BotConnect extends TelegramLongPollingBot {
    private final BotCommandSendMessage botCommandSendMessage;
    private final BotCommandCallbackQuery botCommandCallbackQuery;
    private final BotCommandCallBackQueryEdit botCommandCallbackQueryEdit;
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
                      BotCommandCallBackQueryEdit botCommandCallbackQueryEdit,
                      BotCommandForceReply botCommandForceReply) {
        this.botCommandSendMessage = botCommandSendMessage;
        this.botCommandCallbackQuery = botCommandCallbackQuery;
        this.botCommandCallbackQueryEdit = botCommandCallbackQueryEdit;
        this.botCommandForceReply = botCommandForceReply;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.hasMessage() || CommandCheckUtil.checkCommandCallBackEditChangeWish(update)) {
            if (CommandCheckUtil.checkCommandCallBackEditChangeWish(update)){
                String commandIdentifier = FindingDataUtil.findLineByIncomingMessage(update.getCallbackQuery().getData());
                execute(botCommandForceReply.findCommand(commandIdentifier, update));
            } else if (update.getMessage().isReply() && !CommandCheckUtil.checkCommandReplyBackToMainMenu(update)) {
                String commandIdentifier = update.getMessage().getReplyToMessage().getText();
                if(FindingDataUtil.containLineBreak(commandIdentifier)) {
                    commandIdentifier = FindingDataUtil.findLineByIncomingMessageByN(update.getMessage().getReplyToMessage().getText());
                } else {
                    commandIdentifier = update.getMessage().getReplyToMessage().getText();
                }
                execute(botCommandForceReply.findCommand(commandIdentifier, update));
            }
            else {
                execute(botCommandSendMessage.findCommand(update.getMessage().getText(), update));
            }
        } else if (update.hasCallbackQuery()) {
            String commandIdentifier = update.getCallbackQuery().getData();
            if (botCommandCallbackQuery.getCommandMapCommand().containsKey(commandIdentifier)) {
                execute(botCommandCallbackQuery.findCommand(commandIdentifier, update));
            } else {
                if(CommandCheckUtil.checkCommandCallBackEditBackToMainMenu(update)){
                    execute(botCommandSendMessage.findCommand(commandIdentifier, update));
                } else {
                    execute(botCommandCallbackQueryEdit.findCommand(commandIdentifier, update));
                    if (!botCommandCallbackQueryEdit.getCommandMapCommandEdit().containsKey(commandIdentifier)) {
                        commandIdentifier = CommandCheckUtil.whichCommand(update);
                        execute(botCommandCallbackQueryEdit.findCommand(commandIdentifier, update));
                        if (!botCommandCallbackQueryEdit.getCommandMapCommandEdit().containsKey(commandIdentifier)) {
                            execute(botCommandSendMessage.findCommand(update.getCallbackQuery().getData(), update));
                        }
                    }
                }
            }
        }
    }
}

