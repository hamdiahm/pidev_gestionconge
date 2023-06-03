package tn.esprit.spring.pidevgestionconge.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Solde implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolde;
    private Double soldeConge;


    @OneToOne(mappedBy = "solde")
    private Utilisateur utilisateur;
}
