package telegrambot.service.commandBot.receivers.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class SendMessageUtils {
    public static SendMessage sendMessage(Update update, String sentMessage, boolean forceReply){
        long chatIdUser;
        if (update.hasCallbackQuery()) {
            chatIdUser = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatIdUser = update.getMessage().getChatId();
        }
        SendMessage sendMessage = new SendMessage()
                .enableHtml(true)
                .setChatId(chatIdUser)
                .setText(sentMessage);
        if (forceReply) {
            ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
            int messageId;
            if (update.hasCallbackQuery()) {
                messageId = update.getCallbackQuery().getMessage().getMessageId();
            } else {
                messageId = update.getMessage().getMessageId();
            }
            sendMessage.setReplyToMessageId(messageId);
            sendMessage.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        }
        return sendMessage;
    }

    public static EditMessageText sendEditMessage(Update update, String sentMessage, InlineKeyboardMarkup
            inlineKeyboardMarkup){
        return new EditMessageText()
                .setText(sentMessage)
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .enableHtml(true)
                .setReplyMarkup(inlineKeyboardMarkup);
    }
}
