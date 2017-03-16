app.factory('UnidadeService', function($q, $http){
	
	
	return{
		salvar: function(unidade){
			return $http.post('/rest/unidade/salvar', unidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(unidade){
			return $http.put('/rest/unidade/alterar', unidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(unidade){
			return $http.get('/rest/unidade/buscaPorId/'+unidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},	
		
		buscarUnidadePorCoordenadoria: function(id){
			return $http.get('/rest/unidade/buscarUnidadePorCoordenadoria/'+id)
			.then(function(response){
				return response.data;
				console.log(response.data);
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listar: function(){
			return $http.get('/rest/unidade/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listarTipoUnidade: function(){
			return $http.get('/rest/unidade/tipoUnidade/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});