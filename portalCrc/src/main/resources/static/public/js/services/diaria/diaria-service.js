app.factory('diariaService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(objeto){
			return $http.post('/rest/diaria', objeto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 1000000,  text : errResponse.data.message ,  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		encerrar: function(objeto){
			return $http.put('/rest/diaria/encerrar', objeto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Mês de lançamento encerrado", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 100000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(objeto){
			return $http.put('/rest/diaria', objeto)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(objeto){
			return $http.get('/rest/diaria/' +objeto)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		
		diariasEmAberto: function(){
			return $http.get('/rest/diaria/diariasEmAberto')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		lista: function(page , maxResults){
			var config = {params: {page: page , maxResults : maxResults}};
			return $http.get('/rest/diaria/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaMes: function(){
			return $http.get('/rest/diaria/mes')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaUnidade: function(){
			return $http.get('/rest/diaria/listaUnidade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaCoordenadoria: function(){
			return $http.get('/rest/diaria/listaCoordenadoria')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
	}
});