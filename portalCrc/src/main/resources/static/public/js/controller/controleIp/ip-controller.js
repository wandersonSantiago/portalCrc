app.controller('ipController', function($scope,  ipService,  $routeParams, $location) {

	var self = this;
	
	var idIp = $routeParams.idIp;
	
self.salva = function(ip) {
	concatenaIp();
	self.ip.numero = $scope.numero;				
	ipService.salva(self.ip).
		then(function(response){
			self.ip = null;
			}, function(errResponse){
		});
	}
	
	concatenaIp = function() {
		$scope.numero = $scope.classeIp + "." + $scope.redeIp + "." + $scope.rangeIp + ".";
		
	}
	self.altera = function(ip) {		
		ipService.altera(ip).
		then(function(response){
			self.listaIp = null;			
			self.lista();
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