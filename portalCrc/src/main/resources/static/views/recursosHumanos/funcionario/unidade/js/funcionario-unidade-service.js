app.factory('FuncionarioUnidadeService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(funcionarioUnidade){
			return $http.post('/rest/recursosHumanos/funcionarioUnidade/salvar', funcionarioUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(funcionarioUnidade){
			return $http.put('/rest/recursosHumanos/funcionarioUnidade/alterar', funcionarioUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(param){
			return $http.get('/rest/recursosHumanos/funcionarioUnidade/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/recursosHumanos/funcionarioUnidade/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},

		listarStatus: function(){
			return $http.get('/rest/recursosHumanos/funcionarioUnidade/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});