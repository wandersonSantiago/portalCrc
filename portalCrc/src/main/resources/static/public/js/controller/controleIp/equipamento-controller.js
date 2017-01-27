app.controller('equipamentoController', function($scope,  equipamentoService,  $routeParams) {

	var self = this;
	
	var idEquipamento = $routeParams.idEquipamento;
	
self.salva = function(equipamento) {
	equipamentoService.salva(self.equipamento).
		then(function(response){
			self.equipamento = null;
			}, function(errResponse){
		});
	}
	
	self.altera = function(equipamento) {
		equipamentoService.altera(self.equipamento).
		then(function(response){
			self.equipamento = null;
			}, function(errResponse){
		});
	}

	 self.lista = function(){
		 equipamentoService.lista().
			then(function(f){
				self.listaEquipamento = f;			
				}, function(errResponse){
			});
		};
		
	self.listaTipoEquipamentoEnum= function(){
		 equipamentoService.listaTipoEquipamento().
			then(function(f){
				self.tipoEquipamentoEnum = f;			
				}, function(errResponse){
			});
		};
		
	self.buscarPorId = function(id){
			if(!id)return;
			equipamentoService.buscarPorId(id).
			then(function(p){
				self.equipamento = p;
		}, function(errResponse){
			});
		};
	
		if(idEquipamento){
			self.buscarPorId(idEquipamento);
			
		}

});