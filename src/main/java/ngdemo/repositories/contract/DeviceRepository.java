package ngdemo.repositories.contract;

import ngdemo.domain.Device;

public interface DeviceRepository extends Repository<Device> {
    Device create(Device device);

    Device update(Device device);

    void remove(String id);

    int getNumberOfDevices();
}
