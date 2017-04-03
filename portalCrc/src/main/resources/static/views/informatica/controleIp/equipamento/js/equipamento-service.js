app.factory('EquipamentoService', function($q, $http){
	
	
	return{
		salvar: function(equipamento){
			return $http.post('/rest/controleIp/equipamento', equipamento)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(equipamento){
			return $http.put('/rest/controleIp/equipamento', equipamento)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(param){
			return $http.get('/rest/controleIp/equipamento/buscaPorId/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get('/rest/controleIp/equipamento')
			.then(function(response){
				return response.data;
				
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listarTipoEquipamento: function(){
			return $http.get('/rest/controleIp/equipamento/tipoEquipamento')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
	}
});