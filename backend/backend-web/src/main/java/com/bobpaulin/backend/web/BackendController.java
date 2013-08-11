package com.bobpaulin.backend.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.bobpaulin.shared.data.BookPreferenceDataService;
import com.bobpaulin.shared.data.MessageDataService;
import com.bobpaulin.shared.data.UserDataService;
import com.bobpaulin.shared.model.BookPreference;
import com.bobpaulin.shared.model.Message;
import com.bobpaulin.shared.model.User;
import com.bobpaulin.shared.model.book.BookResponse;
import com.bobpaulin.shared.model.book.VolumeItem;


@Controller
@RequestMapping("/main")    
public class BackendController {
    
    @Autowired
    private UserDataService userDataService;
    
    @Autowired
    private MessageDataService messageDataService;
    
    @Autowired
    private BookPreferenceDataService bookPreferenceDataService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String index(@CookieValue(defaultValue="bpaulin", value="userName" ) String userName, Model model)
    {
        List<VolumeItem> bookResults = new ArrayList<VolumeItem>();
        User user = userDataService.getUser(userName);
        List<BookPreference> bookPreferences = bookPreferenceDataService.getUserBookPreferences(userName);
        if(bookPreferences != null)
        {
            for(BookPreference currentPreference: bookPreferences)
            {
                Map<String, String> vars = new HashMap<String, String>();
                vars.put("query", currentPreference.getKeyword());
                BookResponse searchResults = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q={query}&country=US", BookResponse.class, vars);
                if(searchResults.getItems() != null)
                {
                    bookResults.addAll(searchResults.getItems());
                }
            }
            
        }
        
        BookPreference command = new BookPreference();
        command.setUserName(userName);
        model.addAttribute("command", command);
        model.addAttribute("bookPreferences", bookPreferences);
        model.addAttribute("user", user);
        model.addAttribute("bookResults", bookResults);
        
        
        return "main";
    }
    
    @RequestMapping(value = {"/review/{bookId}"}, method = RequestMethod.GET)
    public String review(@CookieValue(defaultValue="bpaulin", value="userName" ) String userName, @PathVariable("bookId") String bookId, Model model)
    {
        User user = userDataService.getUser(userName);
        List<Message> bookMessages = messageDataService.getBookMessages(bookId);
        
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("bookId", bookId);
        VolumeItem bookData = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes/{bookId}?country=US", VolumeItem.class, vars);
        
        Message message = new Message();
        message.setUserName(userName);
        message.setBookId(bookId);
        
        model.addAttribute("command", message);
        model.addAttribute("user", user);
        model.addAttribute("bookData", bookData);
        model.addAttribute("messageList", bookMessages);
        return "bookReview";
    }
    
    
    @RequestMapping(value= {"/createUser"}, method = RequestMethod.POST)
    public String createUser(@RequestParam("userName") String userName, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName )
    {
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userDataService.save(user);
        return "main";
    }
    
    @RequestMapping(value= { "/createMessage"}, method= RequestMethod.POST)
    public String createMessage(@ModelAttribute("newMessage")
    Message newMessage, BindingResult result)
    {
        
        newMessage.setPostDate(new Date());
        messageDataService.save(newMessage);
        return "redirect:review/" + newMessage.getBookId();
    }
    
    @RequestMapping(value= { "/createBookPreference"}, method= RequestMethod.POST)
    public String createBookPreference(@ModelAttribute("newPreference")
    BookPreference newBookPreference, BindingResult result)
    {
        bookPreferenceDataService.save(newBookPreference);
        return "redirect:/main";
    }
}
