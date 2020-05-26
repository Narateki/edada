package edadamanager.repository;

import edadamanager.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    <S extends Ingredient> S save(S entity);

    Set<Ingredient> findAllByIdIn(@Param( "ids" ) Set<Integer> ids );
}

