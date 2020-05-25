package edadamanager.controller;

import edadamanager.converter.RecipeWrapper;
import edadamanager.model.*;
import edadamanager.repository.CategoryRepository;
import edadamanager.repository.IngredientRepository;
import edadamanager.repository.InventoryRepository;
import edadamanager.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


@Service("recipeService")
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void save(RecipeWrapper rw) {
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
        recipeRepository.save(recipe);
    }

    public IngredientInRecipe ingrToIngrInRecipe(Integer id, Double quantity) {
        IngredientInRecipe iir = new IngredientInRecipe();
        Ingredient ingredient = ingredientRepository.findOne(id);
        return new IngredientInRecipe(ingredient,quantity);
    }


}
