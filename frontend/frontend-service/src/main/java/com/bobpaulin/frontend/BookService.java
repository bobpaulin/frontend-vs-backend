package com.bobpaulin.frontend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bobpaulin.frontend.client.BookApi;
import com.bobpaulin.shared.data.BookPreferenceDataService;
import com.bobpaulin.shared.model.BookPreference;
import com.bobpaulin.shared.model.book.VolumeItem;

@Path("/books")
public class BookService {
    
    @Autowired
    private BookPreferenceDataService bookPreferenceDataService;
    
    @Resource(name="bookApi")
    private BookApi bookApi;

    @GET
    @Produces("application/json")
    @Path("/user/{userName}")
    public Response searchBooks(@PathParam("userName") String userName)
    {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);
        
        List<VolumeItem> results = new ArrayList<VolumeItem>();
        
        List<BookPreference> bookPreferences = bookPreferenceDataService.getUserBookPreferences(userName);
        if(bookPreferences != null)
        {
            for(BookPreference currentPreference: bookPreferences)
            {
                results.addAll(bookApi.getSearchResults(currentPreference.getKeyword(), "US").getItems());
            }
        }
        
        return Response.ok(results).cacheControl(cc).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("/book/{bookId}")
    public Response getBooks(@PathParam("bookId") String bookId)
    {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);
        return Response.ok(bookApi.getBook(bookId, "US")).cacheControl(cc).build();
    }
}
