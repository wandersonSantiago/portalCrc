app.controller('diariaController', function($scope,  diariaService,   $routeParams) {

	var self = this;
	
	var idDiaria = $routeParams.idDiaria;
	
	
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
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 diariaService.lista().
			then(function(f){
				self.listaDiaria = f;	
				console.log(f);
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			diariaService.buscarPorId(id).
			then(function(p){
				self.diaria = p;
		}, function(errResponse){
			});
		};
	
		if(idDiaria){
			self.buscarPorId(idDiaria);
			
		}		


});