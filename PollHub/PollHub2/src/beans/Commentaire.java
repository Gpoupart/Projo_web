package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Commentaire {
    
    private String commentaire;
    @Id
    private int auteur;

    
    public Commentaire() {
    	
    }
    public Commentaire(String commentaire, int auteur) {
        this.commentaire = commentaire;
        this.auteur = auteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getAuteur() {
        return auteur;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
