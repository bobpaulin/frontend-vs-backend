package com.bobpaulin.shared.data.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.stereotype.Component;

import com.bobpaulin.shared.data.UserDataService;
import com.bobpaulin.shared.model.User;

@Component
public class UserDataServiceImpl extends AbstractDataService implements UserDataService {
    
    public User getUser(String userName)
    {
        return getTemplate().findOne(query(where("userName").is(userName)), User.class);
    }
}
