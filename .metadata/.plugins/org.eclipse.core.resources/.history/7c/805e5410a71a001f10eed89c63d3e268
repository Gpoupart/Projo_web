package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Commentaire {
    
    private String commentaire;
    @Id
    private String auteur;

    public Commentaire(String commentaire, String auteur) {
        this.commentaire = commentaire;
        this.auteur = auteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
