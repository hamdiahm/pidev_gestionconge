package tn.esprit.spring.pidevgestionconge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Utilisateur implements Serializable, UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;
    private String email;
    private  String password;
    private Integer matricule;
    private String nom;
    private  String prenom;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private Set<Demande>demandes;

    @OneToOne
    @JsonIgnore
    private Calendrier calendrier;

    @OneToOne
    @JsonIgnore
    private Solde solde;


    //METHOD SENDS A LIST OF ROLES - DONE
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    //ADD THIS METHOD , WONT OVERRIDE FROM USERDETAILS BECAUSE OF LOMBOK ANNOTATION
    @Override
    public String getPassword(){
        return  password;
    }

    //CHANGE TO TRUE - DONE
    @Override
    public String getUsername() {
        return email;
    }

    //CHANGE TO TRUE - DONE
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //CHANGE TO TRUE - DONE
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //CHANGE TO TRUE - DONE
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //CHANGE TO TRUE - DONE
    @Override
    public boolean isEnabled() {
        return true;
    }
}
