package telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Аннотация, которая объединяет в себя @Configuration, @EnableAutoConfiguration, @ComponentScan
@SpringBootApplication
public class BotWishList {
    public static void main(String[] args) {


        ApiContextInitializer.init();
        SpringApplication.run(BotWishList.class, args);

        /*ApplicationContext context =
                new AnnotationConfigApplicationContext("telegrambot");
        System.out.println(context.getBean("dataSource"));*/
    }
}