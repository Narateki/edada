package edadamanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class Recipe {
    private Integer id;

    @NotBlank(message = "Name is empty")
    private String name;
    @NotBlank(message = "Decription is empty")
    private String description;
    private Category category;
    private Map<Ingredient, Double> ingredients;
    private List<Inventory> inventories;
    private List<Review> reviews;

    public Recipe(){}

    public Recipe(Integer id, String name, String description, Category category, Map<Ingredient, Double> ingredients, List<Inventory> inventories, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.inventories = inventories;
        this.reviews = reviews;
    }

    public Map<Ingredient, Double> getIngredientsForPortion(Integer n) {
        // Как будем делить блюдо на порции? Либо нужно заранеее указывать кол-во порций в рецепте, либо передавать нужное количество калорий
        return ingredients;
    }
}
