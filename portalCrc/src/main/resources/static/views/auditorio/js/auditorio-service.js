app.factory('AuditorioService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(objeto){
			 return $http.post('/rest/auditorio', objeto)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('/rest/auditorio')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
					
			buscarPorId: function(param){
				return $http.get('/rest/auditorio/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(auditorio){
				return $http.put('/rest/auditorio/', auditorio)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
	 }
	 
});