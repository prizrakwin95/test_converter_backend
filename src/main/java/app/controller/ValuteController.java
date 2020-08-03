package app.controller;

import app.model.ValCurs;
import app.valuteservice.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ValuteController {
    private ValuteService valuteService;

    @Autowired
    public ValuteController(ValuteService valuteService) {
        this.valuteService = valuteService;
    }

    @RequestMapping(value = "/valutes/today", method = RequestMethod.GET)
    public ValCurs getTodayValute(){
        ValCurs curs = valuteService.getValute(new Date());
        return curs;
    }
}
