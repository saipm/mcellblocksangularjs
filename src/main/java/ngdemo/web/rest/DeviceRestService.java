package ngdemo.web.rest;

import com.google.inject.Inject;

import ngdemo.domain.App;
import ngdemo.domain.Data;
import ngdemo.domain.Device;
import ngdemo.service.contract.DeviceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/devices")
public class DeviceRestService {

    private final DeviceService deviceService;

    @Inject
    public DeviceRestService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @GET
    @Path("/install/{device}/{file}")
    @Produces(MediaType.APPLICATION_JSON)
    public String installApp(@PathParam("device") String device, @PathParam("file") String file){
	String command = "adb -s "+device+" -d install "+"/home/saisreem/Downloads/HelloWorld.apk";
	System.out.println("install::::"+command);
	String line1 = "";
	List<Device> dlist = new ArrayList<Device>();
	Process p;
	 StringBuffer buffer = new StringBuffer("output:");
	try {
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		String line="";
		List<String> deviceList = new ArrayList<String>();
		BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	while ((line1 = rd.readLine()) != null) {
		System.out.println(line1);
		buffer.append(line1 + "\n");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return buffer.toString();
   }  
    @GET
    @Path("/upload/{device}/{file}")
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadFile(@PathParam("device") String device, @PathParam("file") String file){
    	
    	file = file.replaceAll("aaa","/");
    	System.out.println("sddsd"+device);
    	System.out.println("jdshdhffd"+file);
    	 StringBuffer buffer = new StringBuffer("output:");
	String command = "adb -s "+device+" push "+file+"  /sdcard/Download/";
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
		buffer.append(line1 + "\n");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return buffer.toString();
   }  

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Device> getAllUsersInJSON() {
    	
    	System.out.println("adb devices");
    	String command = "adb devices";
    	SampleJavaTest st = new SampleJavaTest();
		List<Device> dlist = new ArrayList<Device>();
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
				String model=SampleJavaTest.getDeviceParameters(device);
				//System.out.println(device);
				Device d = new Device();
				d.setDevice(device);
				d.setModel(model);
			    dlist.add(d);
			}
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return dlist;
    }
    
    @GET
    @Path("/apps/{device}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getAllAppsInJSON(@PathParam("device") String device) {
    	
    	System.out.println("adb -s TA93304LE5 shell pm list packages -3");
    	String command = "adb -s "+device+" shell pm list packages -3";
		List<App> dlist = new ArrayList<App>();
		org.codehaus.jettison.json.JSONObject finalObject = new org.codehaus.jettison.json.JSONObject();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			String line="";
			List<String> deviceList = new ArrayList<String>();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				 deviceList.add(line.split(":")[1]);
				}
			for(String app: deviceList)
			{
				App ap = new App();
				ap.setApp(app);
				ap.setDevice(device);
			    dlist.add(ap);
			}
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return dlist;
    }
    
    @GET
    @Path("/command/{cName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Data> runCommand(@PathParam("cName") String deviceId) {	
    	String command="adb -s "+deviceId+ " shell getprop";
    	System.out.println("command:::"+command);
    	List<Data> data = new ArrayList<Data>();
		List<String> deviceList = new ArrayList<String>();
		Data d = new Data();
	   Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			String line1="";
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	while ((line1 = rd.readLine()) != null) {
	    		deviceList.add(line1);
			}
	    	for (String device : deviceList) {
				System.out.println(device);
				if(device.contains("model")||device.contains("version.sdk")||device.contains("manufacturer")
						||device.contains("hardware")||device.contains("platform")
						||device.contains("revision")||device.contains("product.name")
						||device.contains("brand")){
					device = device.replace("[", "").replace("]", "").replace("ro.", "");
				Data d1 = new Data();
				d1.setData(device);
			    data.add(d1);
			}
	    	}
	    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return data;
    }
    
    @GET
    @Path("/memory/{cName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> Memory(@PathParam("cName") String deviceId) {	
    	String command="adb -s "+deviceId+ " shell cat /proc/meminfo";
    	System.out.println("command:::"+command);
    	List<App> data = new ArrayList<App>();
		List<String> deviceList = new ArrayList<String>();
		App d = new App();
	   Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			String line="",line1="",line2="";
			String[] temp;
			String delimiter="\\s+";
			int i=0;
			BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	while ((line = rd.readLine()) != null) {
	    		if(i<4){
	    		line = line.replace(":", " ");
	    		temp=line.split(delimiter);
	    		line1 = temp[0];
	    		line2= temp[1];
				App a = new App();
				a.setDevice(line1);
				a.setApp(line2);
			    data.add(a);
	    		}else
	    			break;
	    		i++;
			}
	    	for(App a1: data){
	    		System.out.println(a1.getDevice()+"   "+a1.getApp());
	    	}
	    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return data;
    }
    
    @GET
    @Path("/memoryperapp/{cName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> MemoryPerApp(@PathParam("cName") String deviceId) {
    	//List<App> app = 
    	String command="adb -s "+deviceId+ " shell dumpsys meminfo| grep com.";
    	System.out.println("command:::"+command);
    	List<App> data = new ArrayList<App>();
		List<String> deviceList = new ArrayList<String>();
		App d = new App();
	   Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			String line="",line1="",line2="";
			String[] temp;
			String delimiter="kB:";
			Set<String> hs = new HashSet<>();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	while ((line = rd.readLine()) != null) {
	    		line=line.trim();
	    		deviceList.add(line);	
			}
	    	hs.addAll(deviceList);
	    	deviceList.clear();
	    	deviceList.addAll(hs);
	    	for(String dnew : deviceList){
	    	//	line = line.replace(":", " ");
	    		temp=dnew.split(delimiter);
	    	/*	System.out.println("temp[0]::::"+temp[0]);
	    		System.out.println("temp[1]::::"+temp[1]);*/
	    		line1 = temp[0].trim();
	    		line2= temp[1];
				App a = new App();a.setDevice(line1);a.setApp(line2);
			    data.add(a);
	    	}
	    	
	    	/*for(App a1: data){
	    		System.out.println(a1.getDevice()+"   "+a1.getApp());
	    	}*/
	    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return data;
    }
    
    
    
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getAppsInJSON(/*@PathParam("device") String device)*/) {
    	
    	System.out.println("adb -s TA93304LE5 shell pm list packages -3");
    	//String command = "adb -s "+device+" shell pm list packages -3";
		List<App> dlist = new ArrayList<App>();
		List<App> dlist1 = new ArrayList<App>();
		List<Device> devices = new ArrayList<Device>();
		DeviceRestService ds = new DeviceRestService((DeviceService) deviceService);
		devices = ds.getAllUsersInJSON();
		
		
		for(int i=0; i<devices.size();i++){
			System.out.println("i:::"+i);
			System.out.println("size:::"+devices.size());
			Device d=devices.get(i);
			String dname=d.getDevice();
			dlist= ds.getAllAppsInJSON(dname);
			dlist1.addAll(dlist);
		}	
    	return dlist1;
    }
    
    
   
    
    @GET
    @Path("/battery/{device}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getbatteryinfo(@PathParam("device") String device) {
    	
    	System.out.println("adb -s TA93304LE5 shell dumpsys battery");
    	String command = "adb -s "+device+" shell dumpsys battery";
		List<App> dlist = new ArrayList<App>();String line1="";
		org.codehaus.jettison.json.JSONObject finalObject = new org.codehaus.jettison.json.JSONObject();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			
			List<String> deviceList = new ArrayList<String>();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line1 = input.readLine()) != null) {
				 System.out.println(line1+":::::line1");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return line1;
    }

    @GET
    @Path("/run/{device}/{file}")
   // @Produces(MediaType.APPLICATION_JSON)
    public String RunApp(@PathParam("device") String device, @PathParam("file") String file){
    	//adb -s TA93304LE5 shell monkey -p com.whatsapp -c android.intent.category.LAUNCHER 1
	String command = "adb -s "+device+" shell monkey -p "+file+" -c android.intent.category.LAUNCHER 1";
	System.out.println("install::::"+command);
	String line1 = "";
	List<Device> dlist = new ArrayList<Device>();
	Process p;
	StringBuffer buffer = new StringBuffer("output:");
	try {
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		String line=""; 
		List<String> deviceList = new ArrayList<String>();
		BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	while ((line1 = rd.readLine()) != null) {
		//System.out.println("dfjjfjfjgjj"+line1);
		buffer.append(line1 + "\n");
		
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return buffer.toString();
   }  
    
    
    @GET
    @Path("/uninstall/{device}/{file}")
    //@Produces(MediaType.APPLICATION_JSON)
    public String UnInstallApp(@PathParam("device") String device, @PathParam("file") String file){
    	//adb -s TA93304LE5 shell monkey -p com.whatsapp -c android.intent.category.LAUNCHER 1
	//String command = "adb -s "+device+" shell am start -a android.intent.action.DELETE -d "+ file;
    	String command= "adb -s "+device+" shell pm uninstall "+file;
	System.out.println("install::::"+command);
	String line1 = "";
	List<Device> dlist = new ArrayList<Device>();
	Process p;
	StringBuffer buffer = new StringBuffer("output:");
	try {
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		String line=""; 
		List<String> deviceList = new ArrayList<String>();
		BufferedReader rd = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	while ((line1 = rd.readLine()) != null) {
		//System.out.println("dfjjfjfjgjj"+line1);
		buffer.append(line1 + "\n");
		
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return buffer.toString();
   }  
    
    

  /*  @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Device getDeviceById(@PathParam("id") int id) {
        return deviceService.getById(id);
    }*/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Device create(Device device) {
        return deviceService.createNewDevice(device);
    }

  /*  @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Device update(Device device) {
        return deviceService.update(device);
    }*/
    
    @PUT
    @Path("{id}")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Device update(Device device) {
    	System.out.println("update::::::::");
    	System.out.println("device::::"+device.getDevice());
    	return device;
        //return deviceService.update(device);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") String id) {
        deviceService.remove(id);
    }
}
