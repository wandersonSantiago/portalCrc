app.factory('SetorService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(cargo){
			 return $http.post('rest/recursosHumanos/setor/salvar', cargo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('rest/recursosHumanos/setor/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			listarPorUnidade: function(){
				return $http.get('rest/recursosHumanos/setor/lista/unidade')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			listarTipoSetor: function(){
				return $http.get('rest/recursosHumanos/setor/tipoSetor')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			buscarPorId: function(param){
				return $http.get('rest/recursosHumanos/setor/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(cargo){
				return $http.put('rest/recursosHumanos/setor/alterar', cargo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
	 }
	 
});