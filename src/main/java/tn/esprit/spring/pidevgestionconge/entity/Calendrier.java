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
public class Calendrier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalendrier;
    private Date date;
    private String designation;

    @OneToOne(mappedBy = "calendrier")
    private Utilisateur utilisateur;
}
