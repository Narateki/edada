package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer calory;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "unit_id")
    private Units units;

    public Ingredient(String name, Integer calory) {
        this.name = name;
        this.calory = calory;
    }

    @Override
    public String toString() {
        return name;
    }
}
