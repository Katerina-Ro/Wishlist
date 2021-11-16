
/*package telegrambot.service.commandBot.receivers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telegrambot.entities.Gift;
import telegrambot.service.commandBot.COMMANDS;
import telegrambot.service.entityservice.WishService;

import java.util.List;

// elfkbnm tuj


public class SerchUtils {
    private final WishService wishService;

    @Autowired
    public SerchUtils(WishService wishService) {
        this.wishService = wishService;
    }

    public String getCommands(String receivedName, long chatIdUser){
        List<Gift> listGifts = wishService.getInfoGifts(chatIdUser);
        for (Gift g: listGifts){
           if (g.getNameGift().equalsIgnoreCase(receivedName)){
               return COMMANDS.NAME_GIFT.getCommand();
           }
       }
        return "Такой команды нет";
    }
}
*/