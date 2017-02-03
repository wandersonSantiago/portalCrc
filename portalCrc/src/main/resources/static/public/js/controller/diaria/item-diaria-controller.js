app.controller('itemDiariaController', function($scope,  $rootScope, itemDiariaService, $location,  $routeParams) {

	var self = this;
	
	var idDiaria = $routeParams.idDiaria;
	var idDiariaCoordenadoria = $routeParams.idDiariaCoordenadoria;
	var idDiariaUnidade = $routeParams.idDiariaUnidade;
	var idDiariaGeral = $routeParams.idDiariaGeral;
	$scope.listaDiariaExcel = [];
	
	self.salva = function() {
		itemDiariaService.salva(self.itemDiaria).
			then(function(response){
				limpaCampos();
				}, function(errResponse){
			});
	};
	
	limpaCampos = function(){
		self.itemDiaria.destino = null;
		self.itemDiaria.dataDiaria = null;
		self.itemDiaria.valorDiaria = null;
		self.itemDiaria.valorPassagem = null;
		self.itemDiaria.motivo = null;
	};
	self.altera = function(diaria) {
		itemDiariaService.altera(self.diaria).
		then(function(response){
			self.diaria = null;
			$location.path("/diaria/lista/unidade");
			}, function(errResponse){
				
		});
	};

	 self.listaSecretaria = function(id){
		 itemDiariaService.listaSecretaria(id).
			then(function(f){
				$scope.listaDiaria = f;	
				forLista(f);
				}, function(errResponse){
			});
		};
		if(idDiariaGeral){
			self.listaSecretaria(idDiariaGeral);
		}
		
	 self.listaCoordenadoria = function(id){
		 itemDiariaService.listaCoordenadoria(id).
			then(function(f){
				$scope.listaDiaria = f;	
				forLista(f);
				}, function(errResponse){
			});
		};
		
		if(idDiariaCoordenadoria){
			self.listaCoordenadoria(idDiariaCoordenadoria);
		}
	 self.listaUnidade = function(id){
		 itemDiariaService.listaUnidade(id).
			then(function(f){
				$scope.listaDiaria = f;	
				forLista(f);
				
				}, function(errResponse){
			});
		};
		if(idDiariaUnidade){
			self.listaUnidade(idDiariaUnidade);
		}
		
		forLista = function(f){
			for(i = 0 ; i < f.length ; i++){
				listaExcel(f[i]);
			}
		}
		
		listaExcel = function(lista){
			$scope.listaDiariaExcel.push({
				nome : lista.funcionario.pessoa.nomeCompleto,
				cargo : lista.funcionario.cargoAtual.descricao,
				unidadeLotado :		lista.unidade.dadosUnidade.mnemonico,
				dataDiaria: lista.dataDiaria,
				destino: lista.destino,
				motivo: lista.motivo,
				valorDiaria: lista.valorDiaria,
				valorPassagem: lista.valorPassagem
			});
		};
		
	self.buscarPorId = function(id){
			if(!id)return;
			itemDiariaService.buscarPorId(id).
			then(function(p){
				self.diaria = p;
				self.diaria.dataDiaria = new Date(p.dataDiaria);
				$scope.itemCtrl.itemDiaria = { diaria : self.diaria};
				console.log($scope.itemCtrl.itemDiaria);
		}, function(errResponse){
			});
		};
	
		if(idDiaria){
			self.buscarPorId(idDiaria);
			
		}		
		
});