package ngdemo.web.rest;

import com.google.inject.Inject;

import ngdemo.domain.App;
import ngdemo.domain.Data;
import ngdemo.domain.Device;
import ngdemo.domain.Status;
import ngdemo.domain.Testcase;
import ngdemo.service.contract.TestcaseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/testcases")
public class TestcaseRestService {

    private final TestcaseService testcaseService;
    private static String APPIUMSERVERSTART="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./appium.js";
    private static String APPIUMSERVERSTARTUID="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./appium.js -U ";
    private static String LOGS="/home/saisreem/workspace/junittest/logs/";
    private static String ADBDEVICES="adb devices";
    private static String JAVASCRIPT="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./javatest.sh";
    private static String JAVAMSCRIPT="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./javaMtest.sh";


    @Inject
    public TestcaseRestService(TestcaseService testcaseService) {
        this.testcaseService = testcaseService;
    }
    @GET
    @Path("/runalldevices")
    @Produces(MediaType.APPLICATION_JSON)
    public String runalldevices() throws IOException, InterruptedException{
    	String[] args1 = {};
    	 StringBuffer buffer = new StringBuffer("output:");
    	 String line1=" ";
    	 BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    	SampleJavaTest.main(args1);
    	while ((line1 = rd.readLine()) != null) {
    		System.out.println(line1);
    		buffer.append(line1 + "\n");
    		}
    	return buffer.toString();
   }  
    @GET
    @Path("/runatestcase/{device}")
    @Produces(MediaType.APPLICATION_JSON)
    public String runatestcase(@PathParam("device") String device) throws IOException, InterruptedException{
    	SampleJavaTest.startAppiumServer(APPIUMSERVERSTARTUID+device);
    	StringBuffer buffer = new StringBuffer("");
    	String test="";
    	String status="";
    	String errormsg=" ";
        Thread.sleep(2000);
    	System.out.println("Device Id: "+device);
    	System.out.println("Device Model: "+SampleJavaTest.getDeviceParameters(device));
    	TestcaseDao td = new TestcaseDao();
    	if(device.equals("TA93304LE5")){
    	test=SampleJavaTest.javaShellScript(JAVAMSCRIPT);
    	}
    	else{
        test=SampleJavaTest.javaShellScript(JAVASCRIPT);
    	}
    	SampleJavaTest.stopAppiumServer();
    	String buffstring = buffer.toString();
    	buffer.append("device:"+device+"\n");
    	buffer.append("device model:"+SampleJavaTest.getDeviceParameters(device)+"\n");
    			buffer.append(test+"\n");
    		if(buffer.indexOf("PASS")>0){
    			status="Pass";
    		}if(buffer.indexOf("FAIL")>0){
    			status="Fail";
    			errormsg="t"+(buffer.toString().substring(buffer.toString().lastIndexOf("test(")+1));	
        		System.out.println("error message:"+errormsg);
    		}
    		
    		 
    	td.insertTestcase("saipm", device, "Trillian Instant Messaging", status, errormsg);
    	return buffer.toString();
   } 
    
    @GET
    @Path("/getalltestcases")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> getalltestcases() throws IOException, InterruptedException{
    	String command="adb devices";
    	List<String> deviceList = new ArrayList<String>();
    	List<Status> statuslist = new ArrayList<Status>();
    	Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			String line="";
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
			    if (line.endsWith("device")) {
			        deviceList.add(line.split("\\t")[0]);
			    } if (line.endsWith("unauthorized")) {
			        deviceList.add(line.split("\\t")[0]);
			    }
			}
			
		for(String device: deviceList){
			Status t = new Status();
			t=getatestcase(device);
			statuslist.add(t);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuslist;
    }
    
    
    @GET
    @Path("/getatestcase/{device}")
    @Produces(MediaType.APPLICATION_JSON)
    public Status getatestcase(@PathParam("device") String device) throws IOException, InterruptedException{
    	SampleJavaTest.startAppiumServer(APPIUMSERVERSTARTUID+device);
    	StringBuffer buffer = new StringBuffer("");
    	String test="";
    	String status="";
    	String errormsg=" ";
        Thread.sleep(2000);
    	System.out.println("Device Id: "+device);
    	System.out.println("Device Model: "+SampleJavaTest.getDeviceParameters(device));
    	TestcaseDao td = new TestcaseDao();
    	if(device.equals("TA93304LE5")){
    	test=SampleJavaTest.javaShellScript(JAVAMSCRIPT);
    	}
    	else{
        test=SampleJavaTest.javaShellScript(JAVASCRIPT);
    	}
    	SampleJavaTest.stopAppiumServer();
    	String buffstring = buffer.toString();
    	buffer.append("device:"+device+"\n");
    	buffer.append("device model:"+SampleJavaTest.getDeviceParameters(device)+"\n");
    			buffer.append(test+"\n");
    		if(buffer.indexOf("PASS")>0){
    			status="Pass";
    		}if(buffer.indexOf("FAIL")>0){
    			status="Fail";
    			errormsg="t"+(buffer.toString().substring(buffer.toString().lastIndexOf("test(")+1));	
        		//System.out.println("error message:"+errormsg);
    		}
    		Status st = new Status();
    		st.setDevice(device);
    		st.setStatus(status);
    		st.setUserid("saipm");
    		st.setTestcase("Trillian Instant Messaging");
    		if(errormsg.length()>200)
    		st.setErrormsg(errormsg.substring(0, 200));
    		else
    			st.setErrormsg(errormsg);
    		 
    	td.insertTestcase("saipm", device, "Trillian Instant Messaging", status, errormsg);
    	//return buffer.toString();
    		return st;
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
    public List<Testcase> getAlltestsInJSON() throws SQLException {
    	
    	System.out.println("Test cases");
    	String command = "adb devices";
		List<Testcase> dlist = new ArrayList<Testcase>();
		org.codehaus.jettison.json.JSONObject finalObject = new org.codehaus.jettison.json.JSONObject();
		dlist=TestcaseDao.getTestcases();
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
    
    
    
/*    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getAppsInJSON(@PathParam("device") String device)) {
    	
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
    */
    
   
    
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
    public Testcase create(Testcase testcase) {
        return testcaseService.createNewTestcase(testcase);
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
        testcaseService.remove(id);
    }
}
