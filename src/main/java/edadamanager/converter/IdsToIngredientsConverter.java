package edadamanager.converter;

import edadamanager.model.Ingredient;
import edadamanager.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

public class IdsToIngredientsConverter implements Converter<Set<Integer>, Set<Ingredient>> {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Set<Ingredient> convert(Set<Integer> ids) {
        return this.ingredientRepository.findAllByIdIn(ids);
    }
}
