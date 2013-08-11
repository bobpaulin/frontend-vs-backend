package com.bobpaulin.frontend;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bobpaulin.shared.data.MessageDataService;
import com.bobpaulin.shared.model.Message;

@Path("/messages")
public class MessageService {
    
    @Autowired
    private MessageDataService messageDataService;
    
    
    @GET
    @Path("/user/{userName}")
    @Produces("application/json")
    public Response getUserMessages(@PathParam("userName") String userName)
    {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);
        return Response.ok(messageDataService.getUserMessages(userName)).cacheControl(cc).build();
    }
    
    @GET
    @Path("/book/{bookId}")
    @Produces("application/json")
    public Response getBookMessages(@PathParam("bookId") String bookId)
    {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);
        return Response.ok(messageDataService.getBookMessages(bookId)).cacheControl(cc).build();
    }
    
    @POST
    @Path("/book/{bookId}")
    @Produces("application/json")
    public Response addMessages(@PathParam("bookId") String bookId, Message message)
    {
        messageDataService.save(message);
        return Response.ok().build();
    }

}
