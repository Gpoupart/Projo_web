package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import beans.Reponse;

@Stateless
public class ReponseManager {

    @PersistenceContext
    private EntityManager em;

    public void createReponse(Reponse reponse) {
        em.persist(reponse);
    }

    public Reponse getReponse(int id) {
        return em.find(Reponse.class, id);
    }

    public List<Reponse> getAllReponses() {
        return em.createQuery("SELECT r FROM Reponse r", Reponse.class).getResultList();
    }

    public Reponse updateReponse(Reponse reponse) {
        return em.merge(reponse);
    }

    public void deleteReponse(int id) {
        Reponse reponse = em.find(Reponse.class, id);
        if (reponse != null) {
            em.remove(reponse);
        }
    }
}
