package edadamanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class Ration {
    private Integer id;

    private String name;
    private List<User> users;
    private  List<Ingredient> ingredients;
    private  List<Inventory> inventories;
    private List<Day> days;
}
