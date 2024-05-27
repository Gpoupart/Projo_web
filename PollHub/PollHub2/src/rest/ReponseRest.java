package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import beans.Reponse;
import manager.ReponseManager;

@Path("/reponse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReponseRest {

    @EJB
    private ReponseManager reponseManager;

    @POST
    @Path("/create")
    public Response createReponse(Reponse reponse) {
        reponseManager.createReponse(reponse);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getReponse(@PathParam("id") int id) {
        Reponse reponse = reponseManager.getReponse(id);
        return Response.ok(reponse).build();
    }

    @GET
    public Response getAllReponses() {
        List<Reponse> reponses = reponseManager.getAllReponses();
        return Response.ok(reponses).build();
    }

    @PUT
    @Path("/update")
    public Response updateReponse(Reponse reponse) {
        Reponse updatedReponse = reponseManager.updateReponse(reponse);
        return Response.ok(updatedReponse).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteReponse(@PathParam("id") int id) {
        reponseManager.deleteReponse(id);
        return Response.ok().build();
    }
}
