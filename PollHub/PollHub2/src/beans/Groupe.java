package beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @ManyToMany(mappedBy="groupes")
    private List<Etudiant> personnes = new ArrayList<Etudiant>();
   
    
    @ManyToMany(mappedBy = "groupes")
    private List<Sondage> sondages= new ArrayList<Sondage>();
    
    
    public Groupe() {
    	
    }
    public Groupe(String nom) {
        this.nom = nom;
    }
	public String getNom() {
		return this.nom;
	}
	
	public void setPersonnes(List<Etudiant> etudiants){
		this.personnes = etudiants;
	}
	
	public void addEtudiant(Etudiant etudiant) {
        this.personnes.add(etudiant);
    }
	
	public void setNom(String nom) {
		this.nom = nom;	
	}
	public Object getEtudiants() {
		return this.personnes;
	}
	

}