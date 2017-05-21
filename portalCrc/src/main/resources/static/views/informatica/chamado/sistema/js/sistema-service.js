app.factory('SistemaService', function($q, $http){
	var url = "/rest/chamado/sistema/";
	
	return{
		salvar: function(sistema){
			return $http.post(url, sistema)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(sistema){
			return $http.put(url , sistema)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(sistema){
			return $http.get(url +'/id'+sistema)
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