package tn.esprit.spring.pidevgestionconge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.pidevgestionconge.entity.Utilisateur;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateur,Integer> {

    //FIND USER BY EMAIL
    Optional<Utilisateur>findByEmail(String email);

}
