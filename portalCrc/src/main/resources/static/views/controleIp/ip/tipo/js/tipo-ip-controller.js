app.controller("TipoIpCadastarController", TipoIpCadastarController);
app.controller("TipoIpEditarController", TipoIpEditarController);
app.controller("TipoIpListarController", TipoIpListarController);
app.controller("TipoIpShowController", TipoIpShowController);

TipoIpCadastarController.$inject = ['TipoIpService',  'toastr', '$rootScope', '$scope'];
TipoIpEditarController.$inject = ['$stateParams', '$state', 'TipoIpService', 'toastr', '$rootScope', '$scope'];
TipoIpListarController.$inject = ['$stateParams', '$state', 'TipoIpService', 'toastr', '$rootScope', '$scope'];
TipoIpShowController.$inject = ['$stateParams', '$state', 'TipoIpService', 'toastr', '$rootScope', '$scope'];

function TipoIpCadastarController( TipoIpService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	
	function submit(tipoIp) {
		TipoIpService.salvar(self.tipoIp).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.tipoIp = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
}
function TipoIpEditarController($stateParams, $state, TipoIpService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idTipoIp = $stateParams.idTipoIp;
	
	function submit(tipoIp) {
		TipoIpService.alterar(self.tipoIp).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.tipoIp = null;
			$state.go('tipoIp.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		TipoIpService.buscarPorId(id).
		then(function(p){
			self.tipoIp = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idTipoIp){
		buscarPorId(idTipoIp);		
	};
	
	
}
function TipoIpListarController($stateParams, $state, TipoIpService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 TipoIpService.listar().
			then(function(f){
				self.tipoIps = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function TipoIpShowController( $stateParams, $state, TipoIpService, toastr, $rootScope, $scope){
	
}