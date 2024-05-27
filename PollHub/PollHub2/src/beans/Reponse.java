package beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Reponse {
    
    @Id
    @GeneratedValue
    private int id;


    private String reponse="";

    private boolean repondu = false;

    @ManyToOne
    private Question question;

    public Reponse() {
    	
    }

    public Reponse(String reponse) {
        this.reponse = reponse;
        
    }

    public void setReponse(String reponse) {
        if (reponse == null) {
            throw new IllegalArgumentException("Reponse ne peut pas etre null");
        }
        this.reponse = reponse;
        this.repondu = true;
    }

    public boolean isRepondu() {
        return repondu;
    }

    public String getReponse() {
        return reponse;
    }

    // comment verifier si reponse il y a
}
