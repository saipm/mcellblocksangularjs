package ngdemo.web.rest;

import com.google.inject.Inject;
import ngdemo.domain.Device;
import ngdemo.service.contract.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Path("/install")
public class InstallRestService {

    //private final DeviceService deviceService;

   /* @Inject
    public DeviceRestService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }*/

    @GET
   // @RequestMapping(value = "/addskill", method = RequestMethod.GET)
    @Path("{device}/{fname}")
    @Produces(MediaType.APPLICATION_JSON)
    public String installApp(@PathParam("device") String device, @PathParam("fname") String filename){
	String command = "adb -s "+device+" -d install "+filename;
	System.out.println("install::::"+command);
	String line1 = "";
	List<Device> dlist = new ArrayList<Device>();
	Process p;
	try {
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		String line="";
		List<String> deviceList = new ArrayList<String>();
		BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	while ((line1 = rd.readLine()) != null) {
		System.out.println(line1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return line1;
   }  
}