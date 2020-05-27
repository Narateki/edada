package edadamanager.converter;

import edadamanager.model.Inventory;
import edadamanager.model.Recipe;
import edadamanager.repository.InventoryRepository;
import edadamanager.repository.RecipeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@NoArgsConstructor
public class IdsToRecipes implements Converter<List<Integer>, List<Recipe>> {

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public List<Recipe> convert(List<Integer> ids) {
        return recipeRepository.findAllByIdIn(ids);
    }
}
