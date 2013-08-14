package com.bobpaulin.shared.model.book;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumeItem {
    
    private VolumeInfo volumeInfo;
    
    private boolean displayReviewLink;
    
    private String id;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
    
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
    
    public boolean isDisplayReviewLink() {
		return displayReviewLink;
	}
    
    public void setDisplayReviewLink(boolean displayReviewLink) {
		this.displayReviewLink = displayReviewLink;
	}
}
