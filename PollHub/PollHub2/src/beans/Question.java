package beans;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Question {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    private int nb_rep;
    
    private String type;

    @OneToMany
    private List<Reponse> reponses = new ArrayList<Reponse>();
    
    @ManyToOne
    private Sondage sondage;


    public Question() {
    	
    }
    public Question(String question) {
        this.question = question;
        this.nb_rep = 0;
    }

    public String getQuestion() {
        return question;
    }

    public int getNb_rep() {
        return nb_rep;
    }

    public void setNb_rep(int nb_rep) {
        this.nb_rep ++;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

   
    
}

