app.factory('CoordenadoriaService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(coordenadoria){
			return $http.post('/rest/coordenadoria/salvar', coordenadoria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(coordenadoria){
			return $http.put('/rest/coordenadoria/alterar', coordenadoria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(coordenadoria){
			return $http.get('/rest/coordenadoria/buscaPorId/'+coordenadoria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/coordenadoria/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});