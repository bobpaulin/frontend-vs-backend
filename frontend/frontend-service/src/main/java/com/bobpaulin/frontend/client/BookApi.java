package com.bobpaulin.frontend.client;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.bobpaulin.shared.model.book.BookResponse;

@Path("/books/v1")
public interface BookApi {
    
    @GET
    @Path("/volumes")
    public BookResponse getSearchResults(@QueryParam("q") String query, @DefaultValue("US") @QueryParam("country") String country);

}
