package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import beans.Sondage;

@Stateless
public class SondageManager {

    @PersistenceContext
    private EntityManager em;

    public void createSondage(Sondage sondage) {
        em.persist(sondage);
    }

    public Sondage getSondage(int id) {
        return em.find(Sondage.class, id);
    }

    public List<Sondage> getAllSondages() {
        return em.createQuery("SELECT s FROM Sondage s", Sondage.class).getResultList();
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
}
