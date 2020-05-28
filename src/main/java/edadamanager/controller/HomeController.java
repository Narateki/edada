package edadamanager.controller;

import edadamanager.model.Category;
import edadamanager.model.Ingredient;
import edadamanager.model.Inventory;
import edadamanager.model.Recipe;
import edadamanager.repository.CategoryRepository;
import edadamanager.repository.IngredientRepository;
import edadamanager.repository.InventoryRepository;
import edadamanager.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

//    @Autowired
//    private RecipeService recipeService;

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private IngredientRepository ingredientRepository;


    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("title", "Сайт с рецептами для туристов приветствует вас!");
        model.addAttribute("recipe", recipeService.getRandom());
//        Recipe recipe = new Recipe("rec9");
//        recipeService.save(recipe);
//        Category category1 = new Category("Суп");
//        categoryRepository.save(category1);
//        Category category2 = new Category("Каша");
//        categoryRepository.save(category2);
//        Category category3 = new Category("Салат");
//        categoryRepository.save(category3);
//        Category category4 = new Category("Гарнир");
//        categoryRepository.save(category4);
//        Category category5 = new Category("Мясное");
//        categoryRepository.save(category5);
//        Category category6 = new Category("Овощное");
//        categoryRepository.save(category6);
//        Category category7 = new Category("Второе");
//        categoryRepository.save(category7);
//        Inventory inventory1 = new Inventory("Кастрюля");
//        inventoryRepository.save(inventory1);
//        Inventory inventory2 = new Inventory("Сковорода");
//        inventoryRepository.save(inventory2);
//        Inventory inventory3 = new Inventory("Шампур");
//        inventoryRepository.save(inventory3);
//        Inventory inventory4 = new Inventory("Котелок");
//        inventoryRepository.save(inventory4);
//        Ingredient ingredient1 = new Ingredient("Помидор", 1);
//        ingredientRepository.save(ingredient1);
//        Ingredient ingredient2 = new Ingredient("Картофель", 7);
//        ingredientRepository.save(ingredient2);
//        Ingredient ingredient3 = new Ingredient("Мясо", 6);
//        ingredientRepository.save(ingredient3);
//        Ingredient ingredient4 = new Ingredient("Рыба", 3);
//        ingredientRepository.save(ingredient4);
//        Ingredient ingredient5 = new Ingredient("Рис", 8);
//        ingredientRepository.save(ingredient5);
//        Ingredient ingredient6 = new Ingredient("Курица", 3);
//        ingredientRepository.save(ingredient6);
//        Ingredient ingredient7 = new Ingredient("Гречка", 2);
//        ingredientRepository.save(ingredient7);
//        Ingredient ingredient8 = new Ingredient("Макароны", 5);
//        ingredientRepository.save(ingredient8);
//        Ingredient ingredient9 = new Ingredient("Тушенка", 5);
//        ingredientRepository.save(ingredient9);
//        Ingredient ingredient10 = new Ingredient("Сосиска", 4);
//        ingredientRepository.save(ingredient10);

        return "home";
    }

    @RequestMapping("/errors")
    public String errors(Model model){
        model.addAttribute("code", "Такой страницы не существует!");
        return "errors";
    }
}

