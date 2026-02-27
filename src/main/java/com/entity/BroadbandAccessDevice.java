package com.entity;

public interface BroadbandAccessDevice extends Identified, Labeled {
    String getHostname();

    String getSerialNumber();

    DeviceType getType();

    int getAvailablePorts();

    void setAvailablePorts(int availablePorts);

    Exchange getExchange();

    void setExchange(Exchange exchange);
}
