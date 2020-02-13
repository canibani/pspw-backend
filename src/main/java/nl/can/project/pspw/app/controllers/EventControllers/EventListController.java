package nl.can.project.pspw.app.controllers.EventControllers;

import nl.can.project.pspw.app.daos.EventDAOs.EventListDAO;
import nl.can.project.pspw.app.daos.UserDAO;
import nl.can.project.pspw.app.dto.EventModels.AddEventRequest;
import nl.can.project.pspw.app.dto.EventModels.EditEventRequest;
import nl.can.project.pspw.app.dto.EventModels.EventListResponse;
import nl.can.project.pspw.app.dto.WrestlingEvent;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/eventList")
public class EventListController {

    @GET
    @Produces("application/json")
    public Response getListOfAllEvents(@QueryParam("token") String token) {
        if (!userDAO.checkToken(token)) {
            return Response.status(403).build();
        }
        EventListResponse response = new EventListResponse();
        ArrayList<WrestlingEvent> eventList = eventListDAO.getListOfEvents();
        response.setEventList(eventList);
        return Response.ok().entity(response).build();
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response addEvent(@QueryParam("token") String token, AddEventRequest addEventRequest) {
        if (!userDAO.checkToken(token)){
            return Response.status(401).build();
        }
        try {
            EventListResponse response = new EventListResponse();
            eventListDAO.addEvent(addEventRequest.getName(), addEventRequest.getDate(), addEventRequest.getPromotion());
            ArrayList<WrestlingEvent> eventList = eventListDAO.getListOfEvents();
            response.setEventList(eventList);
            return Response.ok().entity(response).build();
        } catch (SQLException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/edit/{eventId}")
    @Produces("application/json")
    public Response editEvent(@QueryParam("token") String token, @PathParam("eventId") int eventId, EditEventRequest editEventRequest) {
        if (!userDAO.checkToken(token)){
            return Response.status(401).build();
        }
        try {
            eventListDAO.editEvent(eventId, editEventRequest);
            EventListResponse response = new EventListResponse();
            ArrayList<WrestlingEvent> eventList = eventListDAO.getListOfEvents();
            response.setEventList(eventList);
            return Response.ok().entity(response).build();
        } catch (SQLException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{eventId}")
    @Produces("application/json")
    public Response removeEvent(@QueryParam("token") String token, @PathParam("eventId") int eventId) {
        if (!userDAO.checkToken(token)){
            return Response.status(401).build();
        }
        try {
            eventListDAO.removeEvent(eventId);
            EventListResponse response = new EventListResponse();
            ArrayList<WrestlingEvent> eventList = eventListDAO.getListOfEvents();
            response.setEventList(eventList);
            return Response.ok().entity(response).build();
        } catch (SQLException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    private UserDAO userDAO;
    private EventListDAO eventListDAO;

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setEventListDAO(EventListDAO eventListDAO) {
        this.eventListDAO = eventListDAO;
    }
}
