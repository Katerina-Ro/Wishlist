package telegrambot.service.commandBot;

public enum COMMANDS {
    INFO("О чем канал?"),
    START("/start"),
    ADD_WISH ("Добавить пожелание"),
    ADD_NAME_USER_TO_DB ("Добавить имя"),
    WISHLIST ("Посмотреть список желаний"),
    CHANGE_WISH ("Изменить пожелание"),
    YES ("Да"),
    NO ("Нет"),
    SEND ("Отправить"),
    MORE_DETAILS ("Подробнее"),
    BACK ("Назад"),
    CHOOSE ("Выбрать"),
    DELETE ("Удалить"),
    NAME_GIFT("/ "),
    STATE (" "),
    STATE_DB ("Активно"),
    FOR_YOURESELF ("Свой список пожеланий"),
    FOR_ANOTHER ("Для другого человека");

    private final String command;

    COMMANDS (String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}


