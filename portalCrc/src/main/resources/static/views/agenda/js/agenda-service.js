app.factory('AgendaService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(agenda){
			return $http.post('/rest/agenda/salvar', agenda)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(agenda){
			return $http.put('/rest/agenda/alterar', agenda)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(agenda){
			return $http.get('/rest/agenda/buscaPorId/'+agenda)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/agenda/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});