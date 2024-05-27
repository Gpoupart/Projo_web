package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import beans.Commentaire;

@Stateless
public class CommentaireManager {

    @PersistenceContext
    private EntityManager em;

    public void createCommentaire(Commentaire commentaire) {
        em.persist(commentaire);
    }

    public Commentaire getCommentaire(int auteur) {
        return em.find(Commentaire.class, auteur);
    }

    public List<Commentaire> getAllCommentaires() {
        return em.createQuery("SELECT c FROM Commentaire c", Commentaire.class).getResultList();
    }

    public Commentaire updateCommentaire(Commentaire commentaire) {
        return em.merge(commentaire);
    }

    public void deleteCommentaire(int auteur) {
        Commentaire commentaire = em.find(Commentaire.class, auteur);
        if (commentaire != null) {
            em.remove(commentaire);
        }
    }
}
