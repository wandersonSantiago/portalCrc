app.factory('TemaService', function($q, $http){
	var url = '/rest/informatica/sistema/modulo/tema'
	
	return{
		salvar: function(tema){
			return $http.post(url + , tema)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(tema){
			return $http.put(url +, tema)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(tema){
			return $http.get(url +'/'+ tema)
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