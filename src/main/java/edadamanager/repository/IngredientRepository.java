package edadamanager.repository;

import edadamanager.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    <S extends Ingredient> S save(S entity);

}

