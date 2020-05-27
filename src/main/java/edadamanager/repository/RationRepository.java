package edadamanager.repository;


import edadamanager.model.Ration;
import edadamanager.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;

public interface RationRepository extends JpaRepository<Ration, Integer> {
    <S extends Ration> S save(S entity);

    @Query("select r from Ration as r")
    Page<Ration> findAllPages(Pageable page);

    Ration findById(Integer id);
}
