app.controller('chamadoTiController', function($scope, $rootScope, chamadoTiService, setorService, usuarioService,  $routeParams) {

	var self = this;
	$scope.habilitaTexto = true;
	$scope.habilitaBotaoAtenderChamado = false;
	$scope.habilitaBotaoFecharChamado = false;
	$rootScope.atualizarListaChamado = false;
	
	var idChamadoTi = $routeParams.idChamadoTi;
	
	self.verificaEquipamento = function(equipamento){
		if(equipamento == "COMPUTADOR"){
			$scope.computador = true;
			$scope.impressora = false;
		}else if(equipamento == "IMPRESSORA"){
			$scope.computador = false;
			$scope.impressora = true;
		}else if(equipamento == null){
			$scope.computador = false;
			$scope.impressora = false;
		}
	};
	self.atualizaListaChamadoSuporte = function(){
		$rootScope.atualizarListaChamado = true;
		if($rootScope.atualizarListaChamado === true ){
				self.verificaMensagemLidaAtualizada();
			}
	};
	
	self.verificaMensagemLida = function(){			
		if($rootScope.atualizarListaChamado === false && $rootScope.logado){
			self.listaSuporte();
			timeoutLida = setTimeout(self.verificaMensagemLida, 40000);			
		}
	};
	
	self.verificaMensagemLidaAtualizada = function(){
		self.listaSuporte();	
		$rootScope.atualizarListaChamado = false;
		clearTimeout(timeoutLida);
		self.verificaMensagemLida();			
	};
	
	self.salva = function(chamadoTi) {
		self.chamadoTi.mensagens = [{texto : $scope.texto}];
		chamadoTiService.salva(self.chamadoTi).
			then(function(response){
				self.chamadoTi = null;
				$scope.texto = null;
				$scope.equipamento = null;
				self.verificaEquipamento($scope.equipamento);
				}, function(errResponse){
			});
		}
	
	self.salvaMensagem = function(chamadoTi) {
		self.chamadoTi.mensagens = null;
		self.chamadoTi.mensagens = [{texto : $scope.texto}];
		chamadoTiService.salvaMensagem(self.chamadoTi).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoTi);
			}, function(errResponse){
		});
	}
	
	self.salvaServicos = function(descricao ) {		
		self.chamadoTi.mensagens = null;
		self.chamadoTi.descricaoServico = descricao;
		chamadoTiService.salvaServicos(self.chamadoTi).
		then(function(response){	
			self.buscarPorId(self.chamadoTi.id);
			}, function(errResponse){				
		});
	};
	
	self.silenciarChamadoFalse = function(chamadoTi) {
		chamadoTi.mensagens = null;
		chamadoTiService.silenciarChamadoFalse(chamadoTi).
			then(function(response){				
				self.listaSuporte();
				}, function(errResponse){					
			});
		};
		
		self.silenciarChamadoTrue = function(chamadoTi) {
			chamadoTi.mensagens = null;
			chamadoTiService.silenciarChamadoTrue(chamadoTi).
				then(function(response){
					self.listaSuporte();
					}, function(errResponse){						
				});
			};
	
	self.atenderChamado = function(chamadoTi) {
		self.chamadoTi.mensagens = null;
		swal({
			  title: 'Atender Chamado!!!',
			  text: "Tem que certeza que deseja atender este chamado?",
			  type: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Atender!'
			}).then(function () {
				chamadoTiService.atenderChamado(self.chamadoTi).
				then(function(response){
					$scope.texto = null;
					self.buscarPorId(idChamadoTi);
					self.verificaMensagemLida();
					}, function(errResponse){
				});			  
			})		
	}
	
	self.fecharChamado = function(chamadoTi) {
		self.chamadoTi.mensagens = null;
		swal({
			  title: 'Encerrar Chamado!!!',
			  text: "Tem que certeza que deseja encerrar este chamado?",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Encerrar!'
			}).then(function () {
		chamadoTiService.fecharChamado(self.chamadoTi).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoTi);
			}, function(errResponse){
		});
	})
}
	
	var vid = document.getElementById("myAudio");
	
	self.enableAutoplay = function() { 
	    vid.autoplay = true;
	    vid.load();
	    
	}
	self.disableAutoplay = function() { 
	    vid.autoplay = false;
	    vid.load();
	} 


	
	self.listaSuporte = function(){
		chamadoTiService.listaSuporte().
			then(function(f){
				self.listaChamadoTiSuporte = f;	
				self.tocaMusica = 0;
				self.contagemSuporte = [];
				for(i = 0 ; i < self.listaChamadoTiSuporte.length ; i++){						
							if(self.listaChamadoTiSuporte[i].lido === false){
								self.contagemSuporte.push({
									cout : self.listaChamadoTiSuporte[i]
								});							
								$rootScope.quantidadeChamadoAbertoTi = self.contagemSuporte.length; 								
					}
					if(self.listaChamadoTiSuporte[i].lido === false && self.listaChamadoTiSuporte[i].silenciar === false){
						
						self.tocaMusica = 1;
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
			 chamadoTiService.listaUsuario().
				then(function(f){
					self.listaChamadoTiUsuario = f;						
					}, function(errResponse){
				});
			};
			self.listaSetor = function(){
				 setorService.lista().
					then(function(f){
						self.listaSetores = f;
						}, function(errResponse){
					});
				};
			self.usuarioLista = function(){
				 usuarioService.lista().
					then(function(f){
						self.usuarios = f;				
						}, function(errResponse){
					});
				};
			
			
		 self.prioridade = function(){
			 chamadoTiService.prioridade().
				then(function(f){
					self.prioridades = f;			
					}, function(errResponse){
				});
			};
			self.status = function(){
				 chamadoTiService.status().
					then(function(f){
						self.listaStatus = f;			
						}, function(errResponse){
					});
				};
				
			self.tipoEquipamento = function(){
				 chamadoTiService.tipoEquipamento().
					then(function(f){
						self.tipoEquipamentos = f;			
						}, function(errResponse){
					});
				};
			self.titulo = function(){
				 chamadoTiService.titulo().
					then(function(f){
						self.titulos = f;			
						}, function(errResponse){
					});
				};
			self.tituloImpressora = function(){
				 chamadoTiService.tituloImpressora().
					then(function(f){
						self.tituloImpressoras = f;			
						}, function(errResponse){
					});
				};	
		
	self.buscarPorId = function(id){
			if(!id)return;
			chamadoTiService.buscarPorId(id).
			then(function(p){
				self.chamadoTi = p;
				if(self.chamadoTi.status == "EM_ANDAMENTO"){	
					$scope.habilitaTexto = true;
					$scope.habilitaBotaoFecharChamado = true;
					$scope.habilitaBotaoAtenderChamado = false;
					$scope.habilitaBotaoServicoChamado = true;
				}else if(self.chamadoTi.status == "ABERTO"){
					$scope.habilitaBotaoAtenderChamado = true;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoServicoChamado = false;
				}else if(self.chamadoTi.status == "FECHADO"){
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaBotaoServicoChamado = true;
				}
				for(i = 0 ; i < self.chamadoTi.mensagens.length ; i++){			
				
					if(self.chamadoTi.mensagens[i].usuario.id != $rootScope.user.usuario.id){
						
						self.chamadoTi.mensagens[i].usuario.id = null;
					}else {					
					}
					$rootScope.quantidadeMensagem = i + 1;
				}
		}, function(errResponse){
			});
		};
	
		if(idChamadoTi){
			self.buscarPorId(idChamadoTi);
			
		}
		
		self.buscaDinamicaUsuario = function(usuario){
			$scope.buscaChamado = null;
			$scope.buscaChamado = usuario.nome;
		}
		self.buscaDinamicaSetor = function(setor){
			$scope.buscaChamado = null;
			$scope.buscaChamado = setor.nome;
		}
		self.buscaDinamicaStatus = function(status){
			$scope.buscaChamado = null;
			$scope.buscaChamado = status;
		}
		self.buscaDinamicaTitulo = function(titulo){
			$scope.buscaChamado = null;
			$scope.buscaChamado = titulo;
		}
		self.buscaDinamicaData = function(data){
			$scope.buscaChamado = null;
			$scope.buscaChamado = data;
		}

});