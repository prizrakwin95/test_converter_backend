package app.model;


import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "firstvalute")
    private String firstValute;
    @Column(name = "secondvalute")
    private String secondValute;
    @Column(name = "firstsum")
    private Double firstSum;
    @Column(name = "resultsum")
    private Double resultSum;
    @Column(name = "date")
    private String date;
    @Column(name = "curs_id")
    private Integer curs_id;
    @Transient
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirstValute() {
        return firstValute;
    }

    public void setFirstValute(String firstValute) {
        this.firstValute = firstValute;
    }

    public String getSecondValute() {
        return secondValute;
    }

    public void setSecondValute(String secondValute) {
        this.secondValute = secondValute;
    }

    public Double getFirstSum() {
        return firstSum;
    }
    public void setFirstSum(Double firstSum) {
        this.firstSum = firstSum;
    }

    public Double getResultSum() {
        return resultSum;
    }

    public void setResultSum(Double resultSum) {
        this.resultSum = resultSum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCurs_id() {
        return curs_id;
    }

    public void setCurs_id(Integer curs_id) {
        this.curs_id = curs_id;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", firstValute='" + firstValute + '\'' +
                ", secondValute='" + secondValute + '\'' +
                ", firstSum=" + firstSum +
                ", resultSum=" + resultSum +
                ", date='" + date + '\'' +
                ", curs_id=" + curs_id +
                ", user=" + user +
                '}';
    }
}
