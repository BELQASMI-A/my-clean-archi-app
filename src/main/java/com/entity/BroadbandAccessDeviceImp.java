
package com.entity;

public class BroadbandAccessDeviceImp implements BroadbandAccessDevice {

    private String id;
    private String label;
    private Exchange exchange;

    private String hostname;
    private String serialNumber;
    private DeviceType type;
    private int availablePorts;

    public BroadbandAccessDeviceImp(String hostname, String serialNumber, DeviceType type) {
        this.hostname = hostname;
        this.serialNumber = serialNumber;
        this.type = type;
        this.id = serialNumber;
        this.label = hostname;
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

    public String getHostname() {
        return hostname;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public DeviceType getType() {
        return type;
    }

    public int getAvailablePorts() {
        return availablePorts;
    }

    public void setAvailablePorts(int availablePorts) {
        this.availablePorts = availablePorts;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "BroadbandAccessDevice{" +
                "exchange=" + exchange +
                ", hostname='" + hostname + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", type=" + type +
                ", availablePorts=" + availablePorts +
                '}';
    }

}