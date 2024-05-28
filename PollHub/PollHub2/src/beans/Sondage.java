package beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToMany;
/**
 * Adresse
 */
@Entity
public class Sondage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // plusiseurs grouep peuvent participer à un sondage
  @ManyToMany
  private List<Groupe> groupes;

  private boolean categorie;
  
  private String nom;

  // pour simplifier
  @OneToMany(mappedBy="sondage", fetch= FetchType.EAGER)
  private List<Question> questions= new ArrayList<Question>();

  @OneToMany
  private List<Historique> historique= new ArrayList<Historique>(); /* can be null */

  public Sondage() {
	  
  }

  public Sondage (List<Groupe> groupes, List<Question> questions) {
    this.groupes = groupes;
    this.questions = questions;
  }

  public int getId() {
    return id;
  }
  //verifier perosnne présente dans classe groupe
  public List<Groupe> getGroupe() {
    return groupes;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public List<Question> ajoutQuestion(Question question) {
    questions.add(question);
    return questions;
  }

  public List<Question> supprimerQuestion(Question question) {
    if (questions.contains(question)) {
      questions.remove(question);
    }
    return questions;
  }

public boolean getCategorie() {
	return categorie;
}

public void setCategorie(boolean categorie) {
	this.categorie = categorie;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

}
