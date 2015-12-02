package ngdemo.service.contract;

import ngdemo.domain.Device;

import java.util.List;

public interface DeviceService {

    List<Device> getAllDevices();

    Device getById(int id);

    Device createNewDevice(Device device);

    Device update(Device device);

    void remove(String id);

    int getNumberOfDevices();
}
