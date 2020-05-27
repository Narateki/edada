package edadamanager.controller;

import edadamanager.repository.*;
import edadamanager.wrapper.RationWrapper;
import edadamanager.wrapper.RecipeWrapper;
import edadamanager.wrapper.set.IngredientsSet;
import edadamanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service("recipeService")
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RationRepository rationRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public Page<Recipe> findAllPages(Pageable page) {
        return recipeRepository.findAllPages(page);
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe save(RecipeWrapper rw) {
        Set<IngredientInRecipe> ingredients = new HashSet<>();
        Set<Inventory> inventories = new HashSet<>();
        List<Integer> rwIngrsId = rw.getIngrs_id();
        List<Double> rwIngrsQ = rw.getIngrs_q();
        for (int i = 0; i < rwIngrsId.size(); i++) {
            ingredients.add(ingrToIngrInRecipe(rwIngrsId.get(i), rwIngrsQ.get(i)));
        }
        List<Integer> rwInventId = rw.getInvent_id();
        for (Integer s : rwInventId) {
            Inventory inventory = inventoryRepository.findOne(s);
            inventories.add(inventory);
        }
        Category category = categoryRepository.findOne(rw.getCategory_id());

        Recipe recipe = new Recipe(rw.getName(), rw.getDescription(), category, ingredients, inventories);
        Recipe r = recipeRepository.save(recipe);
        return r;
    }

    public IngredientInRecipe ingrToIngrInRecipe(Integer id, Double quantity) {
        IngredientInRecipe iir = new IngredientInRecipe();
        Ingredient ingredient = ingredientRepository.findOne(id);
        IngredientInRecipe ingredientInRecipe = new IngredientInRecipe(ingredient,quantity);

        return new IngredientInRecipe(ingredient,quantity);
    }


    public List<Recipe> searchAllByIngredients(IngredientsSet ingredientsSet) {
       return recipeRepository.searchAllByIngredients(ingredientsSet.getIngredientSet());
    }

    public List<Recipe> findRecipes(Ration ration) {
        return recipeRepository.searchAllByParams(ration.getIngredients(), ration.getInventories());
    }
}
