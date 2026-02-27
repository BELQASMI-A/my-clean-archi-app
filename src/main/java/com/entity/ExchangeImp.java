
package com.entity;

public class ExchangeImp implements Exchange {

    private String id;
    private String label;
    private final String code;
    private final String name;
    private final String location;

    public ExchangeImp(String code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.id = code; // ID simpliste
        this.label = name;
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
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "ExchangeImp{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}