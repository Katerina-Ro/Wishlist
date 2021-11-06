package telegrambot.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telegrambot.modelEntities.TelegramUser;
import telegrambot.repository.util.TelegramRepositoryUtil;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class TelegramUserRepositoryImpl implements TelegramUserRepository {

    @Autowired
    @Setter
    private TelegramRepositoryUtil telegramRepositoryUtil;

    private Session session;

    @Autowired
    public Session setSession (SessionFactory sessionFactory) {
        assert sessionFactory != null;
        return this.session = sessionFactory.getCurrentSession();
    }

    @Override
    public List<TelegramUser> findAllByActiveTrue() {
        List<TelegramUser> telegramUserList = new ArrayList<>();
        String sqlAllByActiveTrue = "SELECT Name, chat_id, Active FROM gift_owner WHERE Active = 1";
        ScrollableResults resultAllByActiveTrue = session.createSQLQuery(sqlAllByActiveTrue).scroll();

        // здесь попробовать написать через лямбду
        while (resultAllByActiveTrue.next()) {
            TelegramUser telegramUser = new TelegramUser();
            telegramUser.setName(resultAllByActiveTrue.getString(1));
            telegramUser.setChatId(resultAllByActiveTrue.getString(2));
            telegramUser.setActive(resultAllByActiveTrue.getBoolean(3));
            telegramUserList.add(telegramUser);
        }
        return telegramUserList;
    }

    @Override
    public List<TelegramUser> findAllByActiveFalse() {
        List<TelegramUser> telegramUserList = new ArrayList<>();
        String sqlAllByActiveFalse = "SELECT Name, chat_id, Active FROM gift_owner WHERE Active = 0";
        ScrollableResults resultAllByActiveFalse = session.createSQLQuery(sqlAllByActiveFalse).scroll();

        // здесь попробовать написать через лямбду
        while (resultAllByActiveFalse.next()) {
            TelegramUser telegramUser = new TelegramUser();
            telegramUser.setName(resultAllByActiveFalse.getString(1));
            telegramUser.setChatId(resultAllByActiveFalse.getString(2));
            telegramUser.setActive(resultAllByActiveFalse.getBoolean(3));
            telegramUserList.add(telegramUser);
        }
        return telegramUserList;
    }

    @Override
    public void createPerson(String name, long chat_id) {
        String sqlInsertPerson = "INSERT into gift_owner (Name, Phone_number) " +
                "VALUES('" + name.trim() + "', '" + chat_id + "')";
        if (!telegramRepositoryUtil.checkChat_id(chat_id)) {
            session.createQuery(sqlInsertPerson);
        }
    }

    @Override
    public void deleteGiftOwner(long chat_id) {
        //Для меня: DELETE Hibernate - это ключевоеслово используется для удаления одного или нескольких объектов;
        // только через createSQLQuery
        String sqlGiftOwner = "DELETE FROM gift_owner WHERE chat_id = '" + chat_id + "'";
        if (telegramRepositoryUtil.checkChat_id(chat_id)) {
            session.createSQLQuery(sqlGiftOwner);
            System.out.println("Номер " + chat_id + " удален");
        }
    }
}
