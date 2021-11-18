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

    /*
    public void updateStatusGiftOwn(int idGift, STATUS_GIFT statusGiftOwn){
        Gift gift = new Gift();
        gift.setGiftId(idGift);
        gift.setStatusGiftOwn(statusGiftOwn);
        giftRepository.save(gift);
    } */

    public void updateStatusGift(Gift gift){
        giftRepository.save(gift);
    }

    public List<Gift> getInfoGifts(long userId){
        return giftRepository.findAllByGiftOwnerChatId(userId);
    }

    public List<Gift> getInfoAnotherGifts(Long userId) {
        return giftRepository.findAllByChatIdByStatusGift(userId);
    }

    public List<Gift> getListWishAnother(long iDGiftPresenter){
        return giftRepository.findAllByGiftAnotherChatId(iDGiftPresenter);
    }

    public Gift getInfoGiftById(int idGift) {
        return giftRepository.findByGiftId(idGift);
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

    public boolean checkingGiftGivingStatus(int idGift) {
        boolean checkingGiftGivingStatus = false;
        if(getInfoGiftById(idGift).getStatusGiftAnother().getStatusGift()
                .equalsIgnoreCase(STATUS_GIFT.ACTIVE.getStatusGift())){
            checkingGiftGivingStatus = true;
        }
        return checkingGiftGivingStatus;
    }

    public void deleteWishById(int idGift){
        giftRepository.deleteById(idGift);
    }
}