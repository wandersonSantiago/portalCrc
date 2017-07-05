app.factory('FuncionarioContaDiariaService', function($q, $http){
	var url = '/rest/diaria/funcionario/conta';
	
	return{
		salvar: function(objeto){
			return $http.post(url, objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(objeto){
			return $http.put(url, objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		excluir: function(objeto){
			return $http.delete(url+objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(objeto){
			return $http.get(url +'/'+objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		buscarPorIdFuncionario: function(objeto){
			return $http.get(url +'/funcionario/'+objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listarPorUnidade: function(){
			return $http.get(url +'/unidade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		limite: function(){
			return $http.get(url +'/limite')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		indice: function(){
			return $http.get(url +'/indice')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorTexto :function(texto, pagina){
			pagina = 0;
			var config = {params: {page: pagina, q:texto}};
			return $http.get(url +'/buscar/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		
	}
});