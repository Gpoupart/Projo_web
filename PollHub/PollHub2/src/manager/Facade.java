package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import beans.*;


@Stateless
public class Facade {
	
	@PersistenceContext
	EntityManager em;
	
	public void creerPersonne(String nom, String prenom, String mail, String password, boolean role, int year, String major, String speciality) {
		
		if(!role) {
			System.out.println("Etape 1");
			Etudiant etudiant = new Etudiant(nom,prenom,mail,password,year, major, speciality);
			em.persist(etudiant);
			
			System.out.println("Etape 2");
			String groupName = etudiant.getMajor() + "_" + etudiant.getYear() + "A";
			System.out.println("tkt frere je faire le faire l'ajout");
			ajoutPersonneGroupe(etudiant,groupName);
			System.out.println("tkt frere j'ai fait l'ajout");
	        if (etudiant.getSpeciality() != null && !etudiant.getSpeciality().isEmpty()) {
	            groupName += "_" + etudiant.getSpeciality();
	            ajoutPersonneGroupe(etudiant,groupName);
	            System.out.println(groupName);
	        }

	       
		} else {
			Professeur professeur = new Professeur(nom,prenom,mail,password);
			em.persist(professeur);
		}
		
		}
	
	public void creerQuestion(String questions) {
		Question question = new Question(questions);
		em.persist(question);
	}
	
	public void creerReponse(String reponses) {
		Reponse reponse = new Reponse(reponses);
		em.persist(reponse);
	}
	
	public void creerGroupe(String groupName) {
		Groupe groupe = new Groupe(groupName);
		em.persist(groupe);
	}


	
	public void creerSondage(List<Question> questions, List<Groupe> groupes) {
		Sondage sondage = new Sondage(groupes,questions);
		em.persist(sondage);
	}

	public void creerHistorique(String nom, int numero_sondage) {
		Historique historique = new Historique(nom,numero_sondage);
		em.persist(historique);
	}
	
	public void creerCommentaire(String commentaire, int auteur) {
		Commentaire com = new Commentaire(commentaire,auteur);
		em.persist(com);
	}
		
	
	 public Etudiant trouverEtudiantParEmail(String email) {
	        TypedQuery<Etudiant> query = em.createNamedQuery("Etudiant.findByEmail", Etudiant.class);
	        query.setParameter("mail", email);
	        List<Etudiant> result = query.getResultList();
	        if (result.isEmpty()) {
	            return null; 
	        } else {
	            return result.get(0);
	        }
	    }

	
	/*public void ajoutPersonneGroupe(Etudiant etu, String groupName) {
		Groupe groupe = null;
        try {
            groupe = em.createQuery("SELECT g FROM Groupe g WHERE g.nom = :groupName", Groupe.class)
                    .setParameter("groupName", groupName)
                    .getSingleResult();
        } catch (NoResultException e) {
            groupe = new Groupe(groupName);
            em.persist(groupe);
        }
        groupe.addEtudiant(etu);
        etu.getGroupes().add(groupe);
        em.persist(groupe);
        em.persist(etu);
	}*/
	 public void ajoutPersonneGroupe(Etudiant etu, String groupName) {
		    Groupe groupe = null;
		    try {
		        groupe = em.createQuery("SELECT g FROM Groupe g WHERE g.nom = :groupName", Groupe.class)
		                .setParameter("groupName", groupName)
		                .getSingleResult();
		    } catch (NoResultException e) {
		        groupe = new Groupe(groupName);
		        em.persist(groupe);
		    }
		    // Initialize groupes collection
		    etu = em.find(Etudiant.class, etu.getId());
		    if (etu != null) {
		        etu.getGroupes().size(); // Initialize the collection
		        groupe.addEtudiant(etu);
		        etu.getGroupes().add(groupe);
		        em.persist(groupe);
		        em.persist(etu);
		    }
		}
	

	public List<Groupe> getGroups(){
		return em.createQuery("SELECT g FROM Group g", Groupe.class).getResultList();
	}
	
	/*public void initGroupe() {
		//groupe de base SN
		Groupe sn_1A = new Groupe("sn_1A");
		em.persist(sn_1A);
		Groupe sn_2A = new Groupe("sn_2A");
		em.persist(sn_2A);
		Groupe sn_3A = new Groupe("sn_3A");
		em.persist(sn_3A);
		Groupe sn_2A_IMM = new Groupe("sn_2A_IMM");
		em.persist(sn_2A_IMM);
		Groupe sn_2A_HPCB = new Groupe("sn_2A_HPCB");
		em.persist(sn_2A_HPCB);
		//groupe de base 3EA
		Groupe eeea_1A = new Groupe("eeea_1A");
		em.persist(eeea_1A);
		Groupe eeea_2A = new Groupe("eeea_2A");
		em.persist(eeea_2A);
		Groupe eeea_3A = new Groupe("eeea_3A");
		em.persist(eeea_3A);
		//groupe de base MF2E
		Groupe mf2e_1A = new Groupe("mf2e_1A");
		em.persist(mf2e_1A);
		Groupe mf2e_2A = new Groupe("mf2e_2A");
		em.persist(mf2e_2A);
		Groupe mf2e_3A = new Groupe("mf2e_3A");
		em.persist(mf2e_3A);
	}*/
	
}
