app.factory('FuncionarioService', function($q , $http){
	
	
	return{
		salvar: function(funcionario){
			return $http.post('/rest/recursosHumanos/funcionario/salvar', funcionario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(funcionario){
			return $http.put('/rest/recursosHumanos/funcionario/alterar', funcionario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(param){
			return $http.get('/rest/recursosHumanos/funcionario/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/recursosHumanos/funcionario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarPorUnidade: function(){
			return $http.get('/rest/recursosHumanos/funcionario/lista/porUnidade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		estadoCivil: function(){
			return $http.get('/rest/pessoa/estadoCivil')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorTexto :function(params){
			return $http.get('/rest/recursosHumanos/funcionario/buscar?q=' +params)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
	}
});