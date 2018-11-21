app.factory('ChamadoTiService', function($rootScope, toastr, $http, $q){
	
	var url = '/rest/chamado/chamadoTi'
	return{
		
		salvar: function(form){
			return $http.post(url , form, {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined}
	        }).then(function(response){
				return response.data;
			},function(errResponse){
			return $q.reject(errResponse);
			});
		},
		salvarMensagem: function(mensagem){
			return $http.post('/rest/chamado/mensagem', mensagem)
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
		marcarCommoLido: function(chamado){
			return $http.put('/rest/chamado/mensagem/lidas', chamado)
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
		silenciarChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoTi/silenciar', chamado)
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
		
		count: function(){
			return $http.get('/rest/chamado/chamadoTi/count')
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
		relatorioPorData: function(dataInicial, dataFinal){
			return $http.get('/rest/chamado/chamadoTi/suporte/relatorio/dataInicial/' +dataInicial +'/dataFinal/' +dataFinal)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					return $q.reject(errResponse);
			});
		},
		
		relatorioPorDataPorTitulo: function(dataInicial, dataFinal ,titulo){
			return $http.get('/rest/chamado/chamadoTi/suporte/relatorio/dataInicial/' +dataInicial +'/dataFinal/' +dataFinal + '/titulo/' +titulo)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 30000,  text : errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
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
		filter: function(chamadoFilter){
			return $http.post(url + '/filter', chamadoFilter)
			.then(function(response){
				return response.data;
			},function(errResponse){
				return $q.reject(errResponse);
			});
		},
		pdf: function(chamadoFilter){
				return $http.post(url + '/imprimir',chamadoFilter, { responseType: 'arraybuffer'} )
				.then(function(response){
					return response.data;
				},function(errResponse){
					return $q.reject(errResponse);
				});			
		},
				
	}
});