package com.entity;

public interface Exchange extends Identified, Labeled {
    String getCode();

    String getName();

    String getLocation();
}
