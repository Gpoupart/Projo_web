package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import beans.Historique;

@Stateless
public class HistoriqueManager {

    @PersistenceContext
    private EntityManager em;

    public void createHistorique(Historique historique) {
        em.persist(historique);
    }

    public Historique getHistorique(int id) {
        return em.find(Historique.class, id);
    }

    public List<Historique> getAllHistoriques() {
        return em.createQuery("SELECT h FROM Historique h", Historique.class).getResultList();
    }

    public Historique updateHistorique(Historique historique) {
        return em.merge(historique);
    }

    public void deleteHistorique(int id) {
        Historique historique = em.find(Historique.class, id);
        if (historique != null) {
            em.remove(historique);
        }
    }
}
