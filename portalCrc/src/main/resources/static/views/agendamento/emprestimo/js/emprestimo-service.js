app.factory('EmprestimoService', function($http, $q , $rootScope, toastr)
{

	 return {
		 salvar: function(objeto){
			 return $http.post('/rest/agendamento/emprestimo', objeto)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			listar: function(){
				return $http.get('/rest/agendamento/emprestimo')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
					
			buscarPorId: function(param){
				return $http.get('/rest/agendamento/emprestimo/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			alterar: function(emprestimo){
				return $http.put('/rest/agendamento/emprestimo/', emprestimo)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			
			
			status : function(){
				return $http.get('/rest/agendamento/emprestimo/status')
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
			buscarPorTexto: function(texto, pagina){
				var config = {params: {page: pagina, descricao:texto}};
				return $http.get('/rest/agendamento/emprestimo/descricao', config)
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});
			},
			
	 }
	 
});