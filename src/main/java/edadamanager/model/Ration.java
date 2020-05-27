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
    private List<Day> days = new ArrayList<>();

    @Transient
    private Integer qdays = 0;

    /**
     * Вычисление количества калорий на весь рацион
     */
    public Double calcCalories() {
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
    public Double calcDayCalory(Day day) {
        double cal = 0.;
        for (User user: users) {
            cal+=(user.calcBase()*day.calcCaloryCoeff());
        }
        return cal;
    }

    /**
     * Преобразование массива участников к строке
     */
    public String usersToString() {
        StringBuilder s = new StringBuilder();
        for (User a: users) {
            s.append(a.toString());
            s.append(", ");
        }
        if (s.length() <= 2) return "";
        return s.substring(0, s.length()-2);
    }

    /**
     * Преобразование массива инвентаря к строке
     */
    public String inventoriesToString() {
        StringBuilder s = new StringBuilder();
        for (Inventory inventory: inventories) {
            s.append(inventory.toString());
            s.append(", ");
        }
        if (s.length() <= 2) return "";
        return s.substring(0, s.length()-2);
    }

    /**
     * Преобразование массива ингредиетнов к строке
     */
    public String ingrToString() {
        StringBuilder s = new StringBuilder();
        for (Ingredient a: ingredients) {
            s.append(a.toString());
            s.append(", ");
        }
        if (s.length() <= 2) return "";
        return s.substring(0, s.length()-2);
    }


}
