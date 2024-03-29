app.factory('DiariaService', function($q, $http){
	
	
	return{
		salvar: function(objeto){
			return $http.post('/rest/diaria', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		encerrar: function(objeto){
			return $http.put('/rest/diaria/encerrar', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(objeto){
			return $http.put('/rest/diaria', objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(objeto){
			return $http.get('/rest/diaria/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		diariasEmAberto: function(){
			return $http.get('/rest/diaria/diariasEmAberto')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listar: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/diaria/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarMes: function(){
			return $http.get('/rest/diaria/mes')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		unidade: function(){
			return $http.get('/rest/diaria/unidade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarUnidade: function(){
			return $http.get('/rest/diaria/listaUnidade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listarCoordenadoria: function(){
			return $http.get('/rest/diaria/listaCoordenadoria')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		diariasDoMes: function(mes){
			var config = {params: {mes: mes}};
			return $http.get('/rest/diaria/unidades/mes/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		funcionariosPorDataPorId: function(idFuncionario, data1 , data2){
			var config = {params : {idFuncionario : idFuncionario, dataInicial : data1 , dataFinal : data2}};
			return $http.get('/rest/diaria/funcionarios/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
		imprimir: function(idFuncionario, data1 , data2){
			var config = {params : {idFuncionario : idFuncionario, dataInicial : data1 , dataFinal : data2}, responseType: 'arraybuffer'};
				return $http.get('/rest/diaria/funcionarios/imprimir',config )
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});			
		},
		
		buscarDiariaPorFuncionario: function(idDiaria, tipo){
			var config = {params : {idDiaria : idDiaria, tipo :tipo}};
			return $http.get('/rest/diaria/mes/funcionarios/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarDiariaPorFuncionarioPDF: function(idDiaria, tipo){
			var config = {params : {idDiaria : idDiaria, tipo :tipo}, responseType: 'arraybuffer'};
				return $http.get('/rest/diaria/mes/funcionarios/imprimir',config )
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});			
		},
	}
});