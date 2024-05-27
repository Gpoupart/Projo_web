package beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Personne {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private int autorisation;
    
    @ManyToMany
    private List<Groupe> groupes;
    
    public Personne() {
    	
    }
    
    public Personne(String nom, String prenom, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.autorisation = 0;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public int getAutorisation() {
        return autorisation;
    }

    //changer l'autorisation en focntion de l'adresse mail
    public void setAutorisation(int autorisation) {
        this.autorisation = autorisation;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
