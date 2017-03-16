app.factory('SecretariaService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(secretaria){
			return $http.post('/rest/secretaria/salvar', secretaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(secretaria){
			return $http.put('/rest/secretaria/alterar', secretaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(secretaria){
			return $http.get('/rest/secretaria/buscaPorId/'+secretaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/secretaria/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});