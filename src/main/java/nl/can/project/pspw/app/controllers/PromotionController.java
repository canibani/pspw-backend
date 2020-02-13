package nl.can.project.pspw.app.controllers;

import nl.can.project.pspw.app.daos.PromotionsDAO;
import nl.can.project.pspw.app.daos.UserDAO;
import nl.can.project.pspw.app.dto.PromotionsResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/promotions")
public class PromotionController {

    private PromotionsDAO promotionsDAO;
    private UserDAO userDAO;

    @GET
    @Path("/getPromotions")
    @Produces("application/json")
    public Response getPromotions(@QueryParam("token") String token) {
        if (!userDAO.checkToken(token)){
            return Response.status(403).build();
        }
        PromotionsResponse response = new PromotionsResponse();
        response.setPromotions(promotionsDAO.getPromotions());
        return Response.ok().entity(response).build();
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setPromotionsDAO(PromotionsDAO promotionsDAO) { this.promotionsDAO = promotionsDAO; }
}
