package edadamanager.repository;

import edadamanager.model.Ingredient;
import edadamanager.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
        <S extends Inventory> S save(S entity);
        Set<Inventory> findAllByIdIn(@Param( "ids" ) Set<Integer> ids );
}
