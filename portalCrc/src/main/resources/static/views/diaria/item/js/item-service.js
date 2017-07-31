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
		
		porSecretaria: function(idDiaria){
			return $http.get('/rest/diaria/item/secretaria/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		porUnidades: function(idDiaria){
			return $http.get('/rest/diaria/item/unidade/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		porCoordenadoria: function(idDiaria){
			return $http.get('/rest/diaria/item/coordenadoria/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarValoresDiaria : function(objeto){
			return $http.get('/rest/diaria/funcionario/valores' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarItensDiariaPorFuncionarioDiaria: function(idFuncionario){
			return $http.get('/rest/diaria/item/itens/'+idFuncionario)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		itensPorFuncionarioDiariaETipo: function(idFuncionario, tipo){
			return $http.get('/rest/diaria/item/itens/'+idFuncionario+"/tipo/"+tipo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarPorIdFuncionario: function(idDiaria , idFuncionario){
			return $http.get('/rest/diaria/funcionario/'+idFuncionario+'/diaria/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarPorIdFuncionarioPorConta: function(idDiaria , idFuncionario){
			return $http.get('/rest/diaria/funcionario/conta/'+idFuncionario+'/diaria/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		analizado: function(idItem){
			return $http.post('/rest/diaria/item/'+idItem+'/analizado')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		tipos: function(){
			return $http.get('/rest/diaria/item/tipos')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
	}
});