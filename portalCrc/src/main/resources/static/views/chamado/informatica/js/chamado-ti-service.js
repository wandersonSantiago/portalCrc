app.factory('ChamadoTiService', function($rootScope, toastr, $http){
	
	
	return{
		salvar: function(chamado){
			return $http.post('/rest/chamado/chamadoTi/salvar', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		salvarMensagem: function(mensagem){
			return $http.put('/rest/chamado/chamadoTi/mensagem', mensagem)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		salvarServicos: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/servicos', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		atenderChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/atender', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoFalse: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/silenciar/false', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		silenciarChamadoTrue: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/silenciar/true', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		fecharChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/fechar', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		altera: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/alterar', chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(chamado){
			return $http.get('/rest/chamado/chamadoTi/buscaPorId/'+chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},		
		
		listaSuporte: function(){
			return $http.get('/rest/chamado/chamadoTi/suporte/lista')
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
		listaUsuario: function(){
			return $http.get('/rest/chamado/chamadoTi/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		prioridade: function(){
			return $http.get('/rest/chamado/chamadoTi/prioridade')
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
		tipoEquipamento : function(){
			return $http.get('/rest/chamado/chamadoTi/equipamento/tipo')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		titulo : function(){
			return $http.get('/rest/chamado/chamadoTi/titulo/tI')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		tituloImpressora : function(){
			return $http.get('/rest/chamado/chamadoTi/titulo/impressora')
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		
	}
});