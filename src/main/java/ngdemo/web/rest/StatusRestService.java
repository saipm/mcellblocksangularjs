package ngdemo.web.rest;

import com.google.inject.Inject;

import ngdemo.domain.App;
import ngdemo.domain.Data;
import ngdemo.domain.Device;
import ngdemo.domain.Status;
import ngdemo.service.contract.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/devicetests")
public class StatusRestService {

    private final DeviceService deviceService;

    @Inject
    public StatusRestService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> getAllUsersInJSON() {
    	
    	System.out.println("adb devices");
    	String command = "adb devices";
		List<Status> dlist = new ArrayList<Status>();
		org.codehaus.jettison.json.JSONObject finalObject = new org.codehaus.jettison.json.JSONObject();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			String line="";
			List<String> deviceList = new ArrayList<String>();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
			    if (line.endsWith("device")) {
			        deviceList.add(line.split("\\t")[0]);
			    } if (line.endsWith("unauthorized")) {
			        deviceList.add(line.split("\\t")[0]);
			    }
			}
			for (String device : deviceList) {
				//System.out.println(device);
				Status d = new Status();
				d.setDevice(device);
			    dlist.add(d);
			}
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return dlist;
    }
    
    
    @GET
    @Path("/devicecount")
    @Produces(MediaType.APPLICATION_JSON)
    public int getcountofDevices() {
    
    	TestcaseDao td = new TestcaseDao();
    	int count=td.getDevicesCount();
    	return count;
    
    }
    
    @GET
    @Path("/testcount")
    @Produces(MediaType.APPLICATION_JSON)
    public int getcountofTests() {
    
    	TestcaseDao td = new TestcaseDao();
    	int count=td.getTestcasesCount();
    	return count;
    
    }
    @GET
    @Path("/errorcount")
    @Produces(MediaType.APPLICATION_JSON)
    public int geterrorTests() {
    
    	TestcaseDao td = new TestcaseDao();
    	int count=td.getErrorsCount();
    	return count;
    
    }
    
  
}
