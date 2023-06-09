package tn.esprit.spring.pidevgestionconge.service;

import tn.esprit.spring.pidevgestionconge.entity.Demande;

import java.util.List;

public interface IserviceDemande {

    public Demande ajouterDemande(Demande demande);
   void  removeDemande(Integer idDemande);
    List<Demande> retrieveAlldemandes();
    Demande updateDemande (Demande d);
    Demande retrieveDemande (Integer idDemande);
}
