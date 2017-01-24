app.controller('coordenadoriaController', function($scope,  coordenadoriaService, unidadeService,  $routeParams) {

	var self = this;
	
	var idCoordenadoria = $routeParams.idCoordenadoria;
	
	
self.salva = function(coordenadoria) {
	coordenadoriaService.salva(self.coordenadoria).
		then(function(response){
			self.coordenadoria = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(coordenadoria) {
		coordenadoriaService.altera(self.coordenadoria).
		then(function(response){
			self.coordenadoria = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 coordenadoriaService.lista().
			then(function(f){
				self.listaCoordenadoria = f;			
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			coordenadoriaService.buscarPorId(id).
			then(function(p){
				self.coordenadoria = p;
		}, function(errResponse){
			});
		};
	
		if(idCoordenadoria){
			self.buscarPorId(idCoordenadoria);
			
		}		


});