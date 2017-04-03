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
		
		listarIpSemUso: function(){
			return $http.get('/rest/controleIp/ip/semUso')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});