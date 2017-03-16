app.factory('PortaSwitchService', function($q, $http){
	
	
	return{
		salvar: function(Switch){
			return $http.post('/rest/controleIp/switch/porta', Switch)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(Switch){
			return $http.put('/rest/controleIp/switch/porta', Switch)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(param){
			return $http.get('/rest/controleIp/switch/porta/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/controleIp/switch/porta')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
	}
});