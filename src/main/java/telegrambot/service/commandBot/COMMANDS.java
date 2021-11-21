package telegrambot.service.commandBot;

public enum COMMANDS {
    ADD_WISH ("Добавить пожелание"),
    BUTTON_BACK_TO_START ("Назад, в главное меню"),
    BACK ("Назад к выбору списка желаний"),
    CHANGE_STATUS_OWN_WISH ("Поменять"),
    CHANGE_WISH ("Изменить"),
    CHOOSE ("Выбрать"),
    CHOOSE_WISH ("Дарить"),
    DELETE ("Удалить"),
    FOR_YOURESELF ("Свой список пожеланий"),
    FOR_ANOTHER ("Дарю другим"),
    INFO("О чем канал?"),
    MORE_DETAILS ("Подробнее"),
    NAME_GIFT("/ "),
    NAME_GIFT_OWNER("Имя"),
    NO ("Нет, не удалять"),
    START("/start"),
    STATE_DB ("ACTIVE"),
    STATE_DB_NOT_ACTIVE ("NOT_ACTIVE"),
    WISHLIST ("Посмотреть список желаний"),
    YES ("Да,удалить");

    private String command;

    COMMANDS (String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}


