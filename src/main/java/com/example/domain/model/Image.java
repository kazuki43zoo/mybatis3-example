package com.example.domain.model;

import org.joda.time.DateTime;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public class Image implements Serializable {

    private String id;
    private InputStream imageData;
    private DateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InputStream getImageData() {
        return imageData;
    }

    public void setImageData(InputStream imageData) {
        this.imageData = imageData;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }
}
