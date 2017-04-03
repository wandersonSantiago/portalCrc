app.factory('PontoService', function($q, $http){
	
	
	return{
		salvar: function(Ponto){
			return $http.post('/rest/controleIp/ponto', Ponto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(Ponto){
			return $http.put('/rest/controleIp/ponto', Ponto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(param){
			return $http.get('/rest/controleIp/ponto/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/controleIp/ponto')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
	}
});