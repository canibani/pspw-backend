package nl.can.project.pspw.app.controllers;

import nl.can.project.pspw.app.daos.UserDAO;
import nl.can.project.pspw.app.dto.User;
import nl.can.project.pspw.app.dto.UserRequest;
import nl.can.project.pspw.app.dto.UserResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {
    private UserDAO userDAO;

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public Response login(UserRequest userRequest) {
        UserResponse response = new UserResponse();
        if (userRequest.getUsername() != null && userRequest.getPassword() != null) {
            User user = userDAO.getUserByUsername(userRequest.getUsername());
            if (user != null) {
                System.out.println(user.getPassword());
                System.out.println(userRequest.getPassword());
                if (user.getPassword().equals(userRequest.getPassword())) {
                    response.setUsername(user.getUsername());
                    response.setToken(user.getToken());
                    return Response.ok().entity(response).build();
                }
            }
        }
        return Response.status(403).build();
    }

    @POST
    @Path("/register")
    @Produces("application/json")
    public Response register() {
        UserResponse response = new UserResponse();

        return Response.ok().entity(response).build();
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
