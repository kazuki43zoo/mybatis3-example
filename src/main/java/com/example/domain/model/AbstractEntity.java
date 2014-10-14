package com.example.domain.model;

import com.example.infra.mybatis.plugin.VersionPlugin;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

import java.io.Serializable;

/**
 * Created by shimizukazuki on 2014/10/12.
 */
public abstract class AbstractEntity<ID extends Serializable> implements Auditable<String, ID>, Versionable {

    private String createdBy;
    private String lastModifiedBy;
    private DateTime createdDate;
    private DateTime lastModifiedDate;
    private long version;

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public void setVersion(long version) {
        this.version = version;
    }

}
