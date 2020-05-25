package edadamanager.model;

import edadamanager.converter.RecipeWrapper;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Название не введено")
    private String name;
    @NotBlank(message = "Описание не добавлено")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredientsInRecipe_id")
    private Set<IngredientInRecipe> ingredients = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipes_inventories",
            joinColumns = @JoinColumn(name = "recipe_ID"),
            inverseJoinColumns = @JoinColumn(name = "inventory_ID"))
    private Set<Inventory> inventories = new HashSet<>();

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "recipes_reviews",
//            joinColumns = @JoinColumn(name = "recipe_ID"),
//            inverseJoinColumns = @JoinColumn(name = "review_ID"))
//    private Set<Review> reviews = new HashSet<>();

    public Recipe(String name, String description, Category category, Set<IngredientInRecipe> ingredients, Set<Inventory> inventories) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.inventories = inventories;
    }


    /**
     * Вычисление калорийности всего рецепта в первоначальном виде
     */
    public Double calcCalory() {
        double cal = 0.;
        for (IngredientInRecipe ingr: ingredients) {
            cal+=(ingr.getIngredient().getCalory()*ingr.getAmount());
        }
        return cal;
    }

    /**
     * Вычисление количества ингредиентов для заданного количества калорий
     * @param calory Калории, для которых нужно вычислить ингредиенты
     * @return Количество каждого из ингредиентов для получения заданного числа калорий
     */
    public Map<Ingredient, Double> calcIngredientsAmount(Double calory) {
        Map<Ingredient, Double> amounts= new HashMap<>();
        Double recCalory = calcCalory();
        Double proportion = 0.;
        if (recCalory != 0) proportion = calory/recCalory;
        for (IngredientInRecipe ingredient: ingredients) {
            amounts.put(ingredient.getIngredient(), ingredient.getAmount()*proportion);
        }
        return amounts;
    }

    /**
     * Вычисление среднего рейтинга по всем отзывам
     */
//    public Double getAvgRate() {
//        Double sum = 0.;
//        for (Review review: reviews) {
//            sum+=review.getRate();
//        }
//        if (reviews.size() == 0 || sum == 0) return 0.;
//        return  sum/reviews.size();
//    }

    /**
     * Преобразование массива ингрединентов к строке
     */
    public String ingredietsToString() {
        StringBuilder s = new StringBuilder();
        for (IngredientInRecipe ingredient: ingredients) {
            s.append(ingredient.getIngredient().toString());
            s.append(", ");
        }
        if (s.length() <= 2) return "";
        return s.substring(0, s.length()-2);
    }

    /**
     * Преобразование массива инвентаря к строке
     */
    public String inventoriesToString() {
        StringBuilder s = new StringBuilder();
        for (Inventory inventory: inventories) {
            s.append(inventory.toString());
            s.append(", ");
        }
        if (s.length() <= 2) return "";
        return s.substring(0, s.length()-2);
    }

//    public Recipe(String name){
//        this.name = name;
//        this.description = "nonono";
//        this.category = new Category("catt");
//        Ingredient ingredient = new Ingredient("ingr", 50);
//        Ingredient ingredient2 = new Ingredient("ingr2", 90);
//        Set<Ingredient> ingredientt = new HashSet<>();
//        ingredientt.add(ingredient);
//        ingredientt.add(ingredient2);
//
//
//        for (Ingredient ingredienttt: ingredientt) {
//            IngredientInRecipe ingr = new IngredientInRecipe(ingredienttt, 10.);
//            ingredients.add(ingr);
//        }
//
//        Inventory inventory = new Inventory("inv1");
//        Set<Inventory> inventories = new HashSet<>();
//        inventories.add(inventory);
//        this.inventories = inventories;
//
//        User user = new User("user");
//
//        Review review = new Review(user);
//        Set<Review> reviews = new HashSet<>();
//        reviews.add(review);
//        this.reviews = reviews;
//    }


}
