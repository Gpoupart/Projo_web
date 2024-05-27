package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import beans.Question;

@Stateless
public class QuestionManager {

    @PersistenceContext
    private EntityManager em;

    public void createQuestion(Question question) {
        em.persist(question);
        em.flush();  // Force la synchronisation avec la base de données pour obtenir l'ID généré
    }

    public Question getQuestion(int id) {
        return em.find(Question.class, id);
    }

    public List<Question> getAllQuestions() {
        return em.createQuery("SELECT q FROM Question q", Question.class).getResultList();
    }

    public Question updateQuestion(Question question) {
        return em.merge(question);
    }

    public void deleteQuestion(int id) {
        Question question = em.find(Question.class, id);
        if (question != null) {
            em.remove(question);
        }
    }
}
