package edadamanager.wrapper.set;

import edadamanager.model.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class IngredientsSet {
    Set<Ingredient> ingredientSet = new HashSet<>();
}
