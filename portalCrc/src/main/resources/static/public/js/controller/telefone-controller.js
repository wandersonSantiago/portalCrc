app.controller('telefoneController', function($scope,  telefoneService,  $routeParams) {

	var self = this;	
	var idTelefone = $routeParams.idTelefone;
	
self.salva = function(listaTelefone) {
	telefoneService.salva(self.listaTelefone).
		then(function(response){
			self.listaTelefone = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(listaTelefone) {
		telefoneService.altera(self.listaTelefone).
		then(function(response){
			self.listaTelefone = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 telefoneService.lista().
			then(function(f){
				self.listaTelefone = f;				
				}, function(errResponse){
			});
		};
		 
	self.buscarPorId = function(id){
			if(!id)return;
			telefoneService.buscarPorId(id).
			then(function(p){
				self.listaTelefone = p;
		}, function(errResponse){
			});
		};
	
		if(idTelefone){
			self.buscarPorId(idTelefone);
			
		}		
				

});