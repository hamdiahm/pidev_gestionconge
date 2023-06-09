package tn.esprit.spring.pidevgestionconge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.pidevgestionconge.entity.Solde;
import tn.esprit.spring.pidevgestionconge.service.SoldeService;

import java.util.List;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class SoldeController {
    @Autowired
    SoldeService ss;



    @GetMapping("/afficherAllSolde")
    @ResponseBody
    List<Solde> afficherAllSolde(){
        return ss.retrieveSoldeConge();
    }


    @GetMapping("/afficherSolde/{id}")
    @ResponseBody
    Solde afficherSolde(@PathVariable("id")  Integer id) {
        return ss.retrieveById(id);
    }


    @DeleteMapping("/deleteSolde/{id}")
    @ResponseBody
    void deleteSolde(@PathVariable("id") Integer id) {
        ss.deleteSoldeConge(id);
    }

    @PostMapping("/addSolde")
    @ResponseBody
    Solde addSolde(@RequestBody Solde s) {
        return ss.saveSoldeConge(s);
    }

    @PutMapping ("/updateSolde")
    @ResponseBody
    Solde updateSolde(@RequestBody Solde s) {
        return ss.saveSoldeConge(s);
    }
}
