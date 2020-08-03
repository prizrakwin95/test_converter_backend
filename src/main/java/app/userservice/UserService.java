package app.userservice;

import app.dao.UserDao;
import app.dao.UserDaoImpl;
import app.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao usersDao = new UserDaoImpl();

    public UserService() {
    }

    public User findUser(User user) {
        if(user == null || user.getName() == null){
            return null;
        }
        return usersDao.read(user);
    }

    public boolean saveUser(User user) {
        boolean isOk = false;
        if(user == null || user.getName() == null){
            return false;
        }
        try {
            isOk = usersDao.create(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

}
