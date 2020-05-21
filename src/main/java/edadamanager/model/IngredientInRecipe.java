package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

//@Embeddable
@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class IngredientInRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private Double amount;

    public IngredientInRecipe(Ingredient ingredient, Double amount) {

        this.ingredient = ingredient;
        this.amount = amount;
    }
}
