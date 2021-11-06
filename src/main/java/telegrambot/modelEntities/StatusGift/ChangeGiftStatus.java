package telegrambot.modelEntities.StatusGift;

import telegrambot.modelEntities.Gift;
import org.springframework.stereotype.Component;

@Component
public class ChangeGiftStatus implements ChangeStatusGift {

    public ChangeGiftStatus() {
    }

    @Override
    public void setStatusGiftOwn(Gift gift) {
        //нажимает на кнопку ("сделать неактивным" или что-то наподобие - функция Hundler, вроде бы, нужно ее реализовтаь отдельно)
        if (true){
            //проверяем, не заблокировал ли админ
            if (gift.getStatusGiftAdmin() != StatusGift.NOT_ACTIVE) {
                if (gift.getStatusGiftOwn() == StatusGift.ACTIVE) {
                    gift.setStatusGiftOwn(StatusGift.NOT_ACTIVE);
                    System.out.println("Ваше пожелание стало " + StatusGift.NOT_ACTIVE.getStatusGift() + ". Теперь никто, кроме Вас его не видит");
                } else {
                    gift.setStatusGiftOwn(StatusGift.ACTIVE);
                    System.out.println("Ваше пожелание стало " + StatusGift.ACTIVE.getStatusGift() + ". Теперь его могут видеть все");
                }
                //сделать так, чтобы подарок видел только владелец и админ
            } else {
                System.out.println("Ваше пожелание отмечено как неактивное администратором. Обратитесь к администратору");
            }
        }
    }

    @Override
    public void setStatusGiftAnother(Gift gift) {
        //нажимает на кнопку ("сделать неактивным" или что-то наподобие - функция Hundler, вроде бы, нужно ее реализовтаь отдельно)
        StatusGift statusGift = StatusGift.ACTIVE;
        if (true){
            //проверяем, активен ли подарок
            if (gift.getStatusGiftOwn() == StatusGift.ACTIVE && gift.getStatusGiftAdmin() == StatusGift.ACTIVE) {
                gift.setStatusGiftAnother(StatusGift.NOT_ACTIVE);
                //сделать так, чтобы подарок видел только владелец и админ
            } else {
                System.out.println("Пожелание отмечено как неактивное");
            }
        }
    }
}
