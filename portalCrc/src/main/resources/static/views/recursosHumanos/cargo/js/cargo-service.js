app.factory('CargoService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(cargo){
			 return $http.post('rest/recursosHumanos/cargo/salvar', cargo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('rest/recursosHumanos/cargo/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			buscarPorId: function(param){
				return $http.get('rest/recursosHumanos/cargo/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(cargo){
				return $http.put('rest/recursosHumanos/cargo/alterar', cargo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
	 }
	 
});