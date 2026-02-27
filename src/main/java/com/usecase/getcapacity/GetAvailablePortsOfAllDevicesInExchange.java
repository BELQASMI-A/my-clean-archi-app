package com.usecase.getcapacity;

import com.entity.*;

import java.util.List;

public interface GetAvailablePortsOfAllDevicesInExchange {

    List<BroadbandAccessDevice> getAvailablePortsOfAllDevicesInExchange(String exchangeCode);

}