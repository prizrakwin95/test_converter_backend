package app.valuteservice;


import app.dao.ValuteDao;
import app.dao.ValuteDaoImpl;
import app.model.ValCurs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ValuteServiceImpl implements ValuteService {
    private ValuteDao valuteDao = new ValuteDaoImpl();

    public ValuteServiceImpl() {
    }

    @Override
    public ValCurs getValute(Date date) {
        String uri = "http://www.cbr.ru/scripts/XML_daily.asp";

        if(date != null){
            ValCurs curs = valuteDao.readValute(date);
            if(curs != null){
                return curs;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
            String dateString = simpleDateFormat.format(date);
            uri = uri+"?date_req="+dateString;
        }else{
            date = new Date();
            ValCurs curs = valuteDao.readValute(date);
            if(curs != null){
                return curs;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String dateString = simpleDateFormat.format(date);

        System.out.println("Запрашиваю текущий курс из ЦБРФ на день "+dateString);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri,String.class);
        ValCurs valCurs = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(responseEntity.getBody()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        for (ValCurs.Valute c : valCurs.valutes) {
            c.setValCurs(valCurs);
            c.value = c.value.replace(",",".");
        }
        ValCurs.Valute valute = new ValCurs.Valute();
        valute.name = "Российский рубль";
        valute.charCode = "RUB";
        valute.numCode = 643;
        valute.nominal = 1;
        valute.val_id = "1";
        valute.value = "1";
        valute.setValCurs(valCurs);
        valCurs.valutes.add(valute);
        valuteDao.saveValute(valCurs);
        System.out.println(valCurs.date);
        Integer year = Integer.valueOf(valCurs.date.split("\\.")[2]);
        Integer month = Integer.valueOf(valCurs.date.split("\\.")[1])-1;
        Integer day = Integer.valueOf(valCurs.date.split("\\.")[0]);
        Date cursDate = new GregorianCalendar(year,month,day).getTime();
        valCurs = valuteDao.readValute(cursDate);
        return valCurs;
    }

    @Override
    public void saveValute(ValCurs curs) {
        valuteDao.saveValute(curs);
    }
}
