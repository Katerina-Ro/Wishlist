package telegrambot.service.commandBot;

public enum COMMANDS {
    ADD_WISH (new StringBuffer("Добавить пожелание")),
    BUTTON_BACK_TO_START (new StringBuffer("Назад, в главное меню")),
    BACK (new StringBuffer("Назад к выбору списка желаний")),
    CHANGE_STATUS_OWN_WISH (new StringBuffer("Поменять")),
    CHANGE_WISH (new StringBuffer("Изменить")),
    CHOOSE (new StringBuffer("Выбрать")),
    CHOOSE_WISH (new StringBuffer("Дарить")),
    DELETE (new StringBuffer("Удалить")),
    FOR_YOURESELF (new StringBuffer("Свой список пожеланий")),
    FOR_ANOTHER (new StringBuffer("Дарю другим")),
    INFO(new StringBuffer("О чем канал?")),
    MORE_DETAILS (new StringBuffer("Подробнее")),
    NAME_GIFT(new StringBuffer("/ ")),
    NAME_GIFT_OWNER(new StringBuffer("Имя")),
    NO (new StringBuffer("Нет, не удалять")),
    START(new StringBuffer("/start")),
    STATE_DB (new StringBuffer("ACTIVE")),
    STATE_DB_NOT_ACTIVE (new StringBuffer("NOT_ACTIVE")),
    WISHLIST (new StringBuffer("Посмотреть список желаний")),
    YES (new StringBuffer("Да,удалить"));

    private final StringBuffer command;

    COMMANDS (StringBuffer command) {
        this.command = command;
    }

    public StringBuffer getCommand() {
        return command;
    }
}


