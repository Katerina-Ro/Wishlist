package telegrambot.modelEntities.StatusGift;

import telegrambot.modelEntities.Gift;
import org.springframework.stereotype.Component;

@Component
public class ChangeGiftStatus implements ChangeStatusGift {

    @Override
    public String changeStatusGiftOwn(Gift gift) {
        String statusGiftAnother = gift.getStatusGiftAnother(STATUS_GIFT.ACTIVE).getStatusGift();
        //нажимает на кнопку ("сделать неактивным" или что-то наподобие - функция Hundler, вроде бы,
        // нужно ее реализовтаь отдельно)
        if (true){
            //проверяем, не заблокировал ли админ
            if (gift.getStatusGiftAdmin() != STATUS_GIFT.NOT_ACTIVE) {
                if (gift.getStatusGiftOwn() == STATUS_GIFT.ACTIVE) {
                    gift.setStatusGiftOwn(STATUS_GIFT.NOT_ACTIVE);
                    System.out.println("Ваше пожелание стало " + STATUS_GIFT.NOT_ACTIVE.getStatusGift() +
                            ". Теперь никто, кроме Вас его не видит");
                } else {
                    gift.setStatusGiftOwn(STATUS_GIFT.ACTIVE);
                    System.out.println("Ваше пожелание стало " + STATUS_GIFT.ACTIVE.getStatusGift() +
                            ". Теперь его могут видеть все");
                }
                //сделать так, чтобы подарок видел только владелец и админ
            } else {
                System.out.println("Ваше пожелание отмечено как неактивное администратором. " +
                        "Обратитесь к администратору");
            }
        }
        return statusGiftAnother;
    }

    @Override
    public String changeStatusGiftAnother(Gift gift) {
        String statusGiftAnother = gift.getStatusGiftAnother(STATUS_GIFT.NOT_ACTIVE).getStatusGift();
        //нажимает на кнопку ("сделать неактивным" или что-то наподобие - функция Hundler, вроде бы,
        // нужно ее реализовтаь отдельно)

        if (true){
            //проверяем, активен ли подарок
            if (gift.getStatusGiftOwn().equals(STATUS_GIFT.ACTIVE) && gift.getStatusGiftAdmin()
                    .equals(STATUS_GIFT.ACTIVE) && gift.getStatusGiftAnother(STATUS_GIFT.NOT_ACTIVE)
                    .equals(STATUS_GIFT.NOT_ACTIVE)) {
                gift.setStatusGiftAnother(STATUS_GIFT.ACTIVE);
                //сделать так, чтобы подарок видел только владелец и админ
            } else {
                System.out.println("Пожелание отмечено как " + statusGiftAnother);
            }
        }
        return statusGiftAnother;
    }
}
