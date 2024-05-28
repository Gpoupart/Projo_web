package rest;

import manager.Facade;
import beans.Groupe;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupRest {

    @EJB
    private Facade facade;

    @POST
    @Path("/create")
    public Response createGroup(String json) {
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jsonObject = reader.readObject();
            String groupName = jsonObject.getString("groupName");
            JsonArray studentsArray = jsonObject.getJsonArray("students");
            List<String> studentEmails = new ArrayList<>();

            for (int i = 0; i < studentsArray.size(); i++) {
                studentEmails.add(studentsArray.getString(i));
            }

            facade.creerGroupe(groupName, studentEmails);
            JsonObject response = Json.createObjectBuilder()
                    .add("message", "Groupe créé avec succès")
                    .build();
            return Response.ok(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Une erreur s'est produite.").build();
        }
    }

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
    }
}
