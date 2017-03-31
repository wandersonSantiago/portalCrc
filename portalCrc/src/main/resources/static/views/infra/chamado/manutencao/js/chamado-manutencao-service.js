app.factory('ChamadoManutencaoService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(chamado){
			return $http.post('/rest/chamado/chamadoManutencao/salvar', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		salvarServicos: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/servicos', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		salvarMensagem: function(mensagem){
			return $http.put('/rest/chamado/chamadoManutencao/mensagem', mensagem)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		atenderChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/atender', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		fecharChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/fechar', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoFalse: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/silenciar/false', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoTrue: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/silenciar/true', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		alterar: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/alterar', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		relatorioChamadoSuporte: function(pages, maxResults){
			var config = {params: {page: pages , maxResults : maxResults}};
			return $http.get('/rest/chamado/chamadoTi/suporte/relatorio/', config)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		buscarPorId: function(chamado){
			return $http.get('/rest/chamado/chamadoManutencao/buscaPorId/'+chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listarSuporte: function(){
			return $http.get('/rest/chamado/chamadoManutencao/suporte/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		listarUsuario: function(){
			return $http.get('/rest/chamado/chamadoManutencao/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		prioridade: function(){
			return $http.get('/rest/chamado/chamadoManutencao/prioridade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		status : function(){
			return $http.get('/rest/chamado/chamadoTi/status')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		titulo : function(){
			return $http.get('/rest/chamado/chamadoManutencao/titulo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});