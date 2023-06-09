package tn.esprit.spring.pidevgestionconge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.pidevgestionconge.entity.Calendrier;

@Repository
public interface CalendrierRepo extends JpaRepository<Calendrier,Integer> {

}
