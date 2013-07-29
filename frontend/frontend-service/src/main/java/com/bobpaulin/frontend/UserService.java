package com.bobpaulin.frontend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.bobpaulin.shared.data.UserDataService;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/user")
public class UserService {
    
    @Autowired
    private UserDataService userDataService;
    
    @GET
    @Path("/{userName}")
    @Produces("application/json")
    public Response getUser(@PathParam("userName") String userName)
    {
        return Response.ok(userDataService.getUser(userName)).build();
    }
}
