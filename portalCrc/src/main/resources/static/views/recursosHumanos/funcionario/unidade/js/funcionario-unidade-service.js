app.factory('FuncionarioUnidadeService', function($q, $rootScope, toastr, $http){
	
	
	return{
		salvar: function(funcionarioUnidade){
			return $http.post('/rest/recursosHumanos/funcionarioUnidade/salvar', funcionarioUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		update: function(funcionarioUnidade){
			return $http.put('/rest/recursosHumanos/funcionarioUnidade/alterar', funcionarioUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterarUnidade : function(idUnidade){
			return $http.put('/rest/recursosHumanos/funcionarioUnidade/alterar/unidade/'+idUnidade)
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
		buscarPorIdFuncionario: function(param){
			return $http.get('/rest/recursosHumanos/funcionarioUnidade/funcionario/'+param)
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
		
		findAll: function(id){
			return $http.get('/rest/recursosHumanos/funcionarioUnidade/'+id+'/lista')
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
		buscarPorTexto :function(params){
			return $http.get('/rest/recursosHumanos/funcionarioUnidade/buscar?q=' +params)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
	}
});