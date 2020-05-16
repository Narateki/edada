//package edadamanager.model;
//
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity(name="RecipesIngredientsEntity")
//@Table(name="recipes_ingredients")
//@NoArgsConstructor
//public class RecipesIngredients implements Serializable{
//    @EmbeddedId
//    public RecipesIngredientsId recipesIngredientsId;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Integer id;
//
//    public RecipesIngredients(Recipe recipe, Ingredient ingredient) {
//        recipesIngredientsId = new RecipesIngredientsId();
//        recipesIngredientsId.recipeId = recipe.getId();
//        recipesIngredientsId.ingredientId = ingredient.getId();
//    }
//
//    @Embeddable
//    public static class RecipesIngredientsId implements Serializable {
//
//        public Integer recipeId;
//        public Integer ingredientId;
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            RecipesIngredientsId recipesIngredientsId = (RecipesIngredientsId) o;
//            return Objects.equals(recipeId, recipesIngredientsId.recipeId) &&
//                    Objects.equals(ingredientId, recipesIngredientsId.ingredientId);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(recipeId, ingredientId);
//        }
//    }
//}
