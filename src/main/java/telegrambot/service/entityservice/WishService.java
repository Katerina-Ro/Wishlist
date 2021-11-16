package telegrambot.service.entityservice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;
import telegrambot.entities.StatusGift.STATUS_GIFT;
import telegrambot.repository.GiftRepository;

import java.util.*;

@Service
public class WishService {
    @Getter
    private final GiftRepository giftRepository;

    @Autowired
    public WishService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public Gift createNameGift(String nameGift, GiftOwner giftOwner){
            Gift gift = new Gift();
            gift.setNameGift(nameGift);
            gift.setGiftOwner(giftOwner);
            return giftRepository.save(gift);
    }

    public void createDescriptionWish(String giftDescription, Gift gift){
        gift.setDescriptionGift(giftDescription);
        giftRepository.save(gift);
    }

    public List<Gift> getInfoGifts(Long userId){
        return giftRepository.findAllByGiftOwnerChatId(userId);
    }

    public Set<Integer> getIdGift(List<Gift> listGift){
        Set<Integer> setIdGift = new HashSet<>();
        for (Gift g: listGift){
            setIdGift.add(g.getGiftId());
        }
        return setIdGift;
    }

    public Gift getGift (String name, List<Gift> listGifts){
        Gift gift = new Gift();
        for (Gift g: listGifts){
            if(g.getNameGift().equalsIgnoreCase(name)) {
                gift.setGiftId(g.getGiftId());
                gift.setNameGift(g.getNameGift());
                gift.setDescriptionGift(g.getDescriptionGift());
                gift.setStatusGiftOwn(g.getStatusGiftOwn());
                gift.setStatusGiftAnother(g.getStatusGiftAnother());
                gift.setLinksList(g.getLinksList());
            }
        }
        return gift;
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