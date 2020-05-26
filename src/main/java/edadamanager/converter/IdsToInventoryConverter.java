package edadamanager.converter;

import edadamanager.model.Ingredient;
import edadamanager.model.Inventory;
import edadamanager.repository.IngredientRepository;
import edadamanager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

public class IdsToInventoryConverter  implements Converter<Set<Integer>, Set<Inventory>> {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Set<Inventory> convert(Set<Integer> ids) {
        return this.inventoryRepository.findAllByIdIn(ids);
    }
}
