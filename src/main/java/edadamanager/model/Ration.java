package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "rations_users",
            joinColumns = @JoinColumn(name = "ration_ID"),
            inverseJoinColumns = @JoinColumn(name = "user_ID"))
    private List<User> users = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "rations_ingredients",
            joinColumns = @JoinColumn(name = "ration_ID"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_ID"))
    private  List<Ingredient> ingredients = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "rations_inventories",
            joinColumns = @JoinColumn(name = "ration_ID"),
            inverseJoinColumns = @JoinColumn(name = "inventory_ID"))
    private  List<Inventory> inventories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Day> days = new ArrayList<>();

    /**
     * Вычисление количества калорий на весь рацион
     */
    private Double calcCalories() {
        Double cal = 0.;
        for (Day day: days) {
            cal+=calcDayCalory(day);
        }
        return cal;
    }

    /**
     * Вычисление количества калорий на день
     * @param day День для которого вычисляются калории
     */
    private Double calcDayCalory(Day day) {
        double cal = 0.;
        for (User user: users) {
            cal+=(user.calcBase()*day.calcCaloryCoeff());
        }
        return cal;
    }

}
