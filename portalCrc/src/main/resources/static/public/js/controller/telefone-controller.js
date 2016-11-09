app.controller('telefoneController', function($scope,  telefoneService,  $routeParams) {

	var self = this;
	self.listaTelefone = [];
	var idTelefone = $routeParams.idTelefone;
	
self.salva = function(telefone) {
	telefoneService.salva(self.telefone).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(telefone) {
		telefoneService.altera(self.telefone).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 telefoneService.lista().
			then(function(f){
				self.listaTelefone = f;				
				}, function(errResponse){
			});
		};
		 
	self.buscarPorId = function(id){
			if(!id)return;
			telefoneService.buscarPorId(id).
			then(function(p){
				self.telefone = p;
		}, function(errResponse){
			});
		};
	
		if(idTelefone){
			self.buscarPorId(idTelefone);
			
		}
		
		self.adicionarTelefones = function(descricao, telefone, setor){
			self.listaTelefone.push({
				descricao :  descricao,
				telefone : telefone,
				setor : setor
			});
			
			$scope.visialuzarTable = true;
			$scopedescricao = null;
			$scope.telefone = null;
		}
			
	
		
		self.ativarExcluirLote = function(listaTelefone){
			self.listaTelefone.filter(function(telefone){
			if(telefone.selecionado){
				$scope.ativadoExcluirLote = true; }
			});
		}
	

	
		self.apagarTelefone = function(listaTelefone){
				self.listaTelefone = self.listaTelefone.filter(function(telefone){
				if(!telefone.selecionado) return telefone;
				$scope.ativadoExcluirLote = false;
			});
		}
		

});