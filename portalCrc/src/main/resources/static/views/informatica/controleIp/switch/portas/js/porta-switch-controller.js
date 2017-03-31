app.controller("PortaSwitchCadastarController", PortaSwitchCadastarController);
app.controller("PortaSwitchEditarController", PortaSwitchEditarController);
app.controller("PortaSwitchListarController", PortaSwitchListarController);
app.controller("PortaSwitchShowController", PortaSwitchShowController);

PortaSwitchCadastarController.$inject = ['PortaSwitchService',  'toastr', '$rootScope', '$scope'];
PortaSwitchEditarController.$inject = ['$stateParams', '$state', 'PortaSwitchService', 'toastr', '$rootScope', '$scope'];
PortaSwitchListarController.$inject = ['$stateParams', '$state', 'PortaSwitchService', 'toastr', '$rootScope', '$scope'];
PortaSwitchShowController.$inject = ['$stateParams', '$state', 'PortaSwitchService', 'toastr', '$rootScope', '$scope'];

function PortaSwitchCadastarController( PortaSwitchService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	
	function submit() {
		PortaSwitchService.salvar(self.portaSwitch).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.portaSwitch = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
}
function PortaSwitchEditarController($stateParams, $state, PortaSwitchService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idPortaSwitch = $stateParams.idPortaSwitch;
	
	function submit() {
		PortaSwitchService.alterar(self.portaSwitch).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.portaSwitch = null;
			$state.go('portaSwitch.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		PortaSwitchService.buscarPorId(id).
		then(function(p){
			self.portaSwitch = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idPortaSwitch){
		buscarPorId(idPortaSwitch);		
	};
	
	
}
function PortaSwitchListarController($stateParams, $state, PortaSwitchService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 PortaSwitchService.listar().
			then(function(f){
				self.portaSwitchs = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function PortaSwitchShowController( $stateParams, $state, PortaSwitchService, toastr, $rootScope, $scope){
	var self = this;
	var idPortaSwitch = $stateParams.idPortaSwitch;
	
	function buscarPorId(id){
		if(!id)return;
		PortaSwitchService.buscarPorId(id).
		then(function(p){
			self.portaSwitch = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idPortaSwitch){
		buscarPorId(idPortaSwitch);		
	};
}