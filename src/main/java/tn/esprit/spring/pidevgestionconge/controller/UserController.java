package tn.esprit.spring.pidevgestionconge.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.pidevgestionconge.entity.Utilisateur;
import tn.esprit.spring.pidevgestionconge.service.AuthenticationService;
import tn.esprit.spring.pidevgestionconge.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService service;
    private final UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest  request
    ){
       // service.sendEmail(request.getEmail(), "Account Credentials", request.getEmail() + request.getPassword() );
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @DeleteMapping("/delete/{id}")
    void deleteUtilisateur(@PathVariable Integer id){
        utilisateurService.deleteUtilisateur(id);
    }

    @GetMapping("/get")
    List<Utilisateur> retrieveUtilisateur(){
        return utilisateurService.retrieveUtilisateur();
    }

    @GetMapping("/get/{id}")
    Utilisateur retrieveById(@PathVariable  Integer id){
        return utilisateurService.retrieveById(id);
    }

    @PutMapping("/update")
    Utilisateur updateUtilisateur(@RequestBody Utilisateur u){

        return  utilisateurService.updateUtilisateur(u);
    }


}
