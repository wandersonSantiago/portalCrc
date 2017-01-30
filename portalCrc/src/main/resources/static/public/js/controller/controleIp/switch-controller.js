app.controller('switchController', function($scope,  switchService,  $routeParams) {

	var self = this;
	
	var idSwitch = $routeParams.idSwitch;
	
self.salva = function(Switch) {
	switchService.salva(self.switch).
		then(function(response){
			self.switch = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(Switch) {
		switchService.altera(self.switch).
		then(function(response){
			self.switch = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 switchService.lista().
			then(function(f){
				self.listaSwitch = f;			
				}, function(errResponse){
			});
		};
		
		
	self.buscarPorId = function(id){
		
			if(!id)return;
			switchService.buscarPorId(id).
			then(function(p){
				self.switch = p;
		}, function(errResponse){
			});
		};
	
		if(idSwitch){
			self.buscarPorId(idSwitch);
			
		}

});