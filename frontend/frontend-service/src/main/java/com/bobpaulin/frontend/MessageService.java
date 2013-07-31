package com.bobpaulin.frontend;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bobpaulin.shared.data.MessageDataService;

@Path("/messages")
public class MessageService {
    
    @Autowired
    private MessageDataService messageDataService;
    
    
    @GET
    @Path("/{userName}")
    @Produces("application/json")
    public Response getUserMessages(@PathParam("userName") String userName)
    {
        return Response.ok(messageDataService.getUserMessages(userName)).build();
    }

}
