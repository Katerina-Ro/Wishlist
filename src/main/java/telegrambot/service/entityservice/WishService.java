package telegrambot.service.entityservice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;
import telegrambot.entities.StatusGift.STATUS_GIFT;
import telegrambot.repository.GiftRepository;

import java.util.Map;
import java.util.Set;

@Service
public class WishService {
    @Getter
    private final GiftRepository giftRepository;

    @Autowired
    public WishService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Gift createNameGift(String nameGift, GiftOwner giftOwner){
        return giftRepository.saveNameGift(nameGift, giftOwner);
    }

    public void createDescriptionWish(String giftDescription, Gift gift){
        gift.setDescriptionGift(giftDescription);
        giftRepository.save(gift);
    }

    public Map<Integer,Gift> getInfoGifts(Long userId){
        return giftRepository.findAllWishesChatIdUser(userId);
    }

    public STATUS_GIFT getGiftStatusOwner(Map<Integer, Gift> gifts, int idGift){
        return gifts.get(idGift).getStatusGiftOwn();
    }

    public STATUS_GIFT getGiftStatusAnother(Map<Integer, Gift> gifts, int idGift){
        return gifts.get(idGift).getStatusGiftAnother();
    }

    public void deleteAllWish(Set<Integer> giftsId){
        for(Integer i: giftsId) {
            giftRepository.deleteById(i);
        }
    }
}