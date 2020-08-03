package app.dao;

import app.model.ValCurs;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ValuteDaoImpl implements ValuteDao{

    @Override
    public boolean saveValute(ValCurs curs) {
        try{
            Integer day = Integer.valueOf(curs.date.split("\\.")[0]);
            Integer month = Integer.valueOf(curs.date.split("\\.")[1])-1;
            Integer year = Integer.valueOf(curs.date.split("\\.")[2]);
            GregorianCalendar calendar = new GregorianCalendar(year,month,day);
            if(readValute(calendar.getTime()) == null){
                Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                Transaction tx1 = session.beginTransaction();
                session.save(curs);
                tx1.commit();
                session.close();
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ValCurs readValute(Date date){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("From "+ ValCurs.class.getSimpleName()+" where date = :date");
            String dateString = "";
            if(date == null){
                dateString = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
            }else{
                dateString = new SimpleDateFormat("dd.MM.yyyy").format(date);
            }
            query.setParameter("date", dateString);
            ValCurs curs = (ValCurs) query.getSingleResult();
            if(curs != null && dateString.equals(curs.date)){
                return curs;
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
