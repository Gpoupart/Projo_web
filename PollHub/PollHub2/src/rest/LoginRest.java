package rest;

import java.io.StringReader;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;

import manager.LoginManager;
import beans.Etudiant;
import manager.Facade;
import beans.Professeur;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {
    
    @EJB
    private LoginManager loginManager;
    
    @EJB
    private Facade facade;

   
    @POST
    @Path("/connexion")
    public Response login(String json) {
        System.out.println("login");
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jsonObject = reader.readObject();
            String mail = jsonObject.getString("mail");
            String password = jsonObject.getString("mdp");

            //System.out.print(mail);
            //System.out.print(password);
            boolean emailExists = false;
         
            Etudiant etudiant = loginManager.checkLoginE(mail, password);
            if (etudiant != null) {
                JsonObject response = Json.createObjectBuilder()
                        .add("prenom", etudiant.getPrenom())
                        .add("nom", etudiant.getNom())
                        .add("role", 0)
                        .build();
                
                emailExists = true;
                System.out.print(Response.ok(response).build());
                return Response.ok(response).build();
            }

            Professeur professeur = loginManager.checkLoginP(mail, password);
            if (professeur != null) {
            	 ;
                JsonObject response = Json.createObjectBuilder()
                        .add("prenom", professeur.getPrenom())
                        .add("nom", professeur.getNom())
                        .add("role", 1)
                        .build();
                emailExists = true;
                return Response.ok(response).build();
            }

            // Check if the email exists in either table
            if (!emailExists) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Identifiant n'existe pas")
                               .build();
            }
            
            return Response.status(Response.Status.UNAUTHORIZED).build();
            
        } catch (JsonException e) {
            return Response.serverError().entity("Erreur de traitement du JSON.").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Une erreur s'est produite.").build();
        }
    }

    @POST
    @Path("/creation")
    public Response createPeople(String json) {
    	//facade.initGroupe();
        System.out.println("Received create user request with payload: " + json);
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jsonObject = reader.readObject();
            String prenom = jsonObject.getString("prenom");
            String nom = jsonObject.getString("nom");
            String mail = jsonObject.getString("mail");
            String mdp = jsonObject.getString("mdp");
            boolean role = jsonObject.getBoolean("role", false);
            Integer year = jsonObject.containsKey("year") && !jsonObject.isNull("year") ? Integer.parseInt(jsonObject.getString("year")) : null;
            String major = jsonObject.containsKey("major") ? jsonObject.getString("major") : null;
            String specialty = jsonObject.containsKey("specialty") ? jsonObject.getString("specialty") : null;

            System.out.println("Creating person with the following details:");
            System.out.println("Nom: " + nom);
            System.out.println("Prenom: " + prenom);
            System.out.println("Mail: " + mail);
            System.out.println("Mdp: " + mdp);
            System.out.println("Role: " + role);
            System.out.println("Year: " + year);
            System.out.println("Major: " + major);
            System.out.println("Specialty: " + specialty);

            if (role) {
                System.out.println("Inserting professor...");
                facade.creerPersonne(nom, prenom, mail, mdp, role, year, major, specialty);
            } else {
                System.out.println("Inserting student...");
                facade.creerPersonne(nom, prenom, mail, mdp, role, year, major, specialty);
            }
            
            JsonObject response = Json.createObjectBuilder()
                .add("message", "Utilisateur créé avec succès")
                .add("succes", true)
                .add("prenom", prenom)
                .add("nom", nom)
                .add("role", role)
                .build();
            
            return Response.ok(response).build();
            
        } catch (JsonException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            return Response.serverError().entity("Erreur de traitement du JSON.").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Une erreur s'est produite.").build();
        }
    }


    
}


