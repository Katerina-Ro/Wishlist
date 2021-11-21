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

    public Gift createNameGift(String nameGift, int iDGift, GiftOwner giftOwner){
        Gift gift = new Gift();
        gift.setNameGift(nameGift);
        gift.setGiftOwner(giftOwner);
        gift.setGiftId(iDGift);
        return giftRepository.save(gift);
    }

    public Gift save(Gift gift){ return giftRepository.save(gift); }

    public void createDescriptionWish(String giftDescription, Gift gift){
        gift.setDescriptionGift(giftDescription);
        giftRepository.save(gift);
    }

    /**
     * Все активные, но еще никем не выбранные, пожелания одного выбранного пользователя + пожелания, которые
     * выбраны самим презентатором
     * @param userId - id пользователя, чей список пожеланий нужно посмотреть
     * @param userIdPresenter - id презентатора
     * @return список пожеланий
     */
    public List<Gift> getInfoAnotherGiftsInlIdPresenter(Long userId, long userIdPresenter) {
        return giftRepository.findAllByChatIdByStatusGift(userId, userIdPresenter);
    }

    /**
     * Все активные, но еще никем не выбранные, пожелания одного выбранного пользователя
     * @param userId - id пользователя, чей список пожеланий нужно посмотреть
     * @return список пожеланий
     */
    public List<Gift> getInfoAnotherGifts(long userId, long idPresenter) {
        System.out.println("получаем список только моих выбранных и чужих желаний = "+giftRepository.findAllByChatIdByStatusGift(userId, idPresenter));
        return giftRepository.findAllByChatIdByStatusGift(userId, idPresenter);
    }

    /**
     * все пожелания разных людей, где iDGiftPresenter - презентатор
     * @param iDGiftPresenter - iD презентатора
     * @return список пожеланий
     */
    public List<Gift> getListWishAnother(long iDGiftPresenter){
        return giftRepository.findAllByGiftAnotherChatId(iDGiftPresenter);
    }

    public List<Gift> getInfoGifts(long userId){
        return giftRepository.findAllByGiftOwnerChatId(userId);
    }

    public Gift getInfoGiftById(int idGift) {
        return giftRepository.findByGiftId(idGift);
    }

    public void updateStatusGift(Gift gift){
        giftRepository.save(gift);
    }

    public void deleteWishById(int idGift){ giftRepository.deleteById(idGift); }

    public boolean checkingGiftGivingStatus(int idGift) {
        boolean checkingGiftGivingStatus = false;
        if(getInfoGiftById(idGift).getStatusGiftAnother().getStatusGift()
                .equalsIgnoreCase(STATUS_GIFT.ACTIVE.getStatusGift())){
            checkingGiftGivingStatus = true;
        }
        return checkingGiftGivingStatus;
    }
}