app.factory('unidadeService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(unidade){
			return $http.post('/rest/unidade/salvar', unidade)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		altera: function(unidade){
			return $http.put('/rest/unidade/alterar', unidade)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(unidade){
			return $http.get('/rest/unidade/buscaPorId/'+unidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},	
		
		buscarUnidadePorCoordenadoria: function(id){
			return $http.get('/rest/unidade/buscarUnidadePorCoordenadoria/'+id)
			.then(function(response){
				return response.data;
				console.log(response.data);
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		lista: function(){
			return $http.get('/rest/unidade/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		listaTipoUnidade: function(){
			return $http.get('rest/unidade/tipoUnidade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
			});
		},
		
		
	}
});