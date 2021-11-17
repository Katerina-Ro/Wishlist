package telegrambot.service.commandBot;

import telegrambot.entities.Gift;

public enum COMMANDS {
    INFO("О чем канал?"),
    START("/start"),
    ADD_WISH ("Добавить пожелание"),
    ADD_NAME_USER_TO_DB ("Добавить имя"),
    WISHLIST ("Посмотреть список желаний"),
    CHANGE_WISH ("Изменить пожелание"),
    YES ("Да,удалить"),
    NO ("Нет, не удалять"),
    SEND ("Отправить"),
    MORE_DETAILS ("Подробнее"),
    BACK ("Назад к выбору списка желаний"),
    CHOOSE ("Выбрать"),
    DELETE ("Удалить"),
    NAME_GIFT("/ "),
    STATE_DB ("ACTIVE"),
    STATE_DB_NOT_ACTIVE ("NOT_ACTIVE"),
    FOR_YOURESELF ("Свой список пожеланий"),
    FOR_ANOTHER ("Дарю другим"),
    BUTTON_BACK_TO_START ("Назад, в главное меню"),
    ACTIVE (String.valueOf(Character
            .toChars(0x2713))),
    NOT_ACTIVE (" не выбран");

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


