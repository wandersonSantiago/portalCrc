app.factory('ModuloService', function($q, $http){
	var url = '/rest/informatica/sistema/modulo';	
	return{
		salvar: function(modulo){
			return $http.post(url + , modulo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(modulo){
			return $http.put(url + , modulo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(modulo){
			return $http.get(url +'/'+modulo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},			
		
		listar: function(){
			return $http.get(url)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		
		
	}
});