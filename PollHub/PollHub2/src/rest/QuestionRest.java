package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import beans.Question;
import manager.QuestionManager;

@Path("/question")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionRest {

    @EJB
    private QuestionManager questionManager;

    @POST
    @Path("/create")
    public Response createQuestion(Question question) {
        questionManager.createQuestion(question);
        return Response.ok().build();
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
