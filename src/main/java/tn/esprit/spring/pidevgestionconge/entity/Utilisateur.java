package tn.esprit.spring.pidevgestionconge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;
    private String email;
    private  String password;
    private Integer matricule;
    private String nom;
    private  String prenom;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private Set<Demande>demandes;

    @OneToOne
    private Calendrier calendrier;

    @OneToOne
    private Solde solde;


}
