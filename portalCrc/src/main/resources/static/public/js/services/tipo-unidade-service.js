app.factory('tipoUnidadeService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(tipoUnidade){
			return $http.post('/rest/unidade/tipoUnidade/salvar', tipoUnidade)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		altera: function(tipoUnidade){
			return $http.put('/rest/unidade/tipoUnidade/alterar', tipoUnidade)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(tipoUnidade){
			return $http.get('/rest/unidade/tipoUnidade/buscaPorId/'+tipoUnidade)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		
		lista: function(){
			return $http.get('/rest/unidade/tipoUnidade/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		
		
	}
});