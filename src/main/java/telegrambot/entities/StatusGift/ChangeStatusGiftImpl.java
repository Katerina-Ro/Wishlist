package telegrambot.entities.StatusGift;

import telegrambot.entities.Gift;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusGiftImpl implements ChangeStatusGift {

    @Override
    public String changeStatusGiftOwn(Gift gift) {
        String statusGiftAnother = gift.getStatusGiftOwn().getStatusGift();
        if (true){
            if (gift.getStatusGiftOwn().equals(STATUS_GIFT.ACTIVE)) {
                gift.setStatusGiftOwn(STATUS_GIFT.NOT_ACTIVE);
                System.out.println("Ваше пожелание стало " + STATUS_GIFT.NOT_ACTIVE.getStatusGift() +
                        ". Теперь никто, кроме Вас его не видит");
            } else {
                gift.setStatusGiftOwn(STATUS_GIFT.ACTIVE);
                System.out.println("Ваше пожелание стало " + STATUS_GIFT.ACTIVE.getStatusGift() +
                        ". Теперь его могут видеть все");
            }
            //сделать так, чтобы подарок видел только владелец и админ
        }
        return statusGiftAnother;
    }

    @Override
    public String changeStatusGiftAnother(Gift gift) {
        String statusGiftAnother = gift.getStatusGiftAnother().getStatusGift();
        if (true){
            if (gift.getStatusGiftOwn().equals(STATUS_GIFT.ACTIVE) && gift.getStatusGiftAnother()
                    .equals(STATUS_GIFT.NOT_ACTIVE)) {
                gift.setStatusGiftAnother(STATUS_GIFT.ACTIVE);
            }
                //сделать так, чтобы подарок видел только владелец и админ
        }
        return statusGiftAnother;
    }
}
