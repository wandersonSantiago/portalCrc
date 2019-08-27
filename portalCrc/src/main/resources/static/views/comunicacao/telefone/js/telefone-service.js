app.factory('TelefoneService', function($rootScope, toastr, $http, $q){
	
	
	return{
		salvar: function(listaTelefone){
			return $http.post('/rest/telefone/salvar', listaTelefone)
			.then(function(response){
				return response.data;
			},function(errResponse){
				
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(telefone){
			return $http.put('/rest/telefone/alterar', telefone)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(telefone){
			return $http.get('/rest/telefone/buscaPorId/'+telefone)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		buscarTelefonePorUnidade: function(telefone){
			return $http.get('/rest/telefone/buscarTelefonePorUnidade/'+telefone)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/telefone/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});