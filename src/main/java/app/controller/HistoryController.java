package app.controller;

import app.historyservice.HistoryService;
import app.model.History;
import app.model.User;
import app.model.ValCurs;
import app.userservice.UserService;
import app.valuteservice.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class HistoryController {
    private HistoryService historyService;
    private UserService userService;
    private ValuteService valuteService;

    @Autowired
    public HistoryController(HistoryService historyService,UserService userService,ValuteService valuteService) {
        this.historyService = historyService;
        this.userService = userService;
        this.valuteService = valuteService;

    }

    @RequestMapping(value = "/history/", method = RequestMethod.POST)
    public List<History> getHistory(@RequestBody User user){//username
        User thisUser = userService.findUser(user);
        List<History> historyList = historyService.readHistory(thisUser);
        return historyList;
    }

    @RequestMapping(value = "/history/add", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> saveHistory(@RequestBody History history){
        try {
            User thisUser = userService.findUser(history.getUser());
            history.setUser(thisUser);
            history.setUser_id(thisUser.getId());
            Integer year = Integer.valueOf(history.getDate().split("\\.")[2]);
            Integer month = Integer.valueOf(history.getDate().split("\\.")[1])-1;
            Integer day = Integer.valueOf(history.getDate().split("\\.")[0]);
            Date date = new GregorianCalendar(year,month,day).getTime();
            ValCurs curs = valuteService.getValute(date);
            history.setCurs_id(curs.getId());
            historyService.saveHistory(history);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
