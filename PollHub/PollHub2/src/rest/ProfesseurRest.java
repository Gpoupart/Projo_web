package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import beans.Professeur;
import manager.ProfesseurManager;

@Path("/professeur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesseurRest {

    @EJB
    private ProfesseurManager professeurManager;

    @POST
    @Path("/create")
    public Response createProfesseur(Professeur professeur) {
        professeurManager.createProfesseur(professeur);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getProfesseur(@PathParam("id") int id) {
        Professeur professeur = professeurManager.getProfesseur(id);
        return Response.ok(professeur).build();
    }

    @GET
    public Response getAllProfesseurs() {
        List<Professeur> professeurs = professeurManager.getAllProfesseurs();
        return Response.ok(professeurs).build();
    }

    @PUT
    @Path("/update")
    public Response updateProfesseur(Professeur professeur) {
        Professeur updatedProfesseur = professeurManager.updateProfesseur(professeur);
        return Response.ok(updatedProfesseur).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteProfesseur(@PathParam("id") int id) {
        professeurManager.deleteProfesseur(id);
        return Response.ok().build();
    }
}
