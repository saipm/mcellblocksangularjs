package ngdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ngdemo.domain.Device;
import ngdemo.repositories.contract.DeviceRepository;
import ngdemo.service.contract.DeviceService;

import java.util.List;

@Singleton
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Inject
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List getAllDevices() {
        return this.deviceRepository.getAll();
    }

    public Device getById(int id) {
        return this.deviceRepository.getById(id);
    }

    public Device createNewDevice(Device device) {
        Device u = this.deviceRepository.create(device);
        return u;
    }

    public Device update(Device device) {
        return this.deviceRepository.update(device);
    }

    public void remove(String id) {
        this.deviceRepository.remove(id);
    }

    public int getNumberOfDevices() {
        return this.deviceRepository.getNumberOfDevices();
    }
}
