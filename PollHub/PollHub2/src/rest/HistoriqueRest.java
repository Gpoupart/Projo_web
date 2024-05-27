package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import beans.Historique;
import manager.HistoriqueManager;

@Path("/historique")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoriqueRest {

    @EJB
    private HistoriqueManager historiqueManager;

    @POST
    @Path("/create")
    public Response createHistorique(Historique historique) {
        historiqueManager.createHistorique(historique);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getHistorique(@PathParam("id") int id) {
        Historique historique = historiqueManager.getHistorique(id);
        return Response.ok(historique).build();
    }

    @GET
    public Response getAllHistoriques() {
        List<Historique> historiques = historiqueManager.getAllHistoriques();
        return Response.ok(historiques).build();
    }

    @PUT
    @Path("/update")
    public Response updateHistorique(Historique historique) {
        Historique updatedHistorique = historiqueManager.updateHistorique(historique);
        return Response.ok(updatedHistorique).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteHistorique(@PathParam("id") int id) {
        historiqueManager.deleteHistorique(id);
        return Response.ok().build();
    }
}
