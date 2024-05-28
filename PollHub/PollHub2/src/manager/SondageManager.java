package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import beans.Question;
import beans.Reponse;
import beans.Sondage;

@Stateless
public class SondageManager {

    @PersistenceContext
    private EntityManager em;

    public void createSondage(Sondage sondage) {
        em.persist(sondage);
        em.flush();  // Force la synchronisation avec la base de données pour obtenir l'ID généré
    }

    public Sondage getSondage(int id) {
        return em.find(Sondage.class, id);
    }

    public List<Sondage> getAllSondages() {
        return em.createQuery("SELECT p FROM Sondage p", Sondage.class).getResultList();
    }

    public Sondage updateSondage(Sondage sondage) {
        return em.merge(sondage);
    }

    public void deleteSondage(int id) {
        Sondage sondage = em.find(Sondage.class, id);
        if (sondage != null) {
            em.remove(sondage);
        }
    }

	public void createQuestion(Question question) {
        em.persist(question);
        em.flush();  // Force la synchronisation avec la base de données pour obtenir l'ID généré
    }
	
	public void createReponse(Reponse reponse) {
        em.persist(reponse);
    }
}
