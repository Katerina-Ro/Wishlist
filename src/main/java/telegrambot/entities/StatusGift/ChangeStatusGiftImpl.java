package telegrambot.entities.StatusGift;

import telegrambot.entities.Gift;
import org.springframework.stereotype.Component;
import telegrambot.entities.GiftOwner;

@Component
public class ChangeStatusGiftImpl implements ChangeStatusGift {
    @Override
    public String changeStatusGiftOwn(Gift gift) {
        String statusGiftOwn = gift.getStatusGiftOwn().getStatusGift();
            if (gift.getStatusGiftOwn().equals(STATUS_GIFT.ACTIVE)) {
                gift.setStatusGiftOwn(STATUS_GIFT.NOT_ACTIVE);
            } else {
                gift.setStatusGiftOwn(STATUS_GIFT.ACTIVE);
            }
        return statusGiftOwn;
    }

    @Override
    public String changeStatusGiftAnother(Gift gift, GiftOwner presenter) {
        String statusGiftAnother = gift.getStatusGiftAnother().getStatusGift();
            if (gift.getStatusGiftAnother().equals(STATUS_GIFT.NOT_ACTIVE)) {
                gift.setStatusGiftAnother(STATUS_GIFT.ACTIVE);
                gift.setGiftPresenter(presenter);
            } else {
                gift.setStatusGiftAnother(STATUS_GIFT.NOT_ACTIVE);
                gift.setGiftPresenter(null);
            }
        return statusGiftAnother;
    }
}
