app.controller('itemDiariaController', function($scope,  $rootScope, itemDiariaService, $location,  $routeParams) {

	var self = this;
	
	var idDiaria = $routeParams.idDiaria;
	var idItemDiaria = $routeParams.idItemDiaria;
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
	
	self.altera = function() {
		itemDiariaService.altera(self.itemDiaria).
		then(function(response){
			$location.path("/diaria/item/lista/unidade/" + self.itemDiaria.diaria.id);
			}, function(errResponse){
				
		});
	};

	
	self.excluir = function(objeto) {
		swal({
			  title: 'Excluir diária!!!',
			  text: 'Tem certeza que deseja excluir esta diária',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Excluir'
			}).then(function () {
				itemDiariaService.excluir(objeto.id).
				then(function(response){	
					self.listaUnidade(objeto.diaria.id);					
					}, function(errResponse){
				});	
				 
			})		
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
				self.itemDiaria = p;
				self.itemDiaria.dataDiaria = new Date(p.dataDiaria);
		}, function(errResponse){
			});
		};
	
		if(idItemDiaria){
			self.buscarPorId(idItemDiaria);
			
		}	
		
		self.buscarDiariaPorId = function(id){
			if(!id)return;
			itemDiariaService.buscarDiariaPorId(id).
			then(function(p){
				self.diaria = p;
				self.diaria.dataDiaria = new Date(p.dataDiaria);
				$scope.itemCtrl.itemDiaria = {diaria : self.diaria};
		}, function(errResponse){
			});
		};
	
		if(idDiaria){
			self.buscarDiariaPorId(idDiaria);
			
		}
		
});