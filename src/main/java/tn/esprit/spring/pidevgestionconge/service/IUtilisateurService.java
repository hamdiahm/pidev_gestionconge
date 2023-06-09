package tn.esprit.spring.pidevgestionconge.service;

import jdk.jshell.execution.Util;
import tn.esprit.spring.pidevgestionconge.entity.Utilisateur;

import java.util.List;

public interface IUtilisateurService {

    void deleteUtilisateur(Integer id);
    List<Utilisateur> retrieveUtilisateur();

    Utilisateur retrieveById(Integer id);


    Utilisateur updateUtilisateur(Utilisateur u);



}
