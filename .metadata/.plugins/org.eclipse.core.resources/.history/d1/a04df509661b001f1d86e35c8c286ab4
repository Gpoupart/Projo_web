package beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @ManyToMany(mappedBy="groupes")
    private List<Personne> personnes;

    @ManyToMany
    private List<Sondage> sondages;
    
    
    public Groupe() {
    	
    }
    public Groupe(String nom) {
        this.nom = nom;
    }
	public String getNom() {
		return this.nom;
	}

}
