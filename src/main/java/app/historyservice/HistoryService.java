package app.historyservice;

import app.model.History;
import app.model.User;

import java.util.List;

public interface HistoryService {
    void saveHistory(History history);

    List<History> readHistory(User user);
}
