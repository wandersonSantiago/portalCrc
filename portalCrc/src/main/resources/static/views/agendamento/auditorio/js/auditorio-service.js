app.factory('AuditorioService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(objeto){
			 return $http.post('/rest/agendamento/auditorio', objeto)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('/rest/agendamento/auditorio')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
					
			buscarPorId: function(param){
				return $http.get('/rest/agendamento/auditorio/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(auditorio){
				return $http.put('/rest/agendamento/auditorio/', auditorio)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
	 }
	 
});