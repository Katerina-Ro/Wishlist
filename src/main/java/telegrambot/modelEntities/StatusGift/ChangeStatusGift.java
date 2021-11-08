package telegrambot.modelEntities.StatusGift;

import telegrambot.modelEntities.Gift;

public interface ChangeStatusGift {
    default void setStatusGiftAdmin () {
        Gift gift = new Gift();
        //нажимает на кнопку ("сделать неактивным" или что-то наподобие - функция Hundler, вроде бы, нужно ее реализовтаь отдельно)
        if (gift.getStatusGiftAdmin()== STATUS_GIFT.ACTIVE) {
            gift.setStatusGiftAdmin(STATUS_GIFT.NOT_ACTIVE);
            //    gift.setStatusGiftOwn(StatusGift.NOT_ACTIVE);
            System.out.println("Статус подарка " + STATUS_GIFT.NOT_ACTIVE.getStatusGift());
        }
    }

    void setStatusGiftOwn(Gift gift);

    void setStatusGiftAnother(Gift gift);
}
