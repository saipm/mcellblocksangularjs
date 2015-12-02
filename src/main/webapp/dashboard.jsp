 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
        pageEncoding="ISO-8859-1" import="java.util.*,javax.sql.*,java.sql.*,java.awt.*,java.io.*"%> 
<html lang="en">
<head>
<style>
body{
color:"white";
}
</style>
 <style>
    .moreinfo {
    	  display: inline-block;
    	} 
    	TD{font-family: Arial;
    	 font-size: 11pt;}
    </style>
    <!-- <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script> -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script type="text/javascript">
    $( document ).ready(function() {
    $.getJSON('http://localhost:8082/ngdemo/web/devicetests/devicecount/', function(data) {
    	var out=data;
    document.getElementById("devicecount").innerHTML="  "+out;
    });
    
    $.getJSON('http://localhost:8082/ngdemo/web/devicetests/testcount/', function(data) {
    	var out=data;
    document.getElementById("testcount").innerHTML="  "+out;
    });
    
    $.getJSON('http://localhost:8082/ngdemo/web/devicetests/errorcount/', function(data) {
    	var out=data;
    document.getElementById("errorcount").innerHTML="  "+out;
    });
    
    });
    </script>
<style type="text/css" media="screen">
            #carbonads {
              padding: 20px;
            }

            #carbonads .carbon-img {
              clear: both;
              float: left;
              background: #fff;
              padding: 10px;
            }

            #carbonads .carbon-img img {
              display: block;
            }

            #carbonads .carbon-text {
              clear: both;
              display: block;
              padding: 10px 0;
            }

            #carbonads .carbon-poweredby {
              display: block;
              color: #ADB0BA;
              font-size: .9em;
            }
          </style>

    <title> MCELLBLOCKS</title>
    <!--REQUIRED STYLE SHEETS-->
    <!-- BOOTSTRAP CORE STYLE CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLE CSS -->
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" />
   <!--  ANIMATED FONTAWESOME STYLE CSS -->
    <!-- <link href="assets/css/font-awesome-animation.css" rel="stylesheet" />  -->
     <link href="css/session.css" rel="stylesheet" /> 
    <link href="css/elusive-webfont.css" rel="stylesheet" />
    <link href="css/pages.css" rel="stylesheet" />
    <link href="css/sunburst.css" rel="stylesheet" />
       <!-- CUSTOM STYLE CSS -->
    <!-- <link href="assets/css/style.css" rel="stylesheet" /> -->
    <!-- GOOGLE FONT -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>
<body>

    
      <div class="contentWrap">
        <div id="content">
          <header>
          <h1 style="margin-left:-20px;margin-top:-30px;"><img  src="img/bootstrap/mobile-integration-workgroup-squarelogo.png" border="0" alt="MIW" style="margin-bottom:'0px';height:80px;width:'80px;'"/></h1>
            <ul class="commonNav" id="main-nav">
            
            <li><a href="index.html#/testcase-list">Test cases</a></li>
            
              <li><a href="#/device-list">Editor</a></li>
              
              
                <li class="selected"><a href="/user/dashboard/">All your fiddles</a></li>
                <li><a href="index.html">Devices</a></li>
                
                
              
            </ul> <!-- /commonNav -->
            <span id="shadow"></span>
          </header>
          <div id="body">
 <div class="blankslate">
	  <!-- 
      <h2>You don't have any Fiddles yet.</h2>
      <p>You will find all your fiddles here after you will <a title="Create a new Fiddle" href="/">create</a> or fork them.</p>
       -->
      <div class="moreinfo" style="margin-left:40px;">
      <button style="width:85px;height:45px;background-color:#D050A5;border:#D050A5;" id="devicecount" class=" btn glyphicon glyphicon-phone btn-danger"></button>
      </br></br><span style="margin-left:10px;font-family='Tahoma'"><font size="2.5px;" >DEVICES</font></span>
      </br><span style="margin-left:5px;font-family='Tahoma'"><font size="1.5px;" >WERE TESTED.</font></span>
      </div>
        <div style="margin-left:90px;" class="moreinfo">
      <button style="width:85px;height:45px;background-color: #c5ffb3; border:#c5ffb3;" id="testcount" class=" btn glyphicon glyphicon-credit-card btn-danger"></button>
      </br></br><span style="margin-left:10px;font-family='Tahoma'"><font size="2.5px;" >TESTS</font></span>
      </br><span style="margin-left:5px;font-family='Tahoma'"><font size="1.5px;" >WERE RUN.</font></span>
      </div>
          <div style="margin-left:90px;" class="moreinfo">
      <button style="width:85px;height:45px;background-color: #ffb166; border:#ffb166;" id="errorcount" class=" btn glyphicon glyphicon-ban-circle btn-danger"></button>
      </br></br><span style="margin-left:10px;font-family='Tahoma'"><font size="2.5px;" >TESTS</font></span>
      </br><span style="margin-left:5px;font-family='Tahoma'"><font size="1.5px;" >WERE FAILED.</font></span>
      </div>
    </div> <!-- /blankslate -->
      </div> <!-- /body -->
        </div> <!-- /content -->
      </div> <!-- /contentWrap -->

      <div class="sidebarWrap">
        <div id="sidebar">
          <ul class="commonNav" id="side-nav">
            
              <li><a href="/user/logout/">Sign out</a></li>
              <li><a href="/user/dashboard/edit/">Edit your profile</a></li>
            
          </ul> <!-- /commonNav -->
          
  
<div class="segment">
  <div class="face">
    <a href="/user/dashboard/"><img src="http://www.gravatar.com/avatar/036785b6c345a739ed57578650624979/?default=&amp;s=160" height="80" width="80" /></a>
    <h3></h3>
    <p> <%=session.getAttribute("name")%></p>
  </div> <!-- /face -->
  
    <ul class="userDetails">
   </ul>
  
</div>
       
        </div>
      </div> 

    <script src="assets/plugins/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP CORE SCRIPT   -->
    <script src="assets/plugins/bootstrap.js"></script>
  
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
<!-- JQuery ================================================================ -->
<script src="js/jquery/jquery-2.0.3.js"></script>

<!-- Bootstrap ============================================================= -->
<script src="js/bootstrap/bootstrap.js"></script>

<script src="lib/angular/angular.js"></script>
<script src="lib/angular/angular-resource.js"></script>

<!-- AngularJS App Code ==================================================== -->
<script src="js/app.js"></script>
<script src="js/services.js"></script>
<script src="js/controllers.js"></script>
<script src="js/filters.js"></script>
<script src="js/directives.js"></script>

</body>
</html>