package telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;
import telegrambot.repository.GiftRepository;

import java.util.List;
import java.util.Set;

@Service
public class WishService {

    private final GiftRepository giftRepository;

    @Autowired
    public WishService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public void createNameGift(String nameGift, GiftOwner giftOwner){
        giftRepository.saveNameGift(nameGift, giftOwner);
    }

    public void createWish(String giftDescription, Gift gift){
        gift.setDescriptionGift(giftDescription);
        giftRepository.save(gift);
    }

    public List<Gift> getInfoGifts(Long userId){
        return giftRepository.findAllWishesChatIdUser(userId);
    }

    public void deleteAllWish(Set<Integer> giftsId){
        for(Integer i: giftsId) {
            giftRepository.deleteById(i);
        }
    }
}
