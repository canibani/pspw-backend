package nl.can.project.pspw.app.controllers.MatchControllers;

import nl.can.project.pspw.app.daos.EventDAOs.EventsDAO;
import nl.can.project.pspw.app.daos.MatchDAOs.MatchDetailDAO;
import nl.can.project.pspw.app.dto.MatchModels.MatchTypesResponse;
import nl.can.project.pspw.app.dto.MatchModels.PrizeResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/match")
public class MatchController {

    @GET
    @Path("/matchDetail/matchTypes")
    @Produces("application/json")
    public Response getAllMatchTypes() {
        MatchTypesResponse response = new MatchTypesResponse();
        response.setMatchTypes(matchDetailDAO.getAllMatchTypes());
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/matchDetail/prizes/{promotion}")
    @Produces("application/json")
    public Response getAllPrizesForPromotion(@PathParam("promotion") String promotion) {
        PrizeResponse response = new PrizeResponse();
        response.setPrizes(matchDetailDAO.getPrizesForPromotion(promotion));
        return Response.ok().entity(response).build();
    }

    MatchDetailDAO matchDetailDAO;

    @Inject
    public void setMatchDetailDAO(MatchDetailDAO matchDetailDAO) {
        this.matchDetailDAO = matchDetailDAO;
    }
}
