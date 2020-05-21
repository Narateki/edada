package edadamanager.repository;

import edadamanager.model.Recipe;
import edadamanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    <S extends User> S save(S entity);

    public List<User> findAll();

    public User findByUsername(String username);
}
