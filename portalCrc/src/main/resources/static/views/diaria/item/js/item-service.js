app.factory('ItemDiariaService', function($q, $http){
	
	
	return{
		salvar: function(objeto){
			return $http.post('/rest/diaria/item', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(objeto){
			return $http.put('/rest/diaria/item', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		excluir: function(objeto){
			return $http.delete('/rest/diaria/item/excluir/'+objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(objeto){
			return $http.get('/rest/diaria/item/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		buscarDiariaPorId: function(objeto){
			return $http.get('/rest/diaria/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},	
		
		listarSecretaria: function(objeto){
			return $http.get('/rest/diaria/item/listaSecretaria/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarUnidade: function(objeto){
			return $http.get('/rest/diaria/item/listaUnidade/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarCoordenadoria: function(objeto){
			console.log(objeto);
			return $http.get('/rest/diaria/item/listaCoordenadoria/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});