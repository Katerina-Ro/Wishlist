package telegrambot.entities.StatusGift;

import telegrambot.entities.Gift;

public interface ChangeStatusGift {
    String changeStatusGiftOwn(Gift gift);
    String changeStatusGiftAnother(Gift gift);
}
