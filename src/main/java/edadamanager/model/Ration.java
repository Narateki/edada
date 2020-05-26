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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<User> users = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "rations_ingredients",
            joinColumns = @JoinColumn(name = "ration_ID"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_ID"))
    private  Set<Ingredient> ingredients = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "rations_inventories",
            joinColumns = @JoinColumn(name = "ration_ID"),
            inverseJoinColumns = @JoinColumn(name = "inventory_ID"))
    private Set<Inventory> inventories = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Day> days = new HashSet<>();

    @Transient
    private Integer qdays = 0;

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
