app.factory('VeiculoService', function($rootScope, toastr, $http, $q ){
	
	
	return{
		salvar: function(veiculo){
			return $http.post('/rest/infra/veiculo', veiculo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(veiculo){
			return $http.put('/rest/infra/veiculo', veiculo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(veiculo){
			return $http.get('/rest/infra/veiculo/buscaPorId/'+veiculo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		buscarPorUnidade: function(idUnidade){
			return $http.get('/rest/infra/veiculo/unidade/'+idUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/infra/veiculo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		marcas: function(){
			return $http.get('/rest/infra/veiculo/marcas')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		modelos: function(idMarca){
			return $http.get('/rest/infra/veiculo/modelos/marca/' + idMarca)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		cores: function(){
			return $http.get('/rest/infra/veiculo/cor')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
	}
});