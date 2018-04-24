app.factory("Auth", function($http, $q, $sessionStorage, $rootScope, $urlRouter, UsuarioService){
	
	var auth ={};
	
	auth.init = function(){
        if (auth.isLoggedIn()){
        	$rootScope.loginIncorreto = false;
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
      	        $rootScope.loginIncorreto = false;
      	        $rootScope.user = $sessionStorage.user;
      	      } else {
      	        $rootScope.authenticated = false;
      	        $rootScope.loginIncorreto = true;
      	        delete $sessionStorage.user;
      	        delete $rootScope.user;
      	      }
      	      callback && callback();
      	      resolve();
      	    }, function() {
      	      $rootScope.authenticated = false;
      	      $rootScope.loginIncorreto = true;
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
        	$rootScope.permissao = [];
        	for(i = 0 ; i < $rootScope.user.permissoes.length ; i++){
        		$rootScope.permissao[i] = $rootScope.user.permissoes[i].descricao.replace(/.+\?/g, "");
        	}
            if ( $rootScope.permissao.indexOf(permission) >= 0){
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
			 $state.go("login");
		},function(data){
			$rootScope.authenticated = false;
			 $rootScope.user = null;
		});
		delete $sessionStorage.user;
        delete $rootScope.user;
	}
	
	auth.currentUser = function(){
	     return $sessionStorage.user;
	};
	    
	
    auth.isLoggedIn = function(){
        return $sessionStorage.user != null;
    };
	
	return auth;
});