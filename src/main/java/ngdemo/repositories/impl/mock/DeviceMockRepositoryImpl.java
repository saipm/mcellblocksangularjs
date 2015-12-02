package ngdemo.repositories.impl.mock;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.google.inject.Singleton;
import ngdemo.domain.NullDevice;
import ngdemo.domain.Device;
import ngdemo.repositories.contract.DeviceRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class DeviceMockRepositoryImpl extends GenericMockRepository<Device> implements DeviceRepository {

    private List<Device> devices = new ArrayList<Device>();

    public DeviceMockRepositoryImpl() {
        this.devices = this.createDevices();
    }

    public Device getById(String id) {
        for (Device u : this.devices) {
            if (u.getDevice() == id) {
                return u;
            }
        }
        return new NullDevice();
    }

    public List<Device> getAll() {
        return this.devices;
    }

    
    public Device create(Device device) {
        //device.setDevice((getCurrentMaxId() + 1));
    	device.setDevice("hello");
        this.devices.add(device);
        return device;
    }

    
    public Device update(Device device) {
        Device byId = this.getById(device.getDevice());
        //byId.setFirstName(device.getFirstName());
        //byId.setLastName(device.getLastName());
        return byId;
    }

    
    public void remove(int id) {
        Device byId = this.getById(id);
        this.devices.remove(byId);
    }

    
    public int getNumberOfDevices() {
        return this.devices.size();
    }

    private List<Device> createDevices() {
        int numberOfDevices = 10;
        for (int i = 0; i < numberOfDevices; i++) {
            Device device = new Device();
            device.setDevice("i + 1");
          //  device.setFirstName("Foo" + (i + 1));
          //  device.setLastName("Bar" + (i + 1));
            this.devices.add(device);
        }
        return this.devices;
    }

    private String getCurrentMaxId() {
        Ordering<Device> ordering = new Ordering<Device>() {

			@Override
			public int compare(Device arg0, Device arg1) {
				// TODO Auto-generated method stub
				return 0;
			}
            
            /*public int compare(Device left, Device right) {
                return Ints.compare(left.getId(), right.getId());
            }*/
        };
        return ordering.max(this.devices).getDevice();
    }

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}
}
