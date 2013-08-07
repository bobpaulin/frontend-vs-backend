package com.bobpaulin.shared.model.book;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class BookResponse {
    
    private List<VolumeItem> items;
    
    public List<VolumeItem> getItems() {
        return items;
    }
    
    public void setItems(List<VolumeItem> items) {
        this.items = items;
    }
}
