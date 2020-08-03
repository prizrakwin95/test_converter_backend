package app.valuteservice;

import app.model.ValCurs;

import java.util.Date;

public interface ValuteService {
    ValCurs getValute(Date date);

    void saveValute(ValCurs curs);
}
