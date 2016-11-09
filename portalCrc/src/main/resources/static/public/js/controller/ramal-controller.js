app.controller('ramalController', function($scope,  ramalService,  $routeParams) {

	var self = this;
	self.listaTelefone = [];
	var idRamal = $routeParams.idRamal;
	
self.salva = function(listaRamal) {
	ramalService.salva(self.listaRamal).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(ramal) {
		ramalService.altera(self.ramal).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 ramalService.lista().
			then(function(f){
				self.listaRamal = f;				
				}, function(errResponse){
			});
		};
		 
	self.buscarPorId = function(id){
			if(!id)return;
			ramalService.buscarPorId(id).
			then(function(p){
				self.ramal = p;
		}, function(errResponse){
			});
		};
	
		if(idRamal){
			self.buscarPorId(idRamal);
			
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