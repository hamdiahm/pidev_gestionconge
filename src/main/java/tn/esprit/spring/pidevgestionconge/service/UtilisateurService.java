package tn.esprit.spring.pidevgestionconge.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.pidevgestionconge.entity.Utilisateur;
import tn.esprit.spring.pidevgestionconge.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurService implements IUtilisateurService{
    UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    @Override
    public void deleteUtilisateur(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Utilisateur> retrieveUtilisateur() {
        return userRepository.findAll();
    }

    @Override
    public Utilisateur retrieveById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }
}
