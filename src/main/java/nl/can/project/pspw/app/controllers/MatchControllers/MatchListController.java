package nl.can.project.pspw.app.controllers.MatchControllers;

import nl.can.project.pspw.app.daos.MatchDAOs.MatchListDAO;
import nl.can.project.pspw.app.daos.UserDAO;
import nl.can.project.pspw.app.dto.MatchModels.Match;
import nl.can.project.pspw.app.dto.MatchModels.MatchListResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/matchList")
public class MatchListController {

    @GET
    @Path(("/{eventId}"))
    @Produces("application/json")
    public Response getListOfMatchesForEvent(@QueryParam("token") String token, @PathParam("eventId") int eventId) {
        if (!userDAO.checkToken(token)){
            return Response.status(403).build();
        }
        MatchListResponse response = new MatchListResponse();
        ArrayList<Match> matchList = matchListDAO.getEventMatches(eventId);
        response.setMatches(matchList);
        return Response.ok().entity(response).build();
    }


    private UserDAO userDAO;
    private MatchListDAO matchListDAO;

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setMatchListDAO(MatchListDAO matchListDAO) {
        this.matchListDAO = matchListDAO;
    }
}
