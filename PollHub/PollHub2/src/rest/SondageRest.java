package rest;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.StringReader;
import java.util.List;

import beans.*;
import manager.*;

@Path("/sondage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SondageRest {

    @EJB
    private SondageManager sondageManager;

    @POST
    @Path("/create")
    public Response createPoll(String json) {
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jsonObject = reader.readObject();
            String pollTitle = jsonObject.getString("pollTitle");
            boolean isAnonymous = jsonObject.getBoolean("anonymous");
            JsonArray questionsArray = jsonObject.getJsonArray("questions");

            Sondage sondage = new Sondage();
            sondage.setNom(pollTitle);
            sondage.setCategorie(isAnonymous);

            sondageManager.createSondage(sondage);

            for (JsonObject questionJson : questionsArray.getValuesAs(JsonObject.class))
				try {
					{
					    String questionText = questionJson.getString("question");
					    String questionType = questionJson.getString("questionType");
					    int nbRep = questionJson.getInt("nb_rep");
					    JsonArray optionsArray = questionJson.getJsonArray("options");

					    Question question = new Question();
					    question.setQuestion(questionText);
					    question.setType(questionType);
					    question.setNb_rep(nbRep);
					    question.setSondage(sondage);

					    sondageManager.createQuestion(question);

					    try {
							if ("multiple-choice".equals(questionType)) {
							    for (JsonString optionJson : optionsArray.getValuesAs(JsonString.class)) {
							        String optionText = optionJson.getString();
							        Reponse reponse = new Reponse();
							        reponse.setReponse(optionText);
							        reponse.setQuestion(question);
							        sondageManager.createReponse(reponse);
							    }
							}
						} catch (Exception e) {
						}
					}
				} catch (Exception e) {
				}

            JsonObject response = Json.createObjectBuilder()
                .add("message", "Sondage créé avec succès")
                .add("pollId", sondage.getId())
                .add("success", true)
                .build();

            return Response.ok(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject response = Json.createObjectBuilder()
                .add("message", "Erreur lors de la création du sondage")
                .add("success", false)
                .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getSondage(@PathParam("id") int id) {
        Sondage sondage = sondageManager.getSondage(id);
        return Response.ok(sondage).build();
    }

    @GET
    public Response getAllSondages() {
        List<Sondage> sondages = sondageManager.getAllSondages();
        return Response.ok(sondages).build();
    }

    @PUT
    @Path("/update")
    public Response updateSondage(Sondage sondage) {
        Sondage updatedSondage = sondageManager.updateSondage(sondage);
        return Response.ok(updatedSondage).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteSondage(@PathParam("id") int id) {
        sondageManager.deleteSondage(id);
        return Response.ok().build();
    }
}
