package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import javax.json.JsonString;
import java.io.StringReader;
import java.util.List;

import beans.Question;
import beans.Reponse;
import manager.QuestionManager;
import manager.ReponseManager;

@Path("/question")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionRest {

    @EJB
    private QuestionManager questionManager;

    @EJB
    private ReponseManager reponseManager;

    @POST
    @Path("/create")
    public Response createQuestion(JsonObject jsonObject) {
        System.out.println("Received create question request ");
            String questionText = jsonObject.getString("question");
            String questionType = jsonObject.getString("questionType");
            int nbRep = jsonObject.getInt("nb_rep");

            System.out.println("Creating question with the following details:");
            System.out.println("Question: " + questionText);
            System.out.println("Question Type: " + questionType);
            System.out.println("Number of Responses: " + nbRep);

            Question question = new Question();
            question.setQuestion(questionText);
            question.setType(questionType);
            question.setNb_rep(nbRep);

            questionManager.createQuestion(question);

            if ("multiple-choice".equals(questionType)) {
                JsonArray optionsArray = jsonObject.getJsonArray("options");
                for (JsonString optionJson : optionsArray.getValuesAs(JsonString.class)) {
                    String optionText = optionJson.getString();
                    Reponse reponse = new Reponse();
                    reponse.setReponse(optionText);
                    reponse.setQuestion(question);
                    reponseManager.createReponse(reponse);
                }
            }

            JsonObject response = Json.createObjectBuilder()
                .add("message", "Question créée avec succès")
                .add("success", true)
                .add("question", questionText)
                .add("questionType", questionType)
                .add("nb_rep", nbRep)
                .build();
            
            return Response.ok(response).build();
        
        
    }

    @GET
    @Path("/{id}")
    public Response getQuestion(@PathParam("id") int id) {
        Question question = questionManager.getQuestion(id);
        return Response.ok(question).build();
    }

    @GET
    public Response getAllQuestions() {
        List<Question> questions = questionManager.getAllQuestions();
        return Response.ok(questions).build();
    }

    @PUT
    @Path("/update")
    public Response updateQuestion(Question question) {
        Question updatedQuestion = questionManager.updateQuestion(question);
        return Response.ok(updatedQuestion).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteQuestion(@PathParam("id") int id) {
        questionManager.deleteQuestion(id);
        return Response.ok().build();
    }
}
