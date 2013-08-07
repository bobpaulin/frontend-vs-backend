package com.bobpaulin.frontend;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import com.bobpaulin.frontend.client.BookApi;

@Path("/books")
public class BookService {
    
    @Resource(name="bookApi")
    private BookApi bookApi;

    @GET
    @Produces("application/json")
    public Response searchBooks(@QueryParam("search") String searchString)
    {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);
        return Response.ok(bookApi.getSearchResults(searchString, "US")).cacheControl(cc).build();
    }
}
