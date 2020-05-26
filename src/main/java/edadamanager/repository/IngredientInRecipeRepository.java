package edadamanager.repository;

import edadamanager.model.Ingredient;
import edadamanager.model.IngredientInRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInRecipeRepository extends JpaRepository<IngredientInRecipe, Integer> {

}
