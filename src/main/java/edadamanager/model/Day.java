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

    @ManyToOne()
    @JoinColumn(name = "breakfast_id")
    private Recipe breakfast;

    @ManyToOne()
    @JoinColumn(name = "lunch_id")
    private Recipe lunch;

    @ManyToOne()
    @JoinColumn(name = "dinner_id")
    private Recipe dinner;

    public Double calcCaloryCoeff() {
        return (1+mileage/60)*(1+masl/10000)*(1+0.05*totalRise/200);
    }

    public Day(Double mileage, Double masl, Double totalRise) {
        this.mileage = mileage;
        this.masl = masl;
        this.totalRise = totalRise;
    }
}
