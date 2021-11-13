package telegrambot.service;

import org.springframework.stereotype.Service;
import telegrambot.repository.TelegramUserRepository;

@Service
public class TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserService(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    public void createIdUserToDB(long chatId){
        telegramUserRepository.saveIdUser(chatId);
    }

    public void createNameGiftOwner(String name_user, int chatId){
        telegramUserRepository.saveNameUser(name_user, chatId);
    }
}
