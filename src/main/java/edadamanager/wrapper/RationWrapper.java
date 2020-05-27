package edadamanager.wrapper;


import edadamanager.model.Ingredient;
import edadamanager.model.Inventory;
import edadamanager.model.User;
import edadamanager.wrapper.set.DaySet;
import edadamanager.wrapper.set.IngredientsSet;
import edadamanager.wrapper.set.InventorySet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class RationWrapper {
    private Integer id;
    private List<Double> mileage = new ArrayList<>();
    private List<Double> masl = new ArrayList<>();
    private List<Double> totalRise = new ArrayList<>();
}
