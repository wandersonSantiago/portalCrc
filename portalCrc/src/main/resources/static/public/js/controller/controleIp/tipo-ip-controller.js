app.controller('tipoIpController', function($scope,  tipoIpService,  $routeParams) {

	var self = this;
	
	var idTipoIp = $routeParams.idTipoIp;
	
self.salva = function(tipoIp) {
	tipoIpService.salva(self.tipoIp).
		then(function(response){
			self.tipoip = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(tipoIp) {
		tipoIpService.altera(self.tipoIp).
		then(function(response){
			self.tipoip = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 tipoIpService.lista().
			then(function(f){
				self.listaTipoIp = f;			
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
			if(!id)return;
			tipoIpService.buscarPorId(id).
			then(function(p){
				self.tipoIp = p;
		}, function(errResponse){
			});
		};
	
		if(idTipoIp){
			self.buscarPorId(idTipoIp);
			
		}

});