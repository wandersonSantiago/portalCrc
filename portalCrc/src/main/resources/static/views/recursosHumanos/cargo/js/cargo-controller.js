app.controller("CargoCadastarController", CargoCadastarController);
app.controller("CargoEditarController", CargoEditarController);
app.controller("CargoListarController", CargoListarController);
app.controller("CargoShowController", CargoShowController);

CargoCadastarController.$inject = ['CargoService', 'toastr', '$rootScope', '$scope'];
CargoEditarController.$inject = ['$stateParams', '$state', 'CargoService', 'toastr', '$rootScope', '$scope'];
CargoListarController.$inject = ['CargoService', 'toastr', '$rootScope', '$scope'];
CargoShowController.$inject = ['CargoService', 'toastr', '$rootScope', '$scope'];

function CargoCadastarController( CargoService, toastr, $rootScope, $scope){
	var self = this;
	self.submit = submit;
		
	 function submit(cargo){
			CargoService.salvar(self.cargo).
			then(function(c){
				toastr.info('Salvo com Sucesso!!!');
				self.cargo = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
			});
		};
}

function CargoEditarController( $stateParams, $state, CargoService, toastr, $rootScope, $scope){
	
	var self = this;
	var idCargo = $stateParams.idCargo;
	self.submit = submit;
		
		function submit(cargo){
			CargoService.alterar(self.cargo).
			then(function(c){
				toastr.info('Alterado com Sucesso!!!');
				self.cargo = null;
				$state.go('cargo.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
			});
		};
	 
		function buscarPorId(id){
			if(!id)return;
			 CargoService.buscarPorId(id).
				then(function(c){
					self.cargo = c;
					}, function(errResponse){				
						sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
				});
			};
			if(idCargo){
				buscarPorId(idCargo);
			};
}

function CargoListarController( CargoService, toastr, $rootScope, $scope){
	var self = this;
	
	listar();
	
		function listar(){
		 CargoService.listar().
			then(function(c){
				self.cargos = c;
				}, function(errResponse){			
					sweetAlert({ timer : 3000, text : errResponse.data.message, type : "error", width: 300, higth: 100, padding: 20});	return $q.reject(errResponse);
				});
		};
}
function CargoShowController( CargoService, toastr, $rootScope, $scope){
	
}