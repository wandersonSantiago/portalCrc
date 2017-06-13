app.factory("Auth", function($http, $q, $sessionStorage, $rootScope, $urlRouter, UsuarioService){
	
	var auth ={};
	
	auth.init = function(){
        if (auth.isLoggedIn()){
        	$rootScope.usuario = auth.user();
            $rootScope.user = auth.currentUser();
            $rootScope.authenticated = true;
        }
    };
	
    auth.login = function(user, callback){
    	 var headers = user ? {authorization : "Basic " + btoa(user.username + ":" + user.password) } : {};
        return $q(function(resolve, reject){
            $http.get('/rest/usuario/usuario', {headers : headers})
            .then(function(response) {
      	      if (response.data) {
      	    	$sessionStorage.user = response.data;
      	        $rootScope.authenticated = true;
      	        $rootScope.user = $sessionStorage.user;
      	      } else {
      	        $rootScope.authenticated = false;
      	        delete $sessionStorage.user;
      	        delete $rootScope.user;
      	      }
      	      callback && callback();
      	      resolve();
      	    }, function() {
      	      $rootScope.authenticated = false;
      	      $rootScope.user = null;
      	      callback && callback();
      	      reject();
      	    });
            
        });
    };
    
    auth.checkPermissionForView = function(view) {
        if (!view.requiresAuthentication) {
            return true;
        }
        return userHasPermissionForView(view);
    };
     
    var userHasPermissionForView = function(view){
        if(!auth.isLoggedIn()){
            return false;
        }
         
        if(!view.permissions || !view.permissions.length){
            return true;
        }
         
        return auth.userHasPermission(view.permissions);
    };
	
	auth.userHasPermission = function(permissions){
		if(!auth.isLoggedIn()){
            return false;
        }
        for(let permission of permissions){
            if ( $rootScope.user.authorities.indexOf.name(permission) >= 0){
            	console.log($rootScope.user.authorities.indexOf.name)
            	return true;
            }    
        }
        return false;
	}
	
	auth.logout = function(){
		$http.post('/logout',{})
		.then(function(data){
			 $rootScope.authenticated = false;
			 $rootScope.user = null;
			 $rootScope.usuario = null;
			 $state.go("login");
		},function(data){
			$rootScope.authenticated = false;
			 $rootScope.user = null;
			 $rootScope.usuario = null;
		});
		delete $sessionStorage.user;
        delete $rootScope.user;
        delete $rootScope.usuario;
	}
	
	auth.currentUser = function(){
	     return $sessionStorage.user;
	};
	    
	
	auth.user = function(){
		UsuarioService.listarUsuarioLogado().
		then(function(f){
			$rootScope.usuario = f.usuario;
		});
		return $rootScope.usuario;
	}
	
    auth.isLoggedIn = function(){
        return $sessionStorage.user != null;
    };
	
	return auth;
});