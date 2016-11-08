app.controller('unidadeController', function($scope,  unidadeService,  $routeParams) {

	var self = this;
	
	var idUnidade = $routeParams.idUnidade;
	
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
				console.log(self.listaUnidades);
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