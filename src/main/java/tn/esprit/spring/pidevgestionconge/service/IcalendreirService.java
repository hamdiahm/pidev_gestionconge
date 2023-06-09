package tn.esprit.spring.pidevgestionconge.service;

import tn.esprit.spring.pidevgestionconge.entity.*;

import java.util.List;


public interface IcalendreirService {
    List<Calendrier> retrieveAllCalendrier();
    Calendrier retrieveById(Integer id);
    Calendrier saveCalendrier(Calendrier c);
    Calendrier updateCalendrier(Calendrier c);
    void deleteCalendrier(Integer id);






}
