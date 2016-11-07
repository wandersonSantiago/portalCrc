app.factory('setorService', function($http,$q , $rootScope, toastr)
{

	 return {
		 salva: function(cargo){
			 return $http.post('rest/recursosHumanos/setor/salvar', cargo)
				.then(function(response){
					sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			
			lista: function(){
				return $http.get('rest/recursosHumanos/setor/lista')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000, text :"não contem setor salvo", type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
				});
			},
			
			listaTipoSetor: function(){
				return $http.get('rest/recursosHumanos/setor/tipoSetor')
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000, text :"não contem dados salvo", type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
				});
			},
			
			buscarPorId: function(param){
				return $http.get('rest/recursosHumanos/setor/buscaPorId/'+param)
				.then(function(response){
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
					return $q.reject(errResponse);
				});
			},
			
			altera: function(cargo){
				return $http.put('rest/recursosHumanos/setor/alterar', cargo)
				.then(function(response){
					sweetAlert({ timer : 3000, text :"Alterado com sucesso", type : "success", width: 300, higth: 100, padding: 20});
					return response.data;
				},function(errResponse){
					sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
					return $q.reject(errResponse);
				});
			},
	 }
	 
});