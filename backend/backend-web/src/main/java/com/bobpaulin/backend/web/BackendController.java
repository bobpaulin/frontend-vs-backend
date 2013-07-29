package com.bobpaulin.backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobpaulin.shared.data.MessageDataService;
import com.bobpaulin.shared.data.UserDataService;
import com.bobpaulin.shared.model.Message;
import com.bobpaulin.shared.model.User;


@Controller
@RequestMapping("/main")
public class BackendController {
    
    @Autowired
    private UserDataService userDataService;
    
    @Autowired
    private MessageDataService messageDataService;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String index(@CookieValue(defaultValue="bpaulin", value="userName" ) String userName, Model model)
    {
        
        User user = userDataService.getUser(userName);
        
        model.addAttribute("user", user);
        model.addAttribute("messageList", messageDataService.getUserMessages(userName));
        
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
    
    @RequestMapping(value= { "/createMessage"}, method= RequestMethod.POST)
    public String createMessage(@RequestParam("userName") String userName, @RequestParam("message") String message)
    {
        Message newMessage = new Message();
        newMessage.setUserName(userName);
        newMessage.setMessage(message);
        messageDataService.save(newMessage);
        return "main";
    }
}
