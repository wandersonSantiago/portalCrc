app.factory('PermissaoService', function($rootScope, toastr, $http,$q){
	
	
	return{
		
		salvar: function(permissao){
			return $http.post('/rest/permissao', permissao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		listar: function(){
			return $http.get('rest/permissao')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		buscarPorId: function(param){
			return $http.get('rest/permissao/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(permissao){
			return $http.put('rest/permissao', permissao)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		modulos: function(){
			return $http.get('rest/permissao/modulos')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});