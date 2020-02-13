package nl.can.project.pspw.app.controllers;

import nl.can.project.pspw.app.daos.PredictionsDAO;
import nl.can.project.pspw.app.dto.*;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/prediction")
public class PredictionController {
    private PredictionsDAO predictionsDAO;

    @POST
    @Path("/show/{eventId}/user/{token}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response makePrediction(@PathParam("eventId") int eventId,
                                   @PathParam("token") String token,
                                   PredictionsRequest predictions) {
        System.out.println(predictions.getPredictions().length);
        PredictionsResponse response = new PredictionsResponse();
        return Response.ok().entity(response).build();
    }

    @Inject
    public void setPredictionsDAO(PredictionsDAO predictionsDAO) {
        this.predictionsDAO = predictionsDAO;
    }
}
