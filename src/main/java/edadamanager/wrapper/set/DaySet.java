package edadamanager.wrapper.set;

import edadamanager.model.Day;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DaySet {
    Set<Day> daySet = new HashSet<>();
}
