package rest;

import manager.Facade;
import beans.Etudiant;
import beans.Groupe;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupRest {

    @EJB
    private Facade facade;

    @POST
    @Path("/create")
    public Response createGroup(String json) {
    	System.out.println("je passseeee laaaaaaaaaa1");
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
        	System.out.println("je passseeee laaaaaaaaaa2");
            JsonObject jsonObject = reader.readObject();
            String groupName = jsonObject.getString("groupName");
            JsonArray studentsArray = jsonObject.getJsonArray("students");
           

            for (int i = 0; i < studentsArray.size(); i++) {
            	System.out.println("je passseeee laaaaaaaaaa");
            	Etudiant etudiant = facade.trouverEtudiantParEmail(studentsArray.getString(i));
            	System.out.println("je passseeee au milieuuuuuuu");
            	facade.ajoutPersonneGroupe(etudiant, groupName);
            	System.out.println("je passseeee apresssss");
            }
            
            JsonObject response = Json.createObjectBuilder()
                    .add("message", "Groupe créé avec succès")
                    .build();
            return Response.ok(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Une erreur s'est produite.").build();
        }
    }
/*
    @GET
    @Path("/list")
    public Response getGroups() {
        try {
            List<Groupe> groups = facade.getGroups();
            return Response.ok(groups).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Une erreur s'est produite lors de la récupération des groupes.").build();
        }
    }*/
}
