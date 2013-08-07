package com.bobpaulin.shared.model.book;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumeItem {
    
    private VolumeInfo volumeInfo;
    
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
    
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
