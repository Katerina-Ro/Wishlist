package telegrambot.service.utils;

public enum COMMANDS {
    INFO("/info"),
    START("/start"),
    ADD_WISH ("/Добавить пожелание"),
    WISHLIST ("/Список подарков"),
    CHANGE_WISH ("/Изменить пожелание"),
    YES ("/Да"),
    NO ("/Нет"),
    SEND ("/Отправить"),
    MORE_DETAILS ("/Подробнее"),
    BACK ("/Назад"),
    CHOOSE ("/Выбрать"),


    private String command;

    COMMANDS (String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}


