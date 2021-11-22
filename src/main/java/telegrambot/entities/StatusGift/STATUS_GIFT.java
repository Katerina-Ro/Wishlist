package telegrambot.entities.StatusGift;

public enum STATUS_GIFT {
    NOT_ACTIVE ("не выбран"), ACTIVE (String.valueOf(Character
            .toChars(0x2713)));

    private final String statusGift;

    STATUS_GIFT(String statusGift) {
        this.statusGift = statusGift;
    }

    public String getStatusGift() {
        return statusGift;
    }
}
