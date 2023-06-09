package tn.esprit.spring.pidevgestionconge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Demande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDemande;
    private Date dateDebut;
    private  Date dateFin;
    private Integer nbrJour;
    private String status;


    @Enumerated(EnumType.STRING)
    private TypeDemande typeDemande;

    @ManyToOne
    @JsonIgnore
    Utilisateur utilisateur;
}
