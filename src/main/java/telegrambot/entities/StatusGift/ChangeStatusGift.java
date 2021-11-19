package telegrambot.entities.StatusGift;

import telegrambot.entities.Gift;
import telegrambot.entities.GiftOwner;

public interface ChangeStatusGift {
    String changeStatusGiftOwn(Gift gift);
    String changeStatusGiftAnother(Gift gift, GiftOwner presenter);
}
