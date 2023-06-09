package tn.esprit.spring.pidevgestionconge.service;

import tn.esprit.spring.pidevgestionconge.entity.Solde;

import java.util.List;

public interface ISoldeService {

    List<Solde> retrieveSoldeConge();
    Solde retrieveById(Integer id);
    Solde saveSoldeConge(Solde s);
    Solde updateSoldeConge(Solde s);
    void deleteSoldeConge(Integer id);


}
