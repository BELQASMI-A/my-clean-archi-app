package com.entity;

public class UserImp implements User {
    private String id;
    private String label;
    private String ownerId;
    private String description;

    public UserImp(String id, String label) {
        this.id = id;
        this.label = label;
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
    public String toString() {
        return "UserImp{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
