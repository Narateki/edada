package edadamanager.converter;

import edadamanager.model.Ingredient;
import edadamanager.model.IngredientInRecipe;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeWrapper {
    private String name;
    private String description;
    private Integer category_id;
    private List<Integer> ingrs_id = new ArrayList<>();
    private List<Integer> invent_id = new ArrayList<>();
    private List<Double> ingrs_q = new ArrayList<>();


}
