package com.bobpaulin.shared.data;

import com.bobpaulin.shared.model.User;

public interface UserDataService extends BaseDataService {
    
    public User getUser(String userName);
    
}
