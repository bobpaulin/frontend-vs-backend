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

import com.bobpaulin.shared.data.BookPreferenceDataService;
import com.bobpaulin.shared.model.BookPreference;

@Path("/bookPreferences")
public class BookPreferenceService {
    
    @Autowired
    private BookPreferenceDataService bookPreferenceDataService;
    
    @GET
    @Produces("application/json")
    @Path("/user/{userName}")
    public Response getUserBookPreferences(@PathParam("userName") String userName)
    {
        List<BookPreference> userBookPreferences = bookPreferenceDataService.getUserBookPreferences(userName);
        
        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);
        
        return Response.ok(userBookPreferences).cacheControl(cc).build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/user/{userName}")
    public Response addUserBookPreference(@PathParam("userName") String userName, BookPreference bookPreference)
    {
        bookPreferenceDataService.save(bookPreference);
        return Response.ok().build();
    }

}
