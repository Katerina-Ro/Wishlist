package telegrambot.repository.util;

import lombok.Getter;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс. Содержит часто употребляемые методы: проверка и получение информации из БД
 */
@Component
public class TelegramRepositoryUtil {

    @Getter
    private Session session;

    @Autowired
    public Session setSession (SessionFactory sessionFactory) {
        assert sessionFactory != null;
        return this.session = sessionFactory.getCurrentSession();
    }

    /**
     * Получаем имя, к которому относится  chat_id
     * @param chat_id - номер  chat_id, получаемый из телеграм-бота
     * @return имя в виде строки
     */
    public List<String> getNamePerson(String chat_id) {
        List<String> listNameUser = new ArrayList<>();
        String sqlSelectName = "SELECT Name FROM gift_owner WHERE chat_id = '" + chat_id + "'";
        ScrollableResults resultSelectName = session.createSQLQuery(sqlSelectName).scroll();
        while (resultSelectName.next()){
            listNameUser.add(resultSelectName.getString(1));
        }
        return listNameUser;
    }

    /**
     * Проверяем наличие телефона в базе.
     * @param chat_id - chat_id из бота, который нужно проверить
     * @return - если указанный номер уже есть, возвращает checkedPhoneNumber = true
     */
    public boolean checkChat_id(long chat_id) {
        boolean checkedChat_id = false;
        String sqlSelectChat_id = "SELECT chat_id FROM gift_owner";

        ScrollableResults resultSelectChat_id = session.createSQLQuery(sqlSelectChat_id).scroll();
        while (resultSelectChat_id.next()) {
            if (resultSelectChat_id.getString(2).equals(String.valueOf(chat_id))) {
                System.out.println("Пользователь с таким номером " + chat_id + " уже занесен");
                checkedChat_id = true;
            }
        }
        return checkedChat_id;
    }

    /**
     * Получаем chat_id из базы данных по имени
     * @param name - имя, по которому нужно получить chat_id
     * @return chat_id в виде long
     */
    public long getChat_id(String name){
        long chat_id = 0;
        String sqlSelectChat_id = "SELECT chat_id FROM gift_owner WHERE Name = '" + name + "'";
        ScrollableResults resultSelectChat_id = session.createSQLQuery(sqlSelectChat_id).scroll();
        while (resultSelectChat_id.next()) {
            chat_id = Long.parseLong(resultSelectChat_id.getString(2));
            }
        return chat_id;
    }

    /**
     * Получеам idGift (number) подарка для дальнейших операций
     * @param nameGift - наименование подарка
     * @param chat_id - chat_id владельца подарка
     * @return - возвращает number из таблицы gift
     */


    //Переписать метод, т.к. его параментры у меня нигде в теле метода не участвуют


    public int getIdGift(String nameGift, long chat_id){
        int idGift = 0;
        String sqlSelectidGift = "SELECT Number, Name_gift, chat_id_owner FROM gift";
        ScrollableResults resultSelectidGift = session.createSQLQuery(sqlSelectidGift).scroll();
        while (resultSelectidGift.next()) {
            if (nameGift.trim().equalsIgnoreCase(resultSelectidGift.getString(5)) && String.valueOf(chat_id).equals(resultSelectidGift.getString(7))){
                idGift = resultSelectidGift.getInteger(1);
            }
        }
        return idGift;
    }
}
