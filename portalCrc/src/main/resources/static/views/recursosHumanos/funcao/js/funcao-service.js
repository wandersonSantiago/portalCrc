app.factory('FuncaoService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(funcao){
			 return $http.post('rest/recursosHumanos/funcao/salvar', funcao)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('rest/recursosHumanos/funcao/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			buscarPorId: function(param){
				return $http.get('rest/recursosHumanos/funcao/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(cargo){
				return $http.put('rest/recursosHumanos/funcao/alterar', cargo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
	 }
	 
});