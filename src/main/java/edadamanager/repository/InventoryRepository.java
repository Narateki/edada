package edadamanager.repository;

import edadamanager.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
        <S extends Inventory> S save(S entity);
}
