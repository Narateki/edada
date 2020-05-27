package edadamanager.wrapper;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DayWrapper {
    private Integer id;
    private List<Integer> breakfast = new ArrayList<>();
    private List<Integer> lunch = new ArrayList<>();
    private List<Integer> dinner = new ArrayList<>();
}
