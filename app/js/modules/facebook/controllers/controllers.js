(function(){
	
	'use strict';
	
	angular.module('ranguei.facebook.controllers', ['facebook']);
	
	angular.module('ranguei.facebook.controllers').config(['FacebookProvider',function(FacebookProvider) {
		FacebookProvider.init('-------------');
	}]);
	
	angular.module('ranguei.facebook.controllers')
		.controller('LoginController', ['$rootScope', '$scope', '$timeout', 'Facebook', function($rootScope, $scope, $timeout, Facebook){
		
			$rootScope.user = {};
			$rootScope.logged = false;
			
			Facebook.getLoginStatus(function(response) {
				if (response.status == 'connected') {
					$rootScope.logged = true;
				}
			});
		    
			$scope.IntentLogin = function() {
				if(!$rootScope.logged) {
					$scope.login();
				}
			};
		    
			$scope.login = function() {
				Facebook.login(function(response) {
					if (response.status == 'connected') {
						$rootScope.logged = true;
		            	$scope.me();
					}
				});
			};
		    
			$scope.me = function() {
				Facebook.api('/me', function(response) {
					$scope.$apply(function() {
						$rootScope.user = response;
					});
				});
			};
		    
			$scope.logout = function() {
				Facebook.logout(function() {
					$scope.$apply(function() {
						$rootScope.user   = {};
						$rootScope.logged = false;  
					});
				});
			};
		    
			$scope.$on('Facebook:statusChange', function(ev, data) {
				console.log(data);
				if (data.status == 'connected') {
					$scope.$apply(function() {  
						$rootScope.logged = true;
					});
				} else {
					$scope.$apply(function() {
						$rootScope.logged = false;
					});
				}
			});
	}]);
	
})();