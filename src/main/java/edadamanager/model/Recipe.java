package edadamanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
public class Recipe {
    private Integer id;

    @NotBlank(message = "Пункт назначения пустой")
    private String destination;
    @NotNull(message = "Пустой номер поезда")@Min(value = 0, message = "Отрицательный номер поезда")
    private Integer number;
    @NotNull(message = "Пустая дата отправления")@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "Пустой час отправления")
    @Min(value = 0, message = "Отрицательный час")
    @Max(value = 23, message = "В сутках 24 часа. Крайнее время отправления 23:59")
    private Integer hours;
    @NotNull(message = "Пустые минуты отправления")
    @Min(value = 0, message = "Отрицательные минуты")
    @Max(value = 59, message = "В часе 60 минут. Крайнее время отправления 23:59")
    private Integer minutes;
    @Min(value = 0, message = "Отрицательное количество мест в купе")
    private Integer numCompartment;
    @Min(value = 0, message = "Отрицательное количество мест в плацкарте")
    private Integer numBerth;

    public Recipe(){}

    public String getWeekday(){
        return date.getDayOfWeek().toString();
    }

    public String getTimeToDeparture(){
        LocalDateTime datetime = LocalDateTime.of( date.getYear() , date.getMonthValue() , date.getDayOfMonth() , hours , minutes , 0 , 0 );
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, datetime);
        if (duration.isZero()) {
            return "Отправляется прямо сейчас";
        }
        String s = "";
        long seconds = Math.abs(duration.getSeconds());
        long hours = seconds / 3600;
        seconds -= (hours * 3600);
        long minutes = seconds / 60;
        seconds -= (minutes * 60);
        long days = hours/24;
        hours -= (24 * days);
        if (days != 0) s+=(days+ " д. ");
        if (hours != 0) s+=(hours+ " ч. ");
        if (minutes != 0) s+=(minutes+ " мин. ");
        if (seconds != 0) s+=(seconds+ " сек. ");
        if (duration.isNegative()) {
            return "Уехал " + s + "назад";
        }
        return s;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", numCompartment=" + numCompartment +
                ", numBerth=" + numBerth +
                '}';
    }
}
