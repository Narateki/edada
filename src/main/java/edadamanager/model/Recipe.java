package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Название не введено")
    private String name;
    @NotBlank(message = "Описание не добавлено")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipes_ingredients",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "recipeId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "ingredientId"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipes_inventories",
            joinColumns = @JoinColumn(name = "recipe_ID"),
            inverseJoinColumns = @JoinColumn(name = "inventory_ID"))
    private Set<Inventory> inventories = new HashSet<>();;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipes_reviews",
            joinColumns = @JoinColumn(name = "recipe_ID"),
            inverseJoinColumns = @JoinColumn(name = "review_ID"))
    private Set<Review> reviews = new HashSet<>();

    // Вот это потом заменить на количество каждого ингридиента
    @Transient
    Double amount = 5.;

    public Recipe(){}

    public Recipe(String name, String description, Category category, Set<Ingredient> ingredients, Set<Inventory> inventories, Set<Review> reviews) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.inventories = inventories;
        this.reviews = reviews;
    }

    /**
     * Вычисление калорийности всего рецепта в первоначальном виде
     */
    public Double calcCalory() {
        Double cal = 0.;
        for (Ingredient ingr: ingredients) {
            cal+=(ingr.getCalory()*amount);
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
        Double proportion = calory/recCalory;
        for (Ingredient ingredient: ingredients) {
            amounts.put(ingredient, amount*proportion);
        }
        return amounts;
    }

    /**
     * Вычисление среднего рейтинга по всем отзывам
     */
    public Double getAvgRate() {
        Double sum = 0.;
        for (Review review: reviews) {
            sum+=review.getRate();
        }
        return  sum/reviews.size();
    }

    public Recipe(String name){
        this.name = name;
        this.description = "olala";
        this.category = new Category("cat");
        Ingredient ingredient = new Ingredient("ingr", 50);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        this.ingredients = ingredients;
//
        Inventory inventory = new Inventory("inv1");
        Set<Inventory> inventories = new HashSet<>();
        inventories.add(inventory);
        this.inventories = inventories;

        User user = new User("user");

        Review review = new Review(user);
        Set<Review> reviews = new HashSet<>();
        reviews.add(review);
        this.reviews = reviews;
    }
}
