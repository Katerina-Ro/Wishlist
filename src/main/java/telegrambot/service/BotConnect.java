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
import telegrambot.service.commandBot.receivers.addwish.AddCommand;
import telegrambot.service.commandBot.receivers.addwish.InsertNameUserToDBCommand;
import telegrambot.service.commandBot.receivers.utils.CommandCheckUtil;
import telegrambot.service.commandBot.receivers.utils.FindingDataUtil;

/**
 * Класс для соединения с ботом
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
    /*
      Аннотация @SneakyThrows может быть использована для бросания проверяемых исключений без их объявления
      в throws метода.
    */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.hasMessage() || CommandCheckUtil
                .checkCommandCallBackEditChangeWish(update)) {
            System.out.println("сейчас начнется проверка   if (update.getMessage().isReply() && !CommandCheckU");
            System.out.println();
            if (CommandCheckUtil.checkCommandCallBackEditChangeWish(update)){
                String commandIdentifier = FindingDataUtil.findLineByIncomingMessage(update.getCallbackQuery().getData());
                System.out.println("commandIdentifier = " + commandIdentifier);

                execute(botCommandForceReply.findCommand(commandIdentifier, update));
            } else if (update.getMessage().isReply() && !CommandCheckUtil.checkCommandReplyBackToMainMenu(update))
                     {
               // System.out.println(" update.getMessage().getReplyToMessage().getText() " +  update.getMessage().getReplyToMessage().getText());

                System.out.println();
               // System.out.println("long chatId = " + update.getMessage().getChatId());
                System.out.println();
                //System.out.println("update.getMessage().getText() = " + update.getMessage().getText());
                System.out.println("это Реплай ");
               // System.out.println("InsertNameUserToDBCommand.getNameWish() " + InsertNameUserToDBCommand.getNameWish());
               // System.out.println("update.getMessage().getMessageId() " + update.getMessage().getMessageId());
                System.out.println("update.getMessage().isReply() " + update.getMessage().isReply());

                String commandIdentifier = update.getMessage().getReplyToMessage().getText();
                System.out.println("commandIdentifier до обрезки" + commandIdentifier);
                System.out.println();
                if(FindingDataUtil.containLineBreak(update.getMessage().getReplyToMessage().getText())) {
                    commandIdentifier = FindingDataUtil.findLineByIncomingMessageByN(
                            update.getMessage().getReplyToMessage().getText());
                    System.out.println("commandIdentifier обрезан" );
                } else {
                commandIdentifier = update.getMessage().getReplyToMessage().getText();
                    System.out.println();
                }
                System.out.println("commandIdentifier =" + commandIdentifier);
                System.out.println();
                System.out.println("AddCommand.getMESSAGE_ADD() = "+ AddCommand.getMESSAGE_ADD());
                //System.out.println("InsertNameUserToDBCommand.getNameWish() " +  InsertNameUserToDBCommand.getNameWish());
                //System.out.println("равны " + commandIdentifier.equals(InsertNameUserToDBCommand.getNameWish()));
                execute(botCommandForceReply.findCommand(commandIdentifier, update));
            }
            else {
                System.out.println("");
                System.out.println("это Обычный сенд ");
                execute(botCommandSendMessage.findCommand(update.getMessage().getText(), update));
            }
        } else if (update.hasCallbackQuery()) {
            System.out.println("");
            System.out.println("это CallbackQuery");
            String commandIdentifier = update.getCallbackQuery().getData();
            if (botCommandCallbackQuery.getCommandMapCommand().containsKey(commandIdentifier)) {
                execute(botCommandCallbackQuery.findCommand(commandIdentifier, update));
            } else {
                System.out.println("");
                System.out.println("приходит update.getCallbackQuery() это " + commandIdentifier);
                if(CommandCheckUtil.checkCommandCallBackEditBackToMainMenu(update)){

                    System.out.println();
                    System.out.println("внутри блока  if(CommandCheckUtil.checkCommandBackToMainMenu(update)) ");
                    // System.out.println("update.getMessage().getText() = " + update.getMessage().getText());
                    System.out.println();
                    execute(botCommandSendMessage.findCommand(commandIdentifier, update));
                    System.out.println("вышел из блока  if(CommandCheckUtil.checkCommandBackToMainMenu(update))");
                }
                    /*
                    if (CommandCheckUtil.checkCommandCallBackChangeWishStatusOwn(update)){
                        System.out.println();
                        System.out.println("это блок   CommandCheckUtil.checkCommandCallBackChangeWishStatusOwn(update)");
                        System.out.println();

                        System.out.println();
                        System.out.println("commandIdentifier = " + commandIdentifier);
                    } */
                else if (!botCommandCallbackQueryEdit.getCommandMapCommandEdit()
                        .containsKey(commandIdentifier)) {
                    // && !CommandCheckUtil.checkCommandCallBackChangeWishStatusOwn(update)
                    System.out.println();
                    System.out.println("это блок    !botCommandCallbackQueryEdit.getCommandMapCommandEdit().containsKey(commandIdentifier)\n");
                    System.out.println();
                    commandIdentifier = CommandCheckUtil.whichCommand(update);
                    System.out.println();
                    System.out.println("commandIdentifier = " + commandIdentifier);
                    System.out.println("");
                    System.out.println("вышел из блока !botCommandCallbackQueryEdit.getCommandMapCommandEdit(");
                    if (!botCommandCallbackQueryEdit.getCommandMapCommandEdit()
                            .containsKey(commandIdentifier)) {
                        System.out.println();
                        System.out.println("это блок if (!botCommandCallbackQueryEdit.getCommandMapCommandEdit().containsKey(commandIdentifier)");
                        execute(botCommandSendMessage.findCommand(update.getCallbackQuery().getData(), update));
                        System.out.println("вышел из блока if (!botCommandCallbackQueryEdit.getCommandMapCommandEdit().containsKey(commandIdentifier)");
                    }
                }
                execute(botCommandCallbackQueryEdit.findCommand(commandIdentifier, update));
            }
        }
    }
}

