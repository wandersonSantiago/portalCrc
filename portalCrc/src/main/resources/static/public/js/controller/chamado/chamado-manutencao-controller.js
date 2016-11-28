app.controller('chamadoManutencaoController', function($scope, $rootScope, chamadoManutencaoService, $location,  $routeParams) {

	var self = this;
	$scope.habilitaTexto = true;
	$scope.habilitaBotaoAtenderChamado = false;
	$scope.habilitaBotaoFecharChamado = false;
	
	var idChamadoManutencao = $routeParams.idChamadoManutencao;
	
	
	self.atualizaListaChamadoSuporte = function(){
		$location.path('/chamado/manutencao/suporte/lista');
	};
	
self.salva = function(chamadoManutencao) {
	self.chamadoManutencao.mensagens = [{texto : $scope.texto}];
	chamadoManutencaoService.salva(self.chamadoManutencao).
		then(function(response){
			self.chamadoManutencao = null;
			}, function(errResponse){
		});
	};
	
	self.silenciarChamadoFalse = function(chamadoManutencao) {
		chamadoManutencao.mensagens = null;
		chamadoManutencaoService.silenciarChamadoFalse(chamadoManutencao).
			then(function(response){				
				self.listaSuporte();
				}, function(errResponse){					
			});
		};
		
		self.silenciarChamadoTrue = function(chamadoManutencao) {
			chamadoManutencao.mensagens = null;
			chamadoManutencaoService.silenciarChamadoTrue(chamadoManutencao).
				then(function(response){
					self.listaSuporte();
					}, function(errResponse){						
				});
			};
	
	self.salvaMensagem = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = null;
		self.chamadoManutencao.mensagens = [{texto : $scope.texto}];
		chamadoManutencaoService.salvaMensagem(self.chamadoManutencao).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoManutencao);
			}, function(errResponse){
		});
	};
	self.salvaServicos = function(descricao) {			
		self.chamadoManutencao.descricaoServico = descricao;
		console.log(self.chamadoManutencao);
		chamadoManutencaoService.salvaServicos(self.chamadoManutencao).
		then(function(response){		
			}, function(errResponse){
		});
	};
	self.atenderChamado = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = null;
		swal({
			  title: 'Atender Chamado!!!',
			  text: "Tem que certeza que deseja atender este chamado?",
			  type: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Atender!'
			}).then(function () {
				chamadoManutencaoService.atenderChamado(self.chamadoManutencao).
				then(function(response){
					$scope.texto = null;
					self.buscarPorId(idChamadoManutencao);
					self.verificaMensagemLida();
					}, function(errResponse){
				});			  
			})		
	};
	
	self.fecharChamado = function(chamadoManutencao) {
		self.chamadoManutencao.mensagens = null;
		swal({
			  title: 'Encerrar Chamado!!!',
			  text: "Tem que certeza que deseja encerrar este chamado?",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Encerrar!'
			}).then(function () {
		chamadoManutencaoService.fecharChamado(self.chamadoManutencao).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoManutencao);
			}, function(errResponse){
		});
	})
};
	
	var vid = document.getElementById("myVideo");
	
	self.enableAutoplay = function() { 
	    vid.autoplay = true;
	    vid.load();
	};
	self.disableAutoplay = function() { 
	    vid.autoplay = false;
	    vid.load();
	};


	
	 self.listaSuporte = function(){
		 chamadoManutencaoService.listaSuporte().
			then(function(f){
				self.listaChamadoManutencaoSuporte = f;	
				self.tocaMusica = 0;
				for(i = 0 ; i < self.listaChamadoManutencaoSuporte.length ; i++){
					if(self.listaChamadoManutencaoSuporte[i].lido === false && self.listaChamadoManutencaoSuporte[i].silenciar === false){
						
						self.tocaMusica = i;
					}
					if(self.tocaMusica > 0 ){
						self.enableAutoplay(); 						
					}else{
						self.disableAutoplay();
					}
					
				}
				}, function(errResponse){
			});
		};
		self.listaUsuario = function(){
			 chamadoManutencaoService.listaUsuario().
				then(function(f){
					self.listaChamadoManutencaoUsuario = f;					
					}, function(errResponse){
				});
			};
			
			
			self.verificaMensagemLida = function(){
				self.listaSuporte();		
				setTimeout(self.verificaMensagemLida, 40000);
			};
			
		 self.prioridade = function(){
			 chamadoManutencaoService.prioridade().
				then(function(f){
					self.prioridades = f;			
					}, function(errResponse){
				});
			};
		
			self.status = function(){
				chamadoManutencaoService.status().
					then(function(f){
						self.listaStatus = f;			
						}, function(errResponse){
					});
				};
	self.buscarPorId = function(id){
			if(!id)return;
			chamadoManutencaoService.buscarPorId(id).
			then(function(p){
				self.chamadoManutencao = p;
				if(self.chamadoManutencao.status == "Em_Andamento"){	
					$scope.habilitaTexto = true;
					$scope.habilitaBotaoFecharChamado = true;
					$scope.habilitaBotaoAtenderChamado = false;
					$scope.habilitaBotaoServicoChamado = true;
				}else if(self.chamadoManutencao.status == "Aberto"){
					$scope.habilitaBotaoAtenderChamado = true;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoServicoChamado = false;
				}else if(self.chamadoManutencao.status == "Fechado"){
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaBotaoServicoChamado = true;
				}
				for(i = 0 ; i < self.chamadoManutencao.mensagens.length ; i++){			
				
					if(self.chamadoManutencao.mensagens[i].usuario.id != $rootScope.user.usuario.id){
						
						self.chamadoManutencao.mensagens[i].usuario.id = null;
					}else {					
					}
					$rootScope.quantidadeMensagem = i + 1;
				}
		}, function(errResponse){
			});
		};
	
		if(idChamadoManutencao){
			self.buscarPorId(idChamadoManutencao);
			
		}

});