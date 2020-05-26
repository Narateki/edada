package edadamanager.wrapper.set;

import edadamanager.model.Inventory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InventorySet {
    Set<Inventory> inventorySet = new HashSet<>();
}
