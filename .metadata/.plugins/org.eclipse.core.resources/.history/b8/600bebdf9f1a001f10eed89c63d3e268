package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.ManyToMany;
/**
 * Adresse
 */
@Entity
public class Sondage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // plusiseurs grouep peuvent participer à un sondage
  @ManyToMany(mappedBy = "sondages")
  private List<Groupe> groupes;

  private String categorie;

  private List<Question> questions;

  @ManyToOne
  private Historique historique; /* can be null */



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

}
