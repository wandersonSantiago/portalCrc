app.controller('diariaController', function($scope,  diariaService, $location,  $routeParams) {

	var self = this;
	
	var idDiaria = $routeParams.idDiaria;

	$scope.listaDiariaExcel = [];
	
self.salva = function(diaria) {
	diariaService.salva(self.diaria).
		then(function(response){
			self.diaria = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(diaria) {
		diariaService.altera(self.diaria).
		then(function(response){
			self.diaria = null;
			$location.path("/diaria/lista/unidade");
			}, function(errResponse){
				
		});
	}

	 self.lista = function(){
		 diariaService.lista().
			then(function(f){
				self.listaDiaria = f;	
				forLista(f);
				}, function(errResponse){
			});
		};
		
	 self.listaCoordenadoria = function(){
		 diariaService.listaCoordenadoria().
			then(function(f){
				self.listaDiaria = f;	
				forLista(f);
				}, function(errResponse){
			});
		};
		
	 self.listaUnidade = function(){
		 diariaService.listaUnidade().
			then(function(f){
				self.listaDiaria = f;	
				forLista(f);
				}, function(errResponse){
			});
		};
		
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
			diariaService.buscarPorId(id).
			then(function(p){
				self.diaria = p;
				self.diaria.dataDiaria = new Date(p.dataDiaria);
		}, function(errResponse){
			});
		};
	
		if(idDiaria){
			self.buscarPorId(idDiaria);
			
		}		


});