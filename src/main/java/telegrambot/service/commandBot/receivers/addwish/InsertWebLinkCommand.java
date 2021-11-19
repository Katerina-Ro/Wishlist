package telegrambot.service.commandBot.receivers.addwish;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.service.commandBot.receivers.utils.MakerInlineKeyboardMarkupUtils;
import telegrambot.service.entityservice.WebLinkService;
import telegrambot.service.commandBot.Command;

@Service
public class InsertWebLinkCommand implements Command {
    private static final String SPARKLES =  String.valueOf(Character.toChars(0x2728));
    // кнопка сделать неактивным
    // кнопка внести еще пожелание
    // кнопка главное меню

    @Getter
    private static final String STATUS_WISH_GIFT_OWNER = "Ваше пожаление записано " + SPARKLES +
            " Его видят все, у кого активен этот бот. \nВы можете переслать ссылку на этот бот другим, " +
            "чтобы они увидели список Ваших пожеланий либо сделать пожелание неактивным на какое-то время " +
            "(если пожелание неактивно, то его никто, кроме Вас не видит";

    @Getter
    private final WebLinkService webLinkService;
    @Getter
    private final InsertProductDescriptionToDBCommand insertProductDescriptionToDBCommand;

    @Autowired
    public InsertWebLinkCommand(WebLinkService webLinkService, InsertProductDescriptionToDBCommand insertProductDescriptionToDBCommand) {
        this.webLinkService = webLinkService;
        this.insertProductDescriptionToDBCommand = insertProductDescriptionToDBCommand;
    }

    @Override
    @Transactional
    public SendMessage execute(Update update)  {

        //SendMessage messageWebLink = new SendMessage();
       // String webLink = ;
        //ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();

        System.out.println("подарок до внесения ссылки "+ insertProductDescriptionToDBCommand.getInsertNameGiftToDBCommand().getGift());
System.out.println("webLink = " + update.getMessage().getText());
System.out.println("insertProductDescriptionToDB.getInsertNameGiftToDB().getGift()" + insertProductDescriptionToDBCommand.getInsertNameGiftToDBCommand().getGift());
        webLinkService.saveWebLink(update.getMessage().getText(), insertProductDescriptionToDBCommand.getInsertNameGiftToDBCommand().getGift());

        int idGift = insertProductDescriptionToDBCommand.getInsertNameGiftToDBCommand().getGift().getGiftId();

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
        /*
        return SendMessageUtils.sendMessage(update,STATUS_WISH_GIFT_OWNER).setReplyMarkup(
        MakerInlineKeyboardMarkup.get2x2x3InlineKeyboardMarkup(Buttons. getKeyBoardButtonChangeStatusOwnWish(),
        Buttons.getKeyBoardButtonAddMoreWish(), Buttons.getKeyBoardBackToStart()));*/

}
