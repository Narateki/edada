package edadamanager.repository;

import edadamanager.model.Ingredient;
import edadamanager.model.Inventory;
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

    @Query(
            "SELECT r from Recipe as r inner join r.ingredients as irr inner join r.inventories as ivrr" +
                    " where irr.ingredient in :ingrSet and ivrr in :invSet"
    )
    List<Recipe> searchAllByParams(@Param("ingrSet") Set<Ingredient> ingredientSet, @Param("invSet") Set<Inventory> inventorySet);

    @Query("select r from Recipe as r")
    Page<Recipe> findAllPages(Pageable page);

    Set<Recipe> findAllByIdIn(@Param( "ids" ) Set<Integer> ids );

    List<Recipe> findAllByIdIn(@Param( "ids" ) List<Integer> ids );
}
