package edadamanager.repository;

import edadamanager.model.Inventory;
import edadamanager.model.Recipe;
import edadamanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    <S extends User> S save(S entity);

    public List<User> findAll();

    Set<User> findAllByIdIn(@Param( "ids" ) Set<Integer> ids );

    public User findByUsername(String username);
}
