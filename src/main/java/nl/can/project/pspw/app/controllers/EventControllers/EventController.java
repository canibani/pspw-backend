package nl.can.project.pspw.app.controllers.EventControllers;

import nl.can.project.pspw.app.daos.EventDAOs.EventsDAO;
import nl.can.project.pspw.app.dto.EventModels.EventDataResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/event")
public class EventController {

    private EventsDAO eventsDAO;

    @GET
    @Path("/eventData/{eventId}")
    @Produces("application/json")
    public Response getEventData(@PathParam("eventId") int eventId) {
        EventDataResponse response = eventsDAO.getEventData(eventId);
        return Response.ok().entity(response).build();
    }


    @Inject
    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }


}
