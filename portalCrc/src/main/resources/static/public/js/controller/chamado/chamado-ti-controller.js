app.controller('chamadoTiController', function($scope, $rootScope, chamadoTiService,  $routeParams) {

	var self = this;
	$scope.habilitaTexto = true;
	$scope.habilitaBotaoAtenderChamado = false;
	$scope.habilitaBotaoFecharChamado = false;
	
	var idChamadoTi = $routeParams.idChamadoTi;
	
self.salva = function(chamadoTi) {
	self.chamadoTi.mensagens = [{texto : $scope.texto}];
	chamadoTiService.salva(self.chamadoTi).
		then(function(response){
			self.chamadoTi = null;
			}, function(errResponse){
		});
	}
	
	self.salvaMensagem = function(chamadoTi) {
		self.chamadoTi.mensagens = [{texto : $scope.texto}];
		self.chamadoTi.dataAbertura = null;
		chamadoTiService.salvaMensagem(self.chamadoTi).
		then(function(response){
			$scope.texto = null;
			self.buscarPorId(idChamadoTi);
			}, function(errResponse){
		});
	}
	self.atenderChamado = function(chamadoTi) {
		self.chamadoTi.mensagens = null;
		self.chamadoTi.dataAbertura = null;	
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
		self.chamadoTi.dataAbertura = null;	
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
	
	var vid = document.getElementById("myVideo");
	
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
				for(i = 0 ; i < self.listaChamadoTiSuporte.length ; i++){
					if(self.listaChamadoTiSuporte[i].lido == false){
						
						$scope.tocaMusica = i;
						console.log($scope.tocaMusica);
					}
					if($scope.tocaMusica > 0 ){
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
					/*for(i = 0 ; i < self.listaChamadoTiUsuario.length ; i++){
						if(self.listaChamadoTiUsuario[i].lido == false){
							
							$scope.tocaMusica 	 = i;
						}
						if($scope.tocaMusica > 0 ){
							console.log("tetetet");
							self.enableAutoplay(); 
						}else{
							self.disableAutoplay();
						}
						
					}*/
					}, function(errResponse){
				});
			};
			
			self.verificaMensagemLida = function(){
				self.listaSuporte();
				self.listaUsuario();				
			}
			
		 self.prioridade = function(){
			 chamadoTiService.prioridade().
				then(function(f){
					self.prioridades = f;			
					}, function(errResponse){
				});
			};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			chamadoTiService.buscarPorId(id).
			then(function(p){
				self.chamadoTi = p;
				if(self.chamadoTi.status == "Em_Andamento"){	
					$scope.habilitaTexto = true;
					$scope.habilitaBotaoFecharChamado = true;
					$scope.habilitaBotaoAtenderChamado = false;
				}else if(self.chamadoTi.status == "Aberto"){
					$scope.habilitaBotaoAtenderChamado = true;
					$scope.habilitaBotaoFecharChamado = false;
					$scope.habilitaTexto = false;
				}else if(self.chamadoTi.status == "Fechado"){
					$scope.habilitaTexto = false;
					$scope.habilitaBotaoFecharChamado = false;
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

});