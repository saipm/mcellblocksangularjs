'use strict';

/* Controllers */

var app = angular.module('ngdemo.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


app.controller('DummyCtrl', ['$scope', 'DummyFactory', function ($scope, DummyFactory) {
    $scope.bla = 'bla from controller';
    DummyFactory.get({}, function (dummyFactory) {
        $scope.firstname = dummyFactory.firstName;
    })
}]);

app.controller('UserListCtrl', ['$scope', 'UsersFactory', 'UserFactory', '$location',
    function ($scope, UsersFactory, UserFactory, $location) {

        // callback for ng-click 'editUser':
        $scope.editUser = function (userId) {
            $location.path('/user-detail/' + userId);
        };

        // callback for ng-click 'deleteUser':
        $scope.deleteUser = function (userId) {
            UserFactory.delete({ id: userId });
            $scope.users = UsersFactory.query();
        };

        // callback for ng-click 'createUser':
        $scope.createNewUser = function () {
            $location.path('/user-creation');
        };

        $scope.users = UsersFactory.query();
    }]);


app.controller('DeviceListCtrl', ['$scope','$http', 'DevicesFactory', 'DeviceFactory', '$location',
        function ($scope,$http, DevicesFactory, DeviceFactory, $location) {
	  $scope.apps='';
	  $scope.testInput ='';
	  $scope.username='';
	   var i=0;
	  $scope.init = function(value) {
		 // alert("value:::"+value);
		  $scope.devval=value;
/*		$scope.apps=  $http.get("http://localhost:8080/ngdemo/web/devices/get").
		 success(function(data) {
			// alert("inside");
		     $scope.apps=data;   
		    // $scope.devices = DevicesFactory.query();
		        return $scope.apps;
		    }).error(function (data, status, headers, config) {
		        alert("error");
		     
	});*/
	  };
	  
	  $scope.byStatusId = function(app) {
		 // alert("aoo::"+app);
		 // alert("inside");
		 // alert("jhdfhjf"+app.device);
		        return $scope.devval == app.device;
		   };

	  $scope.selectdata=function(){
		  $scope.testInput=testInput;
		// alert("inside function");
		  $http.get("http://localhost:8082/ngdemo/web/devices/get").
			 success(function(data) {
				 //alert("inside");
			     $scope.apps=data;   
			    // $scope.devices = DevicesFactory.query();
			        return $scope.apps;
			    }).error(function (data, status, headers, config) {
			        alert("error");
			     
		});
		 
	  };
	  $scope.options = $scope.apps;

	                       $scope.genName = function(device,app) {
	                           return device + ' ' + app;
	                       }
	  
	  
	  $scope.dumpbattery=function(testInput){
		  $scope.testInput=testInput;
		// alert("inside function");
		  $http.get("http://localhost:8082/ngdemo/web/devices/battery/"+$scope.testInput).
			 success(function(data) {
				 //alert("i"+i);
			     $scope.battery=data;   
			     alert("jdhdfhfhhhg"+$scope.battery);
			        return $scope.battery;
			    }).error(function (data, status, headers, config) {
			        alert("error");
			     
		});
	  };
	  
	 $scope.showdetails= function(username)
	   {
		  // alert("username"+username);
		   $scope.username=username;
	      window.open('partials/device-info.html?username='+$scope.username,'_blank');
	   }
	 $scope.showapps= function(username)
	   {
		  // alert("username"+username);
		   $scope.devname=username;
	      mywindow= window.open('partials/App-List.html?username='+$scope.devname, "mywindow","menubar=1,resizable=1,width=600,height=300");
	      mywindow.moveTo(750,500);
	   }	  
	  
	$scope.installapk=function(device,file){
		
		//alert("device"+device);
		//alert("file"+file);
		$scope.device=device;
		$scope.file=file;
	
		$http.get("http://localhost:8080/ngdemo/web/devices/install/"+$scope.device+ "/"+$scope.file).
			 success(function(data) {
				 //alert("success");
			     console.log(data);
			     alert(data);
			        return data;
			    }).error(function (data, status, headers, config) {
			        alert("error");
			       // return status;
		});
	}
	
	$scope.uploadFiles=function(device,file){
		alert("device"+device);
		alert("file"+file);
		//var mystring = "this,is,a,test"  
			var newchar = 'aaa';
			file = file.split('/').join(newchar);
			//alert("file::"+file);
		$scope.device=device;
		$scope.file=file;
		$http.get("http://localhost:8080/ngdemo/web/devices/upload/"+$scope.device+ "/"+$scope.file).
			 success(function(data) {
				 alert("success");
			     console.log(data);
			     //alert(data);
			        return data;
			    }).error(function (data, status, headers, config) {
			        alert("error");
			       // return status;
		});
	}
            // callback for ng-click 'editUser':
            $scope.editDevice = function (userId) {
                $location.path('/user-detail/' + userId);
            };
            
            $scope.customNavigate=function(device){
                window.location.assign("partials/device-info.html");
             };
     
            $scope.updateDevice = function (device) {
            	$scope.device=device;
                DeviceFactory.update($scope.device);
                //$location.path('/device-list');
            };

            // callback for ng-click 'deleteUser':
            $scope.deleteUser = function (userId) {
                UserFactory.delete({ id: userId });
                $scope.users = UsersFactory.query();
            };

            // callback for ng-click 'createUser':
            $scope.createNewUser = function () {
                $location.path('/user-creation');
            };
         //  $scope.apps=$scope.selectdata();
            $scope.devices = DevicesFactory.query();
        }]);

app.controller('TestcaseListCtrl', ['$scope','$http', 'TestcasesFactory', 'TestcaseFactory','TestDeviceFactory', '$location',
          function ($scope,$http, TestcasesFactory, TestcaseFactory,TestDeviceFactory, $location) {
	
	
  	  $scope.apps='';
  	  $scope.testInput ='';
  	  $scope.username='';
  	 $scope.custom = true;	
  	 $scope.customdiv=true;
  	 $scope.devices = TestDeviceFactory.query();
  	//$scope.loader.loading = false;
  	 
  	$scope.loader = {
  			 loading: false,
  			 };
  	$scope.status = {
 			 loading: false,
 			 };
  	
/*
    $scope.loader = {
      loading: false,
    };
    
    $scope.showloader = function(){
        $scope.loader.loading = true ;
    }
    $scope.hideloader = function(){
        $scope.loader.loading = false ;
    }
  			 
  			$scope.someFunction = function( )
  			{
  			 
  			 $scope.loader.loading = true ;
  			 
  			 var _url = 'http://localhost:8082/ngdemo/web/testcases/getatestcase/1a5dacc6';
  			 
  			 var data = {
  			   name: name
  			 };
  			 
  			 $http.post( _url, angular.toJson(data), {cache: false} )
  			  .success( function( data )
  			  {
  			   
  			   console.log(data);
  			   
  			   $scope.loader.loading = false ;
  			  
  			  })
  			  .error(function(data){
  			   
  			   error(data);
  			 
  			 });
  			 
  			};*/
  	 
  	 $scope.test=function(){
  		 $scope.loader.loading = true ;
  	    $http.get("http://localhost:8082/ngdemo/web/testcases/getalltestcases").
			 success(function(data) {
			     console.log(data);
			   $scope.devices=data;
			   //alert("status:"+data.status);
			   $scope.status.loading = true ;
			   $scope.loader.loading = false ;
			   return $scope.devices;
			    }).error(function (data, status, headers, config) {
			        alert("error");
		});
  	 };
  	 
  	$scope.runatestcase=function(device){
  			 alert(device.device);
  		     $http.get("http://localhost:8082/ngdemo/web/testcases/getatestcase/"+device.device).
  			 success(function(data) {
  			     console.log(data);
  			   //  alert(data);
  			     $scope.custom=false;
  			   location.reload(); 
  			     //   return data;
  			    }).error(function (data, status, headers, config) {
  			        alert("error");
  		});
  		
  	};
              $scope.testcases = TestcasesFactory.query();
  	}]);

app.controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserFactory', '$location',
    function ($scope, $routeParams, UserFactory, $location) {

        // callback for ng-click 'updateUser':
        $scope.updateUser = function () {
            UserFactory.update($scope.user);
            $location.path('/user-list');
        };

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/user-list');
        };

        $scope.user = UserFactory.show({id: $routeParams.id});
    }]);

app.controller('UserCreationCtrl', ['$scope', 'UsersFactory', '$location',
    function ($scope, UsersFactory, $location) {

        // callback for ng-click 'createNewUser':
        $scope.createNewUser = function () {
            UsersFactory.create($scope.user);
            $location.path('/user-list');
        }
    }]);

