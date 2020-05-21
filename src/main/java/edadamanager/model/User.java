package edadamanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
//@Component
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    private Double weight;
    private Double height;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
    private Boolean sex; // 0 - male, 1 - female
    private String role;
//    private List<Ration> rations;


    public User(String login) {
        this.username = login;
    }

    /**
     * Возраст юзера
     */
    private long getAge() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime datetime = LocalDateTime.of( birthday.getYear() , birthday.getMonthValue() , birthday.getDayOfMonth(), 0,0);
        Duration duration = Duration.between(now, datetime);
        return Math.abs(duration.getSeconds())/365/24/60/60;
    }

    /**
     * Базовое количество калорий для юзера на один день
     */
    public Double calcBase() {
        return (9.99*weight+6.25*height-4.92*getAge())*1.2;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
