app.factory('TipoIpService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(tipoip){
			return $http.post('/rest/controleIp/tipoIp', tipoip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(tipoip){
			return $http.put('/rest/controleIp/tipoIp/', tipoip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(tipoip){
			return $http.get('/rest/controleIp/tipoIp/buscaPorId/'+tipoip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/controleIp/tipoIp')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});