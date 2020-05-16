package edadamanager.controller;

import edadamanager.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service("recipeService")
public class RecipeService {

    private List<Recipe> recipies = new LinkedList();

    public List<Recipe> findAll() {
        return this.recipies;
    }

    public void save(Recipe recipe) {
        this.recipies.add(recipe);
    }

    private Recipe createRecipe(Integer id, String name, String description, Category category,Map<Ingredient,
            Double> ingredients, List<Inventory> inventories, List<Review> reviews) {

        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setCategory(category);
//        recipe.setIngredients(ingredients);
//        recipe.setInventories(inventories);
//        recipe.setReviews(reviews);

        return recipe;
    }

}
