package tn.esprit.spring.pidevgestionconge.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.pidevgestionconge.entity.Demande;
import tn.esprit.spring.pidevgestionconge.repository.DemandeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceDemande implements  IserviceDemande{
    @Autowired

    DemandeRepository demandeRepository;
    @Override
    public Demande ajouterDemande(Demande demande) {return demandeRepository.save(demande);}

    @Override

    public void removeDemande(Integer idDemande) {
        demandeRepository.deleteById(idDemande);
    }

    @Override
    public List<Demande> retrieveAlldemandes() {
        return demandeRepository.findAll();
    }

    @Override
    public Demande updateDemande(Demande d) {
        return demandeRepository.save(d);
    }

    @Override
    public Demande retrieveDemande(Integer idDemande) {

            return demandeRepository.findById(idDemande).orElse(null);

    }
}
