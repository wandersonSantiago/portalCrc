app.factory('IpService', function($q, $http){
	
	
	return{
		salvar: function(ip){
			return $http.post('/rest/controleIp/ip', ip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(ip){
			return $http.put('/rest/controleIp/ip', ip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(ip){
			return $http.get('/rest/controleIp/ip/buscaPorId/'+ip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/controleIp/ip')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listarIpInativo: function(){
			return $http.get('/rest/controleIp/ip/inativo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarIpAtivo: function(){
			return $http.get('/rest/controleIp/ip/ativo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});