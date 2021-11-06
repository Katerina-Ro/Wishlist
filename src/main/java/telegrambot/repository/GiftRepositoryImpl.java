package telegrambot.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import telegrambot.modelEntities.Gift;
import telegrambot.modelEntities.StatusGift.ChangeGiftStatus;
import telegrambot.repository.util.TelegramRepositoryUtil;

@Getter
public class GiftRepositoryImpl implements GiftRepository{

    @Autowired
    @Setter
    private TelegramUserRepositoryImpl telegramUserRepository;

    @Autowired
    @Setter
    private TelegramRepositoryUtil telegramRepositoryUtil;

    @Autowired
    @Setter
    private Gift giftCurrent;

    @Autowired
    @Setter
    private ChangeGiftStatus changeGiftStatus;

    private Session session;

    @Autowired
    public Session setSession (SessionFactory sessionFactory) {
        assert sessionFactory != null;
        return this.session = sessionFactory.getCurrentSession();
    }

    @Override
    public void createGift(String name, String nameGift, long chat_id, String webLink, String comment, String giftStatus) {
        String sqlInsertGift = "INSERT into gift (Gift_status_gift_owner, Name_gift, Product_description, " +
                "Phone_number_owner) VALUES ('" + giftStatus.trim() + "','" + nameGift.trim() + "','" + comment.trim()
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
    public void updateStatusGift(long chat_idPresenter, int idGift, long chat_idGigtOwner){
        giftCurrent.setId(idGift);
        new ChangeGiftStatus().setStatusGiftAnother(giftCurrent);

        String sqlUpdateStatusGift = "UPDATE gift SET Gift_status_giving = '" +
                giftCurrent.getStatusGiftAnother().getStatusGift() + "', chat_id_presenter = '"
                + chat_idPresenter +"' WHERE Number = '" + idGift + "' AND chat_id_owner = '"
                + chat_idGigtOwner + "'";
        session.createSQLQuery(sqlUpdateStatusGift);

    // Для меня: Внести метод на изменение статуса своего подарка
    }

    @Override
    public void getInfoGift(long chat_id) {
        String sqlSelectGifts = "SELECT Name_gift FROM gift WHERE chat_id_owner = '" + chat_id + "'";
        ScrollableResults resultSelectGifts = session.createSQLQuery(sqlSelectGifts).scroll();
        while (resultSelectGifts.next()){
            System.out.println(resultSelectGifts.getString(5));
        }
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
