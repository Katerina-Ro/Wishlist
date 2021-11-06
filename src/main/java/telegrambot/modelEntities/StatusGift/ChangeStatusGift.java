package telegrambot.modelEntities.StatusGift;

import telegrambot.modelEntities.Gift;

public interface ChangeStatusGift {
    default void setStatusGiftAdmin () {
        Gift gift = new Gift();
        //нажимает на кнопку ("сделать неактивным" или что-то наподобие - функция Hundler, вроде бы, нужно ее реализовтаь отдельно)
        if (gift.getStatusGiftAdmin()== StatusGift.ACTIVE) {
            gift.setStatusGiftAdmin(StatusGift.NOT_ACTIVE);
            //    gift.setStatusGiftOwn(StatusGift.NOT_ACTIVE);
            System.out.println("Статус подарка " + StatusGift.NOT_ACTIVE.getStatusGift());
        }
    }

    void setStatusGiftOwn(Gift gift);

    void setStatusGiftAnother(Gift gift);
}
