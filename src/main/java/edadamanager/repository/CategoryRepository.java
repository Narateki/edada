package edadamanager.repository;

import edadamanager.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    <S extends Category> S save(S entity);
}