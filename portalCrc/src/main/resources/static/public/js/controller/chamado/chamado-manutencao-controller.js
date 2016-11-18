app.controller('chamadoManutencaoController', function($scope,  chamadoManutencaoService,  $routeParams) {

	var self = this;
	
	var idChamadoManutencao = $routeParams.idChamadoManutencao;
	
self.salva = function(chamadoManutencao) {
	self.chamadoManutencao.mensagens = [{texto : $scope.texto}];
	chamadoManutencaoService.salva(self.chamadoManutencao).
		then(function(response){
			self.chamadoManutencao = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(chamadoManutencao) {
		chamadoManutencaoService.altera(self.chamadoManutencao).
		then(function(response){
			self.chamadoManutencao = null;
			}, function(errResponse){
		});
	}

	 self.listaSuporte = function(){
		 chamadoManutencaoService.listaSuporte().
			then(function(f){
				self.listaChamadoManutencaoSuporte = f;	
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
		 self.prioridade = function(){
			 chamadoManutencaoService.prioridade().
				then(function(f){
					self.prioridades = f;			
					}, function(errResponse){
				});
			};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			chamadoManutencaoService.buscarPorId(id).
			then(function(p){
				self.chamadoManutencao = p;
		}, function(errResponse){
			});
		};
	
		if(idChamadoManutencao){
			self.buscarPorId(idChamadoManutencao);
			
		}

});