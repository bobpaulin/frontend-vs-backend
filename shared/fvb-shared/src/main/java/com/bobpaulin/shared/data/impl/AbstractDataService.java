package com.bobpaulin.shared.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractDataService {
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public void save(Object dataObject)
    {
        mongoTemplate.save(dataObject);
    }
    
    public MongoTemplate getTemplate()
    {
        return mongoTemplate;
    }
}
