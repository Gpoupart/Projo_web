package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import beans.Professeur;

@Stateless
public class ProfesseurManager {

    @PersistenceContext
    private EntityManager em;

    public void createProfesseur(Professeur professeur) {
        em.persist(professeur);
    }

    public Professeur getProfesseur(int id) {
        return em.find(Professeur.class, id);
    }

    public List<Professeur> getAllProfesseurs() {
        return em.createQuery("SELECT p FROM Professeur p", Professeur.class).getResultList();
    }

    public Professeur updateProfesseur(Professeur professeur) {
        return em.merge(professeur);
    }

    public void deleteProfesseur(int id) {
        Professeur professeur = em.find(Professeur.class, id);
        if (professeur != null) {
            em.remove(professeur);
        }
    }
}
