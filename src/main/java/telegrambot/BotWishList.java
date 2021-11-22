package telegrambot;

import org.springframework.boot.SpringApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotWishList {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(BotWishList.class, args);
    }
}