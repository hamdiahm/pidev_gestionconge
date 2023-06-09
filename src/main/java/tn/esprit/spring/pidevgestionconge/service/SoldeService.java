package tn.esprit.spring.pidevgestionconge.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.pidevgestionconge.entity.Solde;
import tn.esprit.spring.pidevgestionconge.repository.SoldeRepository;

import java.util.List;
@Service
@AllArgsConstructor

public class SoldeService implements ISoldeService{
SoldeRepository soldeRepository;
    @Override
    public List<Solde> retrieveSoldeConge() {
        return soldeRepository.findAll();
    }

    @Override
    public Solde retrieveById(Integer id) {
        return soldeRepository.findById(id).orElse(null);
    }

    @Override
    public Solde saveSoldeConge(Solde s) {
        return soldeRepository.save(s);
    }

    @Override
    public Solde updateSoldeConge(Solde s) {
        return soldeRepository.save(s);
    }

    @Override
    public void deleteSoldeConge(Integer id) {
        soldeRepository.deleteById(id);
    }
}
