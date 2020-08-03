package app.dao;

import app.model.History;
import app.model.User;

import java.util.List;

public interface HistoryDao {
    void saveHistory(History history);

    List<History> readHistory(User user);
}
