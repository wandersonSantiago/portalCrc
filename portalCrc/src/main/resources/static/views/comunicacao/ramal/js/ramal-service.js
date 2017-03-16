app.factory('RamalService', function($rootScope, toastr, $http ,$q){
	
	
	return{
		salvar: function(ramal){
			return $http.post('/rest/ramal/salvar', ramal)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(ramal){
			return $http.put('/rest/ramal/alterar', ramal)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(ramal){
			return $http.get('/rest/ramal/buscaPorId/'+ramal)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		buscarRamalPorUnidade: function(ramal){
			return $http.get('/rest/ramal/buscarRamalPorUnidade/'+ramal)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/ramal/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});