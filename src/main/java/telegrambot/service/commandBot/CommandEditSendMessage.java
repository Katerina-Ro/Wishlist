package telegrambot.service.commandBot;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandEditSendMessage {
    EditMessageText execute(Update update);
}
