package com.bobpaulin.shared.data.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bobpaulin.shared.data.BookPreferenceDataService;
import com.bobpaulin.shared.model.BookPreference;

@Component
public class BookPreferenceDataServiceImpl extends AbstractDataService implements BookPreferenceDataService {
    
    public List<BookPreference> getUserBookPreferences(String userName) {
        return getTemplate().find(query(where("userName").is(userName)), BookPreference.class);
    }
}
