package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {

    boolean create(User user);

//    List<User> readAll();

    User read(User user);

}
