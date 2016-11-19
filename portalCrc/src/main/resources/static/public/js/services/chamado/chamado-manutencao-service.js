app.factory('chamadoManutencaoService', function($rootScope, toastr, $http){
	
	
	return{
		salva: function(chamado){
			return $http.post('/rest/chamado/chamadoManutencao/salvar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Enviado com sucesso", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Não foi possivel Enviar",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		salvaMensagem: function(mensagem){
			return $http.put('/rest/chamado/chamadoManutencao/mensagem', mensagem)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Mensagem Enviada", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Mensagem não Enviada",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		atenderChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/atender', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Chamado Aberto", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"Erro",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		fecharChamado: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/fechar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Encerrado", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não encerrado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		altera: function(chamado){
			return $http.put('/rest/chamado/chamadoManutencao/alterar', chamado)
			.then(function(response){
				sweetAlert({ timer : 3000, text :"Alterado!!!", type : "success", width: 300, higth: 100, padding: 20});
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  text :"não Alterado",  type : "error", width: 300, higth: 300, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
		
		buscarPorId: function(chamado){
			return $http.get('/rest/chamado/chamadoManutencao/buscaPorId/'+chamado)
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "não fopi possivel concluir", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},		
		
		listaSuporte: function(){
			return $http.get('/rest/chamado/chamadoManutencao/suporte/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "não existe chamados salvo", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		listaUsuario: function(){
			return $http.get('/rest/chamado/chamadoManutencao/usuario/lista')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		prioridade: function(){
			return $http.get('/rest/chamado/chamadoManutencao/prioridade')
			.then(function(response){
				return response.data;
			},function(errResponse){
				sweetAlert({ timer : 3000,  type : "error", width: 200, higth: 100, padding: 20});
				return $q.reject(errResponse);
			});
		},
		
	}
});