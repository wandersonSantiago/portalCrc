app.factory('ManutencaoEquipamentoService', function($q, $http){
	
	var url ='/rest/controleIp/equipamento/manutencao';
	return{
		salvar: function(equipamento){
			return $http.post(url , equipamento)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		alterar: function(equipamento){
			return $http.put(url , equipamento)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},

		buscarPorId: function(param){
			return $http.get(url + '/' +param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		buscarPorEquipamento: function(param){
			return $http.get(url+'/equipamento/'+param)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listar: function(){
			return $http.get(url)
			.then(function(response){
				return response.data;				
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listarAtivos: function(){
			return $http.get(url +'/ativos')
			.then(function(response){
				return response.data;				
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		listarAtivosPrioridade: function(){
			return $http.get(url +'/ativos/prioridade')
			.then(function(response){
				return response.data;				
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
				
	}
});