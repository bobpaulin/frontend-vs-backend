package com.bobpaulin.shared.data.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.bobpaulin.shared.data.MessageDataService;
import com.bobpaulin.shared.model.Message;

@Component
public class MessageDataServiceImpl extends AbstractDataService implements MessageDataService {
    
    public List<Message> getUserMessages(String userName)
    {
        Query userMessageQuery = query(where("userName").is(userName));
        userMessageQuery.sort().on("postDate", Order.ASCENDING);
        return getTemplate().find(userMessageQuery, Message.class);
    }
    
    public List<Message> getBookMessages(String bookId) {
        Query bookMessageQuery = query(where("bookId").is(bookId));
        bookMessageQuery.sort().on("postDate", Order.ASCENDING);
        return getTemplate().find(bookMessageQuery, Message.class);
    }
}
