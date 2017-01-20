app.factory('tipoIpService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(tipoip){
			return $http.post('/rest/controleIp/tipoIp', tipoip)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		altera: function(tipoip){
			return $http.put('/rest/controleIp/tipoIp/', tipoip)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(tipoip){
			return $http.get('/rest/controleIp/tipoIp/buscaPorId/'+tipoip)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		
		lista: function(){
			return $http.get('/rest/controleIp/tipoIp')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
	}
});