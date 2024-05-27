package beans;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Personne
 */
@Entity
public class Historique {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nom;
  
  
  @ManyToOne
  private Sondage sondage;

  private int numero_sondage;
  
  public Historique() {
	  
  }

  public Historique(String nom, int numero_sondage) {
    this.nom = nom;
    this.numero_sondage = numero_sondage;
  }

  // lier avec la classe sondage
  public int getnumero_sondage() {
    return numero_sondage;
  }

  public Sondage getSondage() {
    return sondage;
  }

public String getNom() {
	return this.nom;
}

public int getNumero_sondage() {
	return this.numero_sondage;
}

}



