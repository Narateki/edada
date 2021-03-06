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
import javax.validation.constraints.Size;
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

    @Size(min=3, max=15)
    private String username;

    private String password;

    @NotNull(message = "Вес не введен")
    private Double weight;
    @NotNull(message = "Рост не введен")
    private Double height;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Boolean sex; // 0 - male, 1 - female
    private String role;


    public User(String login) {
        this.username = login;
    }

    /**
     * Возраст юзера
     */
    private long getAge() {
        if (birthday == null) return 30;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime datetime = LocalDateTime.of( birthday.getYear() , birthday.getMonthValue() , birthday.getDayOfMonth(), 0,0);
        Duration duration = Duration.between(now, datetime);
        return Math.abs(duration.getSeconds())/365/24/60/60;
    }

    /**
     * Базовое количество калорий для юзера на один день
     */
    public Double calcBase() {
        Double w = this.weight;
        Double h = this.height;
        Boolean s = this.sex;
        if (w == null) w = 70.;
        if (h == null) h = 170.;
        if (s == null) s = false;
        Double base = (9.99*w+6.25*h-4.92*getAge())*1.2;
        if (s) return base;
        return base-161;
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
