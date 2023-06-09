package tn.esprit.spring.pidevgestionconge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.pidevgestionconge.config.JwtService;
import tn.esprit.spring.pidevgestionconge.controller.AuthenticationRequest;
import tn.esprit.spring.pidevgestionconge.controller.AuthenticationResponse;
import tn.esprit.spring.pidevgestionconge.controller.RegisterRequest;
import tn.esprit.spring.pidevgestionconge.entity.Role;
import tn.esprit.spring.pidevgestionconge.entity.Utilisateur;
import tn.esprit.spring.pidevgestionconge.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .matricule(request.getMatricule())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository .save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void sendEmail(String toEmail,String subject, String body){

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("congec654@gmail.com");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);

    mailSender.send(message);
        System.out.println("Mail sent successfully");
    }





}
