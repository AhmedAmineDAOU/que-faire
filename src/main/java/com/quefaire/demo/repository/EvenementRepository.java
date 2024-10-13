package com.quefaire.demo.repository;
import com.quefaire.demo.entity.Evenement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, String> {
    List<Evenement> findByTitle(String title);
}
