package ngdemo.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SampleJavaTest {
	
private static Date date = new Date();	
private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss") ;
private static Process process;
private static Process adbProcess; 
private static Process javaProcess; 
private static Process deviceProcess;
private static String APPIUMSERVERSTART="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./appium.js";
private static String APPIUMSERVERSTARTUID="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./appium.js -U ";
private static String LOGS="/home/saisreem/workspace/junittest/logs/";
private static String ADBDEVICES="adb devices";
private static String JAVASCRIPT="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./javatest.sh";
private static String JAVAMSCRIPT="/home/saisreem/.npm-packages/lib/node_modules/appium/bin/./javaMtest.sh";

public static void startAppiumServer(String APPIUMSERVERSTARTUID) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    process = runtime.exec(APPIUMSERVERSTARTUID);
    Thread.sleep(5000);
    if (process != null) {
        System.out.println("Appium server started..................................");
        System.out.println(".......................................................");
    }
}

public static String getDeviceParameters(String deviceId) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    deviceProcess = runtime.exec("adb -s "+deviceId+" shell getprop ro.product.model");
    deviceProcess.waitFor();
   // Thread.sleep(5000);
    String line="",device="";
    BufferedReader input = new BufferedReader(new InputStreamReader(deviceProcess.getInputStream()));
   	while ((line = input.readLine()) != null) {
   		device=line;
   	}
   	return device;
}

public static String javaShellScript(String script) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    javaProcess = runtime.exec(script);
    javaProcess.waitFor();
    Thread.sleep(5000);
    StringBuffer buffer = new StringBuffer("");
    BufferedReader br = new BufferedReader(new InputStreamReader(javaProcess.getInputStream()));
    PrintWriter pw = new PrintWriter(javaProcess.getOutputStream());
    String line="";

    while ((line = br.readLine()) != null) {
        System.out.println(line);
        buffer.append(line + "\n");
        pw.flush();
    }
    if (javaProcess != null) {
        System.out.println("Script running..................................");
    }
    return buffer.toString();
}

public static void stopAppiumServer() throws IOException {
    if (process != null) {
        process.destroy();
    }
    System.out.println("Appium server stopped......................................");
    System.out.println("...........................................................");
}

public static List<String> getDevicesList() throws IOException, InterruptedException {
	List<String> deviceList = new ArrayList<String>();
    Runtime runtime = Runtime.getRuntime();
    adbProcess = runtime.exec(ADBDEVICES);
    String line="";
	int i=1;
	
    BufferedReader input = new BufferedReader(new InputStreamReader(adbProcess.getInputStream()));
	while ((line = input.readLine()) != null) {
	    if (line.endsWith("device")) {
	        deviceList.add(line.split("\\t")[0]);
	    } if (line.endsWith("unauthorized")) {
	        deviceList.add(line.split("\\t")[0]);
	    }
	}
	return deviceList;
}

public static void main(String args[]) throws IOException, InterruptedException {  
	if(args.length==0){
	List<String> deviceList = new ArrayList<String>();
    
    deviceList=SampleJavaTest.getDevicesList();
  /*  for(String deviceId : deviceList){
     	startAppiumServer(APPIUMSERVERSTARTUID+deviceId);
        Thread.sleep(2000);
    	System.out.println("Device Id: "+deviceId);
    	System.out.println("Device Model: "+getDeviceParameters(deviceId));
    	if(deviceId.equals("TA93304LE5")){
    	javaShellScript(JAVAMSCRIPT);
    	}
    	else{
    	javaShellScript(JAVASCRIPT);
    	}
    	stopAppiumServer();
    }*/
	}
   
}
}