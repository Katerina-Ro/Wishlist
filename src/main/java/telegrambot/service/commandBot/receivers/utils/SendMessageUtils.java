package telegrambot.service.commandBot.receivers.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class SendMessageUtils {
    public static SendMessage sendMessage(Update update, String sentMessage){
        long chatIdUser;
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        if (update.hasCallbackQuery()) {
            chatIdUser = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatIdUser = update.getMessage().getChatId();
        }
        SendMessage sendMessage = new SendMessage()
                .setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .enableHtml(true)
                .setChatId(chatIdUser)
                .setText(sentMessage);
        if (update.getMessage().isReply()){
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
