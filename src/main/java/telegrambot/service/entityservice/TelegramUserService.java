package telegrambot.service.entityservice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.GiftOwner;
import telegrambot.repository.TelegramUserRepository;

@Service
public class TelegramUserService {
    @Getter
    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserService(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    public void createIdUserToDB(long chatId){
        GiftOwner giftOwner = new GiftOwner();
        giftOwner.setChatId(chatId);
        telegramUserRepository.save(giftOwner);
    }

    public void createNameGiftOwner(String name_user, long chatId){
        GiftOwner giftOwner = new GiftOwner();
        giftOwner.setName(name_user);
        giftOwner.setChatId(chatId);
        telegramUserRepository.save(giftOwner);
    }

    public GiftOwner getGiftOwner (long chatIdUser){
        return telegramUserRepository.findById(chatIdUser).get();
    }

    public boolean containsNameUserInDB(long chatIdUser){
        boolean containsNameUserInDB = false;
        if(telegramUserRepository.findById(chatIdUser).get().getName() != null){
            containsNameUserInDB = true;
        }
        return containsNameUserInDB;
    }
}
