package app.dao;

import app.model.ValCurs;

import java.util.Date;

public interface ValuteDao {
    boolean saveValute(ValCurs curs);

    ValCurs readValute(Date date);
}
