package edadamanager.repository;

import edadamanager.model.Category;

import edadamanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    <S extends Category> S save(S entity);

}