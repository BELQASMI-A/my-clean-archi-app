package com.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupImp implements Group {
    private String id;
    private String label;
    private String ownerId;
    private String description;
    private int dbId;
    private String prefix;
    private int groupIdNumeric;
    private String parentId;
    private Integer parentIdNumeric;
    private long privileges;
    private long flags;
    private Date dateC;
    private Date dateM;
    private Boolean expired;
    private Date dateofExpiration;
    private String externalDirectoryId;
    private List<User> users = new ArrayList<>();

    public GroupImp(String id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public int getDbId() {
        return dbId;
    }

    @Override
    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public int getGroupIdNumeric() {
        return groupIdNumeric;
    }

    @Override
    public void setGroupIdNumeric(int groupIdNumeric) {
        this.groupIdNumeric = groupIdNumeric;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getParentIdNumeric() {
        return parentIdNumeric;
    }

    @Override
    public void setParentIdNumeric(Integer parentIdNumeric) {
        this.parentIdNumeric = parentIdNumeric;
    }

    @Override
    public long getPrivileges() {
        return privileges;
    }

    @Override
    public void setPrivileges(long privileges) {
        this.privileges = privileges;
    }

    @Override
    public long getFlags() {
        return flags;
    }

    @Override
    public void setFlags(long flags) {
        this.flags = flags;
    }

    @Override
    public Date getDateC() {
        return dateC;
    }

    @Override
    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }

    @Override
    public Date getDateM() {
        return dateM;
    }

    @Override
    public void setDateM(Date dateM) {
        this.dateM = dateM;
    }

    @Override
    public Boolean isExpired() {
        return expired;
    }

    @Override
    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @Override
    public Date getDateofExpiration() {
        return dateofExpiration;
    }

    @Override
    public void setDateofExpiration(Date dateofExpiration) {
        this.dateofExpiration = dateofExpiration;
    }

    @Override
    public String getExternalDirectoryId() {
        return externalDirectoryId;
    }

    @Override
    public void setExternalDirectoryId(String externalDirectoryId) {
        this.externalDirectoryId = externalDirectoryId;
    }

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }
}
