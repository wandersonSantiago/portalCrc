app.controller('secretariaController', function($scope,  secretariaService,  $routeParams) {

	var self = this;
	
	var idFSecretaria = $routeParams.idFSecretaria;
	
self.salva = function(secretaria) {
	secretariaService.salva(self.secretaria).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(secretaria) {
		secretariaService.altera(self.secretaria).
		then(function(response){
			self.secretaria = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 secretariaService.lista().
			then(function(f){
				self.listaSecretaria = f;				
				}, function(errResponse){
			});
		};
		 
	self.buscarPorId = function(id){
			if(!id)return;
			secretariaService.buscarPorId(id).
			then(function(p){
				self.secretaria = p;
		}, function(errResponse){
			});
		};
	
		if(idFSecretaria){
			self.buscarPorId(idFSecretaria);
			
		}

});