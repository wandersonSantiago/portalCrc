app.controller('pontoController', function($scope,  pontoService,  $routeParams) {

	var self = this;
	
	var idPonto = $routeParams.idPonto;
	
self.salva = function(ponto) {
	pontoService.salva(self.ponto).
		then(function(response){
			self.ponto = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(ponto) {
		pontoService.altera(self.ponto).
		then(function(response){
			self.ponto = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 pontoService.lista().
			then(function(f){
				self.listaPonto = f;			
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			pontoService.buscarPorId(id).
			then(function(p){
				self.ponto = p;
		}, function(errResponse){
			});
		};
	
		if(idPonto){
			self.buscarPorId(idPonto);
			
		}

});