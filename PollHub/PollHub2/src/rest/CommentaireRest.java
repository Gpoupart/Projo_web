package rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import beans.Commentaire;
import manager.CommentaireManager;

@Path("/commentaire")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentaireRest {

    @EJB
    private CommentaireManager commentaireManager;

    @POST
    @Path("/create")
    public Response createCommentaire(Commentaire commentaire) {
        commentaireManager.createCommentaire(commentaire);
        return Response.ok().build();
    }

    @GET
    @Path("/{auteur}")
    public Response getCommentaire(@PathParam("auteur") int auteur) {
        Commentaire commentaire = commentaireManager.getCommentaire(auteur);
        return Response.ok(commentaire).build();
    }

    @GET
    public Response getAllCommentaires() {
        List<Commentaire> commentaires = commentaireManager.getAllCommentaires();
        return Response.ok(commentaires).build();
    }

    @PUT
    @Path("/update")
    public Response updateCommentaire(Commentaire commentaire) {
        Commentaire updatedCommentaire = commentaireManager.updateCommentaire(commentaire);
        return Response.ok(updatedCommentaire).build();
    }

    @DELETE
    @Path("/delete/{auteur}")
    public Response deleteCommentaire(@PathParam("auteur") int auteur) {
        commentaireManager.deleteCommentaire(auteur);
        return Response.ok().build();
    }
}
