package edadamanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Day {
    private Integer id;

    private Double mileage;
    private Double masl; // metres above sea level
    private Double totalRise;
    private Recipe breakfast;
    private Recipe lunch;
    private Recipe dinner;
}
