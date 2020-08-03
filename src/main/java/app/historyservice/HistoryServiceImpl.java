package app.historyservice;

import app.dao.HistoryDao;
import app.dao.HistoryDaoImpl;
import app.model.History;
import app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private HistoryDao historyDao = new HistoryDaoImpl();

    public HistoryServiceImpl() {
    }

    @Override
    public void saveHistory(History history) {
        historyDao.saveHistory(history);
    }

    @Override
    public List<History> readHistory(User user) {
        return historyDao.readHistory(user);
    }
}
