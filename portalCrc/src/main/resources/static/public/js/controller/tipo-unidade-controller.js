app.controller('tipoUnidadeController', function($scope,  tipoUnidadeService,  $routeParams) {

	var self = this;
	
	var idTipoUnidade = $routeParams.idTipoUnidade;
	
self.salva = function(tipoUnidade) {
	tipoUnidadeService.salva(self.tipoUnidade).
		then(function(response){
			self.tipoUnidade = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(tipoUnidade) {
		tipoUnidadeService.altera(self.tipoUnidade).
		then(function(response){
			self.tipoUnidade = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 tipoUnidadeService.lista().
			then(function(f){
				self.listatipoUnidade = f;			
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			tipoUnidadeService.buscarPorId(id).
			then(function(p){
				self.tipoUnidade = p;
		}, function(errResponse){
			});
		};
	
		if(idTipoUnidade){
			self.buscarPorId(idTipoUnidade);
			
		}

});