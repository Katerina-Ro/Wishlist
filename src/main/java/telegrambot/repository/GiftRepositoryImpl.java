package telegrambot.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import telegrambot.modelEntities.Gift;
import telegrambot.modelEntities.StatusGift.ChangeGiftStatus;
import telegrambot.modelEntities.StatusGift.STATUS_GIFT;
import telegrambot.repository.util.TelegramRepositoryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Getter
public class GiftRepositoryImpl implements GiftRepository{

    private TelegramUserRepository telegramUserRepository;
    private TelegramRepositoryUtil telegramRepositoryUtil;

    @Autowired
    @Setter
    private Gift giftCurrent;

    @Autowired
    @Setter
    private ChangeGiftStatus changeGiftStatus;

    private final Session session;

    @Autowired
    public GiftRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.getCurrentSession();
    }

    /*
    @Autowired
    public Session setSession (SessionFactory sessionFactory) {
        //assert sessionFactory != null;
        return this.session = sessionFactory.getCurrentSession();
    } */

    @Autowired
    public void setTelegramUserRepository(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Autowired
    public void setTelegramRepositoryUtil(TelegramRepositoryUtil telegramRepositoryUtil) {
        this.telegramRepositoryUtil = telegramRepositoryUtil;
    }

    @Override
    public void createGift(String name, String nameGift, long chat_id, String webLink, String comment, boolean giftStatus) throws SQLException {
        String sqlInsertGift = "INSERT into gift (Gift_status_gift_owner, Name_gift, Product_description, " +
                "Phone_number_owner) VALUES ('" + giftStatus + "','" + nameGift.trim() + "','" + comment.trim()
                + "','" + chat_id + "')";
        if (!telegramRepositoryUtil.checkChat_id(chat_id)) {
            telegramUserRepository.createPerson(name.trim(), chat_id);
        }
        session.createQuery(sqlInsertGift); // заносим информацию о подарке, но без ссылки (web-link):
                                            // ссылка хранится в другой таблице
        String sqlInsertWebLink = "INSERT into links (Weblink, idGift, Phone_number_owner) VALUES ('" + webLink.trim()
                + "','" + telegramRepositoryUtil.getIdGift(nameGift, chat_id) + "', '" + chat_id + "')";
        session.createQuery(sqlInsertWebLink); // заносим ссылку на подарок в таблицу Comments
    }

    @Override
    public void updateGift(long chat_id, String nameGift, String comment, String webLink) {
        int idGift = telegramRepositoryUtil.getIdGift(nameGift,chat_id);
        String sqlUpdateGift = "UPDATE gift SET Name_gift = '" + nameGift.trim() + "', Product_description = '"
                + comment.trim() + "' WHERE Number = " + idGift;
        String sqlUpdateWebLink = "UPDATE links SET Weblink = '" + webLink.trim() + "' WHERE idGift = '" + idGift
                + "' AND chat_id_owner = '" + chat_id + "'";
        System.out.println("Вы уверены, что хотите внести изменения в пожелание к подарку " + nameGift.trim() + " ?");
        //Для меня: здесь переписать условие на кнопку "Да"
        if (true) {
            session.createSQLQuery(sqlUpdateGift);
            session.createSQLQuery(sqlUpdateWebLink);
            System.out.println("Изменения внесены");
        }
        //Для меня: внести действия, если передумал вносить изменения. Может, функция назад???
    }

    @Override
    public String updateStatusAnotherGift(long chat_idPresenter, int idGift, long chat_idGigtOwner){
        String statusGift;
        giftCurrent.setId(idGift);
        statusGift = new ChangeGiftStatus().changeStatusGiftAnother(giftCurrent);

        String sqlUpdateStatusGift = "UPDATE gift SET Gift_status_giving = '" +
                giftCurrent.getStatusGiftAnother(STATUS_GIFT.NOT_ACTIVE).getStatusGift() + "', chat_id_presenter = '"
                + chat_idPresenter +"' WHERE Number = '" + idGift + "' AND chat_id_owner = '"
                + chat_idGigtOwner + "'";
        session.createSQLQuery(sqlUpdateStatusGift);

        return statusGift;
    // Для меня: Внести метод на изменение статуса своего подарка
    }

    @Override
    public Collection<Gift> getInfoGift(long chat_id) {
        List<Gift> giftList = new ArrayList<>();
        Gift gift = new Gift();
        String sqlSelectGifts = "SELECT Name_gift FROM gift WHERE chat_id_owner = '" + chat_id + "'";
        ScrollableResults resultSelectGifts = session.createSQLQuery(sqlSelectGifts).scroll();
        while (resultSelectGifts.next()){
            gift.setNameGift(resultSelectGifts.getString(5));
            gift.setCommentGift(resultSelectGifts.getString(6));
            gift.setStatusGiftOwn(STATUS_GIFT.valueOf(resultSelectGifts.getString(3)));
            gift.setStatusGiftAnother(STATUS_GIFT.valueOf(resultSelectGifts.getString(4)));
            giftList.add(gift);
            //System.out.println(resultSelectGifts.getString(5));
        }
        return giftList;
    }

    @Override
    public void deleteGift(long chat_id, String nameGift){
        String sqlDeleteGift = "DELETE FROM gift WHERE Name_gift = '" + nameGift + "' AND chat_id_owner = '"
                + chat_id +"'";
        System.out.println("Вы уверены, что хотите удалить этот подарок " + nameGift + "?");
        //Для меня: Заменить парамет if на кнопку "Да" из бота
        if (true) {
            session.createSQLQuery(sqlDeleteGift);
            System.out.println("Подарок " + nameGift + " удален");
        }
    }
}
