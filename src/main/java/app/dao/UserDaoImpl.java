package app.dao;

import app.model.User;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public boolean create(User user) {
        if(read(user) == null){
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            session.close();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User read(User user) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("From "+User.class.getSimpleName()+" where name = :name");
            query.setParameter("name", user.getName());
            User userDao = (User) query.getSingleResult();
            if(userDao != null && userDao.getPassword().equals(user.getPassword())){
                return userDao;
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
