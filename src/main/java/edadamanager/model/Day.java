package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double mileage;
    private Double masl; // metres above sea level
    private Double totalRise;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "breakfast_id")
    private Recipe breakfast;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lunch_id")
    private Recipe lunch;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dinner_id")
    private Recipe dinner;

    public Double calcCaloryCoeff() {
        return (1+mileage/60)*(1+masl/10000)*(1+0.05*totalRise/200);
    }
}
