package edadamanager.repository;

import edadamanager.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    <S extends Recipe> S save(S entity);
}
