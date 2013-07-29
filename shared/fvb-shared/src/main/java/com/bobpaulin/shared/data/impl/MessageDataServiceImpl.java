package com.bobpaulin.shared.data.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bobpaulin.shared.data.MessageDataService;
import com.bobpaulin.shared.model.Message;

@Component
public class MessageDataServiceImpl extends AbstractDataService implements MessageDataService {
    
    public List<Message> getUserMessages(String userName)
    {
        return getTemplate().find(query(where("userName").is(userName)), Message.class);
    }
}
