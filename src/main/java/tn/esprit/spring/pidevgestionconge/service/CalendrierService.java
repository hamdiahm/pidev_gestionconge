package tn.esprit.spring.pidevgestionconge.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.pidevgestionconge.entity.Calendrier;
import tn.esprit.spring.pidevgestionconge.repository.CalendrierRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CalendrierService implements IcalendreirService {

    @Autowired
    CalendrierRepo calendrierRepo;


    @Override
    public List<Calendrier> retrieveAllCalendrier() {
        return calendrierRepo.findAll();
    }

    @Override
    public Calendrier retrieveById(Integer id) {
        return  calendrierRepo.findById(id).orElse(null);
    }

    @Override
    public Calendrier saveCalendrier(Calendrier c) {
        return calendrierRepo.save(c);
    }

    @Override
    public Calendrier updateCalendrier(Calendrier c) {
        return calendrierRepo.save(c);
    }

    @Override
    public void deleteCalendrier(Integer id) {
        calendrierRepo.deleteById(id);

    }
}
