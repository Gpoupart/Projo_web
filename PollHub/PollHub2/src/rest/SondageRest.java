package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import beans.Sondage;
import manager.SondageManager;

@Path("/sondage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SondageRest {

    @EJB
    private SondageManager sondageManager;

    @POST
    @Path("/create")
    public Response createSondage(Sondage sondage) {
        sondageManager.createSondage(sondage);
        return Response.ok().build();
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
