app.controller('secretariaController', function($scope,  secretariaService,  $routeParams) {

	var self = this;
	self.listaTelefone = [];
	var idSecretaria = $routeParams.idSecretaria;
	
self.salva = function(secretaria) {
	secretariaService.salva(self.secretaria).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(secretaria) {
		secretariaService.altera(self.secretaria).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 secretariaService.lista().
			then(function(f){
				self.listaSecretaria = f;				
				}, function(errResponse){
			});
		};
		 
	self.buscarPorId = function(id){
			if(!id)return;
			secretariaService.buscarPorId(id).
			then(function(p){
				self.secretaria = p;
		}, function(errResponse){
			});
		};
	
		if(idSecretaria){
			self.buscarPorId(idSecretaria);
			
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