app.controller('ipController', function($scope,  ipService,  $routeParams) {

	var self = this;
	
	var idIp = $routeParams.idIp;
	
self.salva = function(ip) {
	ipService.salva(self.ip).
		then(function(response){
			self.ip = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(ip) {
		ipService.altera(self.ip).
		then(function(response){
			self.ip = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 ipService.lista().
			then(function(f){
				self.listaIp = f;			
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			ipService.buscarPorId(id).
			then(function(p){
				self.ip = p;
		}, function(errResponse){
			});
		};
	
		if(idIp){
			self.buscarPorId(idIp);
			
		}

});