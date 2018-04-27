app.factory('FuncionarioDiariaService', function($q, $http){
	
	
	return{
		salvar: function(objeto){
			return $http.post('/rest/diaria/funcionario', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(objeto){
			return $http.put('/rest/diaria/funcionario', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		excluir: function(objeto){
			return $http.delete('/rest/diaria/funcionario/excluir/'+objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(objeto){
			return $http.get('/rest/diaria/funcionario/' +objeto)
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
			return $http.get('/rest/diaria/funcionario/secretaria/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		porUnidade: function(idDiaria){
			return $http.get('/rest/diaria/funcionario/unidade/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		porCoordenadoria: function(idDiaria){
			return $http.get('/rest/diaria/funcionario/coordenadoria/' +idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarValoresDiariaPorIndice : function(indice, idDiaria){
			return $http.get('/rest/diaria/funcionario/valores/indice/' +indice+'/diaria/'+ idDiaria)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarFuncionarioPorDiariaPorId: function(texto, idDiaria, pagina){
			var config = {params: {page: pagina, q:texto, idDiaria : idDiaria}};
			return $http.get('/rest/diaria/funcionario/unidade', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		cidades: function(objeto){
			return $http.get('/rest/endereco/cidade/estado/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		estados: function(objeto){
			return $http.get('/rest/endereco/estado/pais/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorTexto : function(params){
			return $http.get('/rest/diaria/funcionario/buscar?q=' +params)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		buscarPorDiaria : function(texto, pagina){
			var config = {params: {page: pagina, q:texto}};
			return $http.get('/rest/diaria/funcionario/buscar/diaria', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
	}
});