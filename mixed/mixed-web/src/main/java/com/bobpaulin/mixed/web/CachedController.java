package com.bobpaulin.mixed.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.bobpaulin.shared.model.BookPreference;
import com.bobpaulin.shared.model.Message;
import com.bobpaulin.shared.model.User;
import com.bobpaulin.shared.model.book.BookResponse;
import com.bobpaulin.shared.model.book.ImageLinks;
import com.bobpaulin.shared.model.book.VolumeItem;

@Controller
@RequestMapping("/cached")
public class CachedController {
	
	@Autowired
    private RestTemplate restTemplate;
	
	private Map<String, Object> bookCache;
	
	
	public CachedController() {
		bookCache = new HashMap<String, Object>();
	}
	
	@RequestMapping(value = {"/books/search/{keyword}"}, method = RequestMethod.GET)
    public String searchHtml(@PathVariable("keyword") String keyword, Model model)
    {
    	Map<String, String> vars = new HashMap<String, String>();
        vars.put("query", keyword);
    	BookResponse searchResults = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q={query}&country=US", BookResponse.class, vars);
    	List<VolumeItem> searchItems = searchResults.getItems();
    	if(searchItems != null)
    	{
    		for(VolumeItem currentVolume: searchItems)
    		{
    			currentVolume.setDisplayReviewLink(true);
    			ImageLinks currentImageLinks = currentVolume.getVolumeInfo().getImageLinks();
    			if(currentImageLinks != null)
    			{
    				calculateDataUri(currentImageLinks);
    			}
    		}
    	}
    	model.addAttribute("items", searchItems);
    	return "allVolumes";
    }
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String index( Model model)
    {
        
        BookPreference command = new BookPreference();
        model.addAttribute("command", command);
        
        return "main";
    }
	
	@RequestMapping(value = {"/review/{bookId}"}, method = RequestMethod.GET)
    public String review( @PathVariable("bookId") String bookId, Model model)
    {
        
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("bookId", bookId);
        VolumeItem bookData = getBookData(bookId);
        
        if(bookData != null && bookData.getVolumeInfo() != null)
        {
        	ImageLinks imageLinks = bookData.getVolumeInfo().getImageLinks();
        	
        	calculateDataUri(imageLinks);
        }
        
        
        
        Message message = new Message();
        message.setBookId(bookId);
        
        model.addAttribute("command", message);
        model.addAttribute("bookData", bookData);
        return "bookReview";
    }
	
	private VolumeItem getBookData(String bookId) {
		VolumeItem bookData;
		if(bookCache.containsKey(bookId))
        {
        	bookData = (VolumeItem)bookCache.get(bookId);
        }else
        {
        	Map<String, String> vars = new HashMap<String, String>();
            vars.put("bookId", bookId);
            bookData = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes/{bookId}?country=US", VolumeItem.class, vars);
            bookCache.put(bookId, bookData);
        }
		return bookData;
	}

	private void calculateDataUri(ImageLinks currentImageLinks) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL imageUrl = new URL(currentImageLinks.getThumbnail());
			URLConnection myconn = imageUrl.openConnection();
			//Add user agent to fake out google
			myconn.setRequestProperty("User-Agent","User-Agent:Mozilla/5.0 (Windows NT 6.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1");
			myconn.setRequestProperty("Accept", "Accept:image/*");
			ImageIO.write(ImageIO.read(myconn.getInputStream()), "jpeg", baos);
			currentImageLinks.setThumbnail("data:image/jpeg;base64,"+ DatatypeConverter.printBase64Binary(baos.toByteArray()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
