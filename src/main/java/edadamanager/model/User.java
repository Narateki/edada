package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;
    private String hashpass;
    private Double weight;
    private Double height;
    private LocalDate birthday;
    private Boolean sex; // 0 - male, 1 - female
//    private List<Ration> rations;


    public User(String login) {
        this.login = login;
    }

    /**
     * Возраст юзера
     */
    private long getAge() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime datetime = LocalDateTime.of( birthday.getYear() , birthday.getMonthValue() , birthday.getDayOfMonth(), 0,0);
        Duration duration = Duration.between(now, datetime);
        return Math.abs(duration.getSeconds())/365/24/60/60;
    }

    /**
     * Базовое количество калорий для юзера на один день
     */
    public Double calcBase() {
        return (9.99*weight+6.25*height-4.92*getAge())*1.2;
    }
}
