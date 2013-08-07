package com.bobpaulin.backend.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.bobpaulin.shared.data.MessageDataService;
import com.bobpaulin.shared.data.UserDataService;
import com.bobpaulin.shared.model.Message;
import com.bobpaulin.shared.model.User;
import com.bobpaulin.shared.model.book.BookResponse;


@Controller
@RequestMapping("/main")    
public class BackendController {
    
    @Autowired
    private UserDataService userDataService;
    
    @Autowired
    private MessageDataService messageDataService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String index(@CookieValue(defaultValue="bpaulin", value="userName" ) String userName, Model model)
    {
        
        User user = userDataService.getUser(userName);
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("query", "vikings");
        BookResponse searchResults = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q={query}&country=US", BookResponse.class, vars);
        
        model.addAttribute("user", user);
        model.addAttribute("messageList", messageDataService.getUserMessages(userName));
        model.addAttribute("bookResults", searchResults);
        
        
        
        return "main";
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
    
    @RequestMapping(value={"message"}, method=RequestMethod.GET)
    public String openMessageForm(@CookieValue(defaultValue="bpaulin", value="userName" ) String userName, Model model )
    {
        Message message = new Message();
        message.setUserName(userName);
        model.addAttribute("messageList", messageDataService.getUserMessages(userName));
        model.addAttribute("command", message);
        return "message";
    }
    
    @RequestMapping(value= { "/createMessage"}, method= RequestMethod.POST)
    public String createMessage(@ModelAttribute("newMessage")
    Message newMessage, BindingResult result)
    {
        messageDataService.save(newMessage);
        return "redirect:message";
    }
}
