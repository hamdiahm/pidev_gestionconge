package tn.esprit.spring.pidevgestionconge.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.pidevgestionconge.entity.Calendrier;
import tn.esprit.spring.pidevgestionconge.service.CalendrierService;

import java.util.List;

@RestController
@RequestMapping("/calendrier")
@RequiredArgsConstructor



public class CalendrierController {

@Autowired
    CalendrierService cs;


    @GetMapping("/get")
    List<Calendrier> retrieveCalendrier(){

        return cs.retrieveAllCalendrier();
    }

    @GetMapping("/get/{id}")
    Calendrier retrieveById(@PathVariable Integer id){
        return cs.retrieveById(id);
    }


    @PostMapping("/add")
    Calendrier saveCalendrier(@RequestBody Calendrier c){
        return cs.saveCalendrier(c);
    }

    @PutMapping("/update/{id}")
    Calendrier updateCalendrier(@RequestBody Calendrier c){
        return  cs.updateCalendrier(c);
    }

    @DeleteMapping("/delete/{id}")
    void deleteCalendrier(@PathVariable  Integer id){

        cs.deleteCalendrier(id);
    }
}
