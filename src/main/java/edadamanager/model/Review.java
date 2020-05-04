package edadamanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
public class Review {
    private Integer id;

    private User author;
    private Integer rate;
    private String text;
    private LocalDate date;
}
