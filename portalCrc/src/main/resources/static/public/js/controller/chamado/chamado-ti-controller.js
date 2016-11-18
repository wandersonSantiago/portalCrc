app.controller('chamadoTiController', function($scope,  chamadoTiService,  $routeParams) {

	var self = this;
	var idChamadoTi = $routeParams.idChamadoTi;
	self.mensagem = {};
	
self.salva = function(chamadoTi) {
	self.chamadoTi.mensagens = [{texto : $scope.texto}];
	chamadoTiService.salva(self.chamadoTi).
		then(function(response){
			self.chamadoTi = null;
			}, function(errResponse){
		});
	}
	
self.salvaMensagem = function(texto , chamadoTi) {
	self.mensagem.texto = texto;	
	self.mensagem.chamado = chamadoTi;
	console.log(self.mensagem);
	chamadoTiService.salvaMensagem(self.mensagem).
		then(function(response){
			self.mensagem = null;
			}, function(errResponse){
		});
	}

	self.altera = function(chamadoTi) {
		chamadoTiService.altera(self.chamadoTi).
		then(function(response){
			self.chamadoTi = null;
			}, function(errResponse){
		});
	}

	 self.listaSuporte = function(){
		 chamadoTiService.listaSuporte().
			then(function(f){
				self.listaChamadoTiSuporte = f;		
				console.log(self.listaChamadoTiSuporte);
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
				console.log(self.chamadoTi);
		}, function(errResponse){
			});
		};
	
		if(idChamadoTi){
			self.buscarPorId(idChamadoTi);
			
		}

});