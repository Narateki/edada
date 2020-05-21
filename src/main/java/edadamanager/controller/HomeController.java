package edadamanager.controller;

import edadamanager.model.Category;
import edadamanager.model.Inventory;
import edadamanager.model.Recipe;
import edadamanager.repository.CategoryRepository;
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


    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("title", "Сайт с рецептами приветствует вас!");
        Recipe recipe = new Recipe("rec9");
        recipeService.save(recipe);
//        Category category1 = new Category("Суп");
//        categoryRepository.save(category1);
//        Category category2 = new Category("Каша");
//        categoryRepository.save(category2);
//        Category category3 = new Category("Салат");
//        categoryRepository.save(category3);
//        Category category4 = new Category("Гарнир");
//        categoryRepository.save(category4);
//        Category category5 = new Category("Второе");
//        categoryRepository.save(category5);
        Inventory inventory1 = new Inventory("Кастрюля");
        inventoryRepository.save(inventory1);
        Inventory inventory2 = new Inventory("Сковорода");
        inventoryRepository.save(inventory2);
        Inventory inventory3 = new Inventory("Шампур");
        inventoryRepository.save(inventory3);
        return "home";
    }

}

