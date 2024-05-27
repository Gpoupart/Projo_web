package rest;

import javax.ejb.EJB;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.*;
import manager.Facade;

import java.util.List;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestRest {
	
	@EJB
    private Facade testManager;

    @GET
    @Path("crsond")
    public void creerPersonne(String nom, String prenom , String mail , String mdp) {
    	System.out.println("Nouvel utilsateur");
    	testManager.creerPersonne(nom, prenom, mail, mdp);
    }
    
    @GET
    @Path("crsond")
    public void creerQuestion(String question) {
    	System.out.println("Nouvelle question");
    	testManager.creerQuestion(question);
    }
    
    @POST
    @Path("creerPersonne")
    public void creerPersonne(Personne personne) {
        System.out.println("Nouvel utilisateur");
        testManager.creerPersonne(personne.getMail(), personne.getMdp(), personne.getPrenom(), personne.getNom());
    }

    @POST
    @Path("creerQuestion")
    public void creerQuestion(Question question) {
        System.out.println("Nouvelle question");
        testManager.creerQuestion(question.getQuestion());
    }

    @POST
    @Path("creerReponse")
    public void creerReponse(Reponse reponse) {
        System.out.println("Nouvelle réponse");
        testManager.creerReponse(reponse.getReponse());
    }

    @POST
    @Path("creerGroupe")
    public void creerGroupe(Groupe groupe) {
        System.out.println("Nouveau groupe");
        testManager.creerGroupe(groupe.getNom());
    }

    @POST
    @Path("creerSondage")
    public void creerSondage(Sondage data) {
        System.out.println("Nouveau sondage");
        testManager.creerSondage(data.getQuestions(), data.getGroupe());
    }

    @POST
    @Path("creerHistorique")
    public void creerHistorique(Historique historique) {
        System.out.println("Nouvel historique");
        testManager.creerHistorique(historique.getNom(), historique.getNumero_sondage());
    }

    @POST
    @Path("creerCommentaire")
    public void creerCommentaire(Commentaire commentaire) {
        System.out.println("Nouveau commentaire");
        testManager.creerCommentaire(commentaire.getCommentaire(), commentaire.getAuteur());
    }
    
    
}
