package telegrambot.entities.StatusGift;

import org.springframework.stereotype.Component;

//@Component
public enum STATUS_GIFT {
    NOT_ACTIVE (" "), ACTIVE ("U+2713");

    private final String statusGift;

    STATUS_GIFT(String statusGift) {
        this.statusGift = statusGift;
    }

    public String getStatusGift() {
        return statusGift;
    }
}
