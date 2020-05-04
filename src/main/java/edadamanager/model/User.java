package edadamanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter
@Setter
public class User {
    private Integer id;

    private String login;
    private String hashpass;
    private Double weight;
    private Double height;
    private LocalDate birthday;
    private Boolean sex; // 0 - male, 1 - female
    private List<Ration> rations;

}
