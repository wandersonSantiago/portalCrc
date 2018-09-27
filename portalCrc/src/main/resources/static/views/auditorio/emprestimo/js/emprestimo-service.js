app.factory('EmprestimoService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salvar: function(objeto){
			 return $http.post('/rest/auditorio/emprestimo', objeto)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('/rest/auditorio/emprestimo')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
					
			buscarPorId: function(param){
				return $http.get('/rest/auditorio/emprestimo/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(emprestimo){
				return $http.put('/rest/auditorio/emprestimo/', emprestimo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			
			status : function(){
				return $http.get('/rest/auditorio/emprestimo/status')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
	 }
	 
});