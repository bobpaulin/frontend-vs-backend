package com.bobpaulin.shared.data;

import java.util.List;

import com.bobpaulin.shared.model.BookPreference;

public interface BookPreferenceDataService extends BaseDataService{

    public List<BookPreference> getUserBookPreferences(String userName);
    
}
