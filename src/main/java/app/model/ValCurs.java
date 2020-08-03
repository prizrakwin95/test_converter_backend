package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "valcurs")
@XmlRootElement(name = "ValCurs")
public class ValCurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    @XmlAttribute(name = "Date")
    public String date;


    @XmlAttribute(name = "name")
    public String name;

    @OneToMany (mappedBy = "valCurs", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlElement(name = "Valute")
    public List<Valute> valutes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Entity
    @Table(name = "valutes")
    public static class Valute {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
        @JoinColumn(name="date_id")
        @JsonIgnore
        private ValCurs valCurs;

        @Column(name = "valute_id")
        @XmlAttribute(name = "ID")
        public String val_id;

        @Column(name = "name")
        @XmlElement(name = "Name")
        public String name;

        @Column(name = "value")
        @XmlElement(name = "Value")
        public String value;

        @Column(name = "nominal")
        @XmlElement(name = "Nominal")
        public Integer nominal;

        @Column(name = "charcode")
        @XmlElement(name = "CharCode")
        public String charCode;

        @Column(name = "numcode")
        @XmlElement(name = "NumCode")
        public Integer numCode;

        public ValCurs getValCurs() {
            return valCurs;
        }

        public void setValCurs(ValCurs valCurs) {
            this.valCurs = valCurs;
        }

        public Valute() {

        }

        @Override
        public String toString() {
            return "Valute {" +
                    "val_id='" + val_id + '\'' +
                    ", name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", nominal='" + nominal + '\'' +
                    ", charCode='" + charCode + '\'' +
                    ", numCode='" + numCode + '\'' +
                    " }";
        }
    }

    @Override
    public String toString() {
        return "ValCurs{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", valutes=" + valutes +
                '}';
    }
}