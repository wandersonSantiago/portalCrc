app.factory('equipamentoService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(equipamento){
			return $http.post('/rest/controleIp/equipamento', equipamento)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Salvo com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		altera: function(equipamento){
			return $http.put('/rest/controleIp/equipamento', equipamento)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Alterado com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"falha na conexão",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(param){
			return $http.get('/rest/controleIp/equipamento/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		
		lista: function(){
			return $http.get('/rest/controleIp/equipamento')
			.then(function(response){
				return response.data;
				
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		listaTipoEquipamento: function(){
			return $http.get('/rest/controleIp/equipamento/tipoEquipamento')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
				
	}
});