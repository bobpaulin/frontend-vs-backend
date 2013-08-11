package com.bobpaulin.shared.data;

import java.util.List;

import com.bobpaulin.shared.model.Message;

public interface MessageDataService extends BaseDataService {
    public List<Message> getUserMessages(String userName);
    
    public List<Message> getBookMessages(String bookId);
}
