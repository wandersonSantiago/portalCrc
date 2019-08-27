app.factory('TipoService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(tipoUnidade){
			return $http.post('/rest/unidade/tipoUnidade/salvar', tipoUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(tipoUnidade){
			return $http.put('/rest/unidade/tipoUnidade/alterar', tipoUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(tipoUnidade){
			return $http.get('/rest/unidade/tipoUnidade/buscaPorId/'+tipoUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/unidade/tipoUnidade/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		
		
	}
});