package com.example.domain.model;

import org.joda.time.DateTime;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public class Text implements Serializable {

    private String id;
    private Reader textData;
    private DateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reader getTextData() {
        return textData;
    }

    public void setTextData(Reader textData) {
        this.textData = textData;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }
}
