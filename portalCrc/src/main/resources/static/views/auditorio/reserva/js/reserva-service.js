app.factory('ReservaService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(objeto){
			 return $http.post('/rest/auditorio/reserva', objeto)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('/rest/auditorio/reserva')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
					
			buscarPorId: function(param){
				return $http.get('/rest/auditorio/reserva/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
		
			
			alterar: function(reserva){
				return $http.put('/rest/auditorio/reserva/', reserva)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
	 }
	 
});