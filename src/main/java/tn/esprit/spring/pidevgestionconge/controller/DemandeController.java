package tn.esprit.spring.pidevgestionconge.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.pidevgestionconge.entity.Demande;
import tn.esprit.spring.pidevgestionconge.repository.DemandeRepository;
import tn.esprit.spring.pidevgestionconge.service.IserviceDemande;

import javax.persistence.Id;
import java.util.List;

@RestController
@AllArgsConstructor
public class DemandeController {
    @Autowired

    IserviceDemande iserviceDemande;
    private final DemandeRepository demandeRepository;

    @PostMapping("/ajouterdemande")
    public Demande ajouterDemande(Demande demande) {

       return iserviceDemande.ajouterDemande(demande);
    }

    @DeleteMapping("/supprimerdemande/{idDemande}")
    public void removeEtudiant(@PathVariable Integer idDemande){

        iserviceDemande.removeDemande(idDemande);
    }
@GetMapping("/afficherAlldemande")
List<Demande> retrieveAlldemandes(){
        return iserviceDemande.retrieveAlldemandes();
}
@PutMapping("/updatedemande")
Demande updateDemande (@RequestBody Demande d) {

    return iserviceDemande.updateDemande(d);
}


@GetMapping("/getDemandeByID/{idDemande}")
Demande retrieveDemande (@PathVariable Integer idDemande){

    return iserviceDemande.retrieveDemande(idDemande);
}



}




