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
        GiftOwner go = new GiftOwner();
        if(telegramUserRepository.findById(chatIdUser).isPresent()){
            go = telegramUserRepository.findById(chatIdUser).get();
        }
        return go;
    }

    public GiftOwner getGiftOwner (String name) {
        System.out.println("Получаем пользователя из БД = " + telegramUserRepository.getGiftOwner(name));
        return telegramUserRepository.getGiftOwner(name);
    }

    public String getNameUser (long idUser){
        return getGiftOwner(idUser).getName();
    }

    public boolean containsNameUserInDB(long chatIdUser){
        boolean containsNameUserInDB = false;
        if(telegramUserRepository.findById(chatIdUser).isPresent() &&
        telegramUserRepository.findById(chatIdUser).get().getName() != null){
            containsNameUserInDB = true;
        }
        return containsNameUserInDB;
    }

    public boolean existNameUserInDB(String inputName){
        boolean existNameUserInDB = false;
            for (String s : telegramUserRepository.findAllName()) {
                if (inputName.equals(s)) {
                    existNameUserInDB = true;
                    break;
                }
            }
        return existNameUserInDB;
    }
}
