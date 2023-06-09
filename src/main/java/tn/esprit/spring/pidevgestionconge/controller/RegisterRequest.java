package tn.esprit.spring.pidevgestionconge.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.spring.pidevgestionconge.entity.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private  String password;
    private Integer matricule;
    private String nom;
    private  String prenom;
    private Role role;

}
