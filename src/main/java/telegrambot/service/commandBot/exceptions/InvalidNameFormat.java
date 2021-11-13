package telegrambot.service.commandBot.exceptions;

public class InvalidNameFormat extends Exception{
    private String nameUser;

    public InvalidNameFormat(String message, String nameUser) {
        super(message);
        this.nameUser = nameUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @Override
    public String toString() {
        return "Неверный формат имени пользователя " +
                "имя пользователя: " + nameUser + getMessage();
    }
}
