package edadamanager.repository;


import edadamanager.model.Ration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface RationRepository extends JpaRepository<Ration, Integer> {
    <S extends Ration> S save(S entity);


    Ration findById(Integer id);
}
