package edadamanager.repository;

import edadamanager.model.Ingredient;
import edadamanager.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    <S extends Recipe> S save(S entity);

    @Query(
            "SELECT r from Recipe as r inner join r.ingredients as irr where irr.ingredient in :ingrSet "
    )
    List<Recipe> searchAllByIngredients(@Param("ingrSet") Set<Ingredient> ingredientSet);

    @Query("select r from Recipe as r")
    Page<Recipe> findAllPages(Pageable page);
}
