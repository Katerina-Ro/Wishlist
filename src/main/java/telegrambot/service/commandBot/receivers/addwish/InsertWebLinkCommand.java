package telegrambot.service.commandBot.receivers.addwish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.entities.WebLinks;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.entityservice.TelegramUserService;
import telegrambot.service.entityservice.WebLinkService;
import telegrambot.service.commandBot.Command;
import telegrambot.service.entityservice.WishService;

@Service
public class InsertWebLinkCommand implements Command {
    private static final String SPARKLES =  String.valueOf(Character.toChars(0x2728));
    private static final String STATUS_WISH_GIFT_OWNER = "Ваше пожаление записано " + SPARKLES +
            " Его видят все, у кого активен этот бот. \nВы можете переслать ссылку на этот бот другим, " +
            "чтобы они увидели список Ваших пожеланий либо сделать пожелание неактивным на какое-то время " +
            "(если пожелание неактивно, то его никто, кроме Вас не видит";
    private final WebLinkService webLinkService;
    private final InsertProductDescriptionToDBCommand insertProductDescriptionToDBCommand;
    private final WishService wishService;

    @Autowired
    public InsertWebLinkCommand(WebLinkService webLinkService, InsertProductDescriptionToDBCommand
            insertProductDescriptionToDBCommand, WishService wishService) {
        this.webLinkService = webLinkService;
        this.insertProductDescriptionToDBCommand = insertProductDescriptionToDBCommand;
        this.wishService = wishService;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {

        //SendMessage messageWebLink = new SendMessage();
       // String webLink = ;
        //ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();

        System.out.println("подарок до внесения ссылки "+ InsertNameGiftToDBCommand.getGift());
        System.out.println("webLink = " + update.getMessage().getText());
        System.out.println("insertProductDescriptionToDB.getInsertNameGiftToDB().getGift()" + InsertNameGiftToDBCommand.getGift());
        webLinkService.saveWebLink(update.getMessage().getText(), InsertNameGiftToDBCommand.getGift());
        WebLinks link = webLinkService.getWebLink(InsertNameGiftToDBCommand.getGift());
        InsertNameGiftToDBCommand.getGift().setLink(link);
        System.out.println();
        System.out.println("InsertNameGiftToDBCommand.getGift() после обновления ссылки " +InsertNameGiftToDBCommand.getGift());
        wishService.save(InsertNameGiftToDBCommand.getGift());
        int idGift = InsertNameGiftToDBCommand.getGift().getGiftId();


        InsertNameGiftToDBCommand.setGift(null);

       // messageWebLink.setChatId(update.getMessage().getChatId())
         //       .setText(WEB_LINK);
        System.out.println("вышел из метода webLinkService.saveWebLink(update.getMessage().getText(");
        //messageWebLink.setReplyMarkup(forceReplyKeyboard.setSelective(true));

        long chatIdUser;
        if (update.hasCallbackQuery()) {
            System.out.println("это " + update.hasCallbackQuery());
            chatIdUser = update.getCallbackQuery().getMessage().getChatId();
            System.out.println("chatIdUser " + chatIdUser);
        } else {
            System.out.println("это else {");
            chatIdUser = update.getMessage().getChatId();
            System.out.println("chatIdUser " + chatIdUser);
        }

        SendMessage sendMessage = new SendMessage()
               // .setReplyToMessageId((int) chatIdUser)
                .enableHtml(true)
                .setChatId(chatIdUser)
                .setText(STATUS_WISH_GIFT_OWNER)
                .setReplyMarkup(MakerInlineKeyboardMarkupUtils.getChangeStatusGiftOwnInlineKeyboardMarkup(idGift));
        /*
        if (update.getMessage().isReply()){
            sendMessage.setReplyMarkup(forceReplyKeyboard.setSelective(true));
        } */
        return sendMessage;
    }
}
