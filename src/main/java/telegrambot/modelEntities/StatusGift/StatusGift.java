package telegrambot.modelEntities.StatusGift;

//@Component
public enum StatusGift {
    NOT_ACTIVE ("Неактивно"), ACTIVE ("Активно");

    private final String statusGift;

    StatusGift(String statusGift) {
        this.statusGift = statusGift;
    }

    public String getStatusGift() {
        return statusGift;
    }
}
