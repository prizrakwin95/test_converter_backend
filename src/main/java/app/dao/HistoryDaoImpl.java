package app.dao;

import app.model.History;
import app.model.User;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HistoryDaoImpl implements HistoryDao {
    @Override
    public void saveHistory(History history) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(history);
        tx1.commit();
        session.close();
    }

    @Override
    public List<History> readHistory(User user){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From "+ History.class.getSimpleName()+" where user_id = :user_id");
        query.setParameter("user_id", user.getId());
        List<History> historyList = (List<History>) query.getResultList();
        session.close();
        return historyList;
    }
}
