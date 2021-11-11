package telegrambot.service;

import org.springframework.stereotype.Service;
import telegrambot.repository.TelegramUserRepository;

@Service
public class TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserService(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    public void createGiftOwner(String name, int chatId){
        telegramUserRepository.saveNameAndId(name, chatId);
    }
}
