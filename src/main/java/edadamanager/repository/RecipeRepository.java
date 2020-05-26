package edadamanager.repository;

import edadamanager.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    <S extends Recipe> S save(S entity);


    List<Recipe> findAll();
}
