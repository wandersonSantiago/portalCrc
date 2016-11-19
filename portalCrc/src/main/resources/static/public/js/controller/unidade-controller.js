app.controller('unidadeController', function($scope, buscaCepService,  unidadeService,  $routeParams) {

	var self = this;
	
	var idUnidade = $routeParams.idUnidade;
	
self.findCep = function () {
		
		self.cep = $scope.unidadeCtrl.unidade.dadosUnidade.endereco.cep;
		buscaCepService.get({'cep': self.cep}).$promise
		.then(function success(result){
			$scope.unidadeCtrl.unidade.dadosUnidade.endereco = result;
		}).catch(function error(msg) {
		});
		
    }

self.salva = function(unidade) {
	unidadeService.salva(self.unidade).
		then(function(response){
			self.unidade = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(unidade) {
		unidadeService.altera(self.unidade).
		then(function(response){
			self.unidade = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 unidadeService.lista().
			then(function(f){
				self.listaUnidades = f;	
				}, function(errResponse){
			});
		};
		self.listaTipoUnidade = function(){
			 unidadeService.listaTipoUnidade().
				then(function(f){
					self.listaTipoUnidade = f;				
					}, function(errResponse){
				});
			};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			unidadeService.buscarPorId(id).
			then(function(p){
				self.unidade = p;
		}, function(errResponse){
			});
		};
	
		if(idUnidade){
			self.buscarPorId(idUnidade);
			
		}

});