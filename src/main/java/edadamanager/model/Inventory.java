package edadamanager.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Inventory {
    private Integer id;

    private String name;
}
