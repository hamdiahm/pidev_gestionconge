package tn.esprit.spring.pidevgestionconge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.pidevgestionconge.entity.Demande;

public interface DemandeRepository extends JpaRepository<Demande,Integer> {
}
