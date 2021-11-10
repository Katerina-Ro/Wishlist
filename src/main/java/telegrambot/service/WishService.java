package telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.Gift;
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

    public void createWish(Gift gift){
        giftRepository.save(gift);
    }

    public List<Gift> getInfoGifts(Integer userId){
        return giftRepository.findAllByChatId(userId);
    }

    public void deleteAllWish(Set<Integer> giftsId){
        for(Integer i: giftsId) {
            giftRepository.deleteById(i);
        }
    }



}
