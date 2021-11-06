package telegrambot.repository;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class CommentsRepositoryImpl implements CommentsRepository{

    private Session session;

    @Autowired
    public Session setSession (SessionFactory sessionFactory) {
        assert sessionFactory != null;
        return this.session = sessionFactory.getCurrentSession();
    }

    @Override
    public void createComment(long chat_id, String comment, int idGift){
        String sqlInsertComment = "INSERT into comments (Comments, idGift, Phone_number_commentator) " +
                "VALUES ('" + comment.trim() + "', '" + idGift + "', '" + chat_id + "')";
        session.createQuery(sqlInsertComment);
        //Для меня: убрать сам коммент (написан для теста)
        System.out.println(comment + "Ваш комментарий внесен");
    }

    @Override
    public void upDateComment(long chat_id, String comment, int idGift){
        String sqlUpdateComment = "UPDATE comments SET Comments = '" + comment.trim() + "' WHERE idGift = '"
                + idGift + "' AND chat_id_commentator = '" + chat_id + "'";
        session.createSQLQuery(sqlUpdateComment);
        //Для меня: убрать сам коммент (написан для теста)
        System.out.println(comment + "Ваш комментарий обновлен");
    }

    @Override
    public void deleteComment(long chat_id, int idGift){
        String sqlDeleteOwnComment = "DELETE FROM comments WHERE chat_id_commentator = '" + chat_id
                + "' AND idGift = " + idGift;
        //Для меня: Заменить парамет if на кнопку "Да" из бота
        session.createSQLQuery(sqlDeleteOwnComment);
        System.out.println("Ваш комментарий удален");
    }
}
