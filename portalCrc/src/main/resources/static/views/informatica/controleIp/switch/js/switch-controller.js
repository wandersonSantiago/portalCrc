app.controller("SwitchCadastarController", SwitchCadastarController);
app.controller("SwitchEditarController", SwitchEditarController);
app.controller("SwitchListarController", SwitchListarController);
app.controller("SwitchListarAtivosController", SwitchListarAtivosController);
app.controller("SwitchListarInativosController", SwitchListarInativosController);
app.controller("SwitchListarBaixadosController", SwitchListarBaixadosController);
app.controller("SwitchShowController", SwitchShowController);

SwitchCadastarController.$inject = ['SwitchService',  'toastr', '$rootScope', '$scope'];
SwitchEditarController.$inject = ['$stateParams', '$state', 'SwitchService', 'toastr', '$rootScope', '$scope'];
SwitchListarController.$inject = ['$stateParams', '$state', 'SwitchService', 'toastr', '$rootScope', '$scope'];
SwitchShowController.$inject = ['$stateParams', '$state', 'SwitchService', 'toastr', '$rootScope', '$scope'];

function SwitchCadastarController( SwitchService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	
	function submit() {
		SwitchService.salvar(self.switch).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.switch = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
}
function SwitchEditarController($stateParams, $state, SwitchService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idSwitch = $stateParams.idSwitch;
	
	function submit() {
		SwitchService.alterar(self.switch).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.switch = null;
			$state.go('switch.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		SwitchService.buscarPorId(id).
		then(function(p){
			self.switch = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idSwitch){
		buscarPorId(idSwitch);		
	};
	
	
}
function SwitchListarController($stateParams, $state, SwitchService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 SwitchService.listar().
			then(function(f){
				self.switchs = f;		
				$rootScope.qtdSwitch = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function SwitchListarAtivosController($stateParams, $state, SwitchService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 SwitchService.listarAtivos().
			then(function(f){
				self.switchs = f;		
				$rootScope.qtdSwitchAtivos = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function SwitchListarInativosController($stateParams, $state, SwitchService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 SwitchService.listarInativos().
			then(function(f){
				self.switchs = f;		
				$rootScope.qtdSwitchInativos = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function SwitchListarBaixadosController($stateParams, $state, SwitchService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 SwitchService.listarBaixados().
			then(function(f){
				self.switchs = f;		
				$rootScope.qtdSwitchBaixados = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function SwitchShowController( $stateParams, $state, SwitchService, toastr, $rootScope, $scope){
	var self = this;
	var idSwitch = $stateParams.idSwitch;
	
	function buscarPorId(id){
		if(!id)return;
		SwitchService.buscarPorId(id).
		then(function(p){
			self.switchs = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idSwitch){
		buscarPorId(idSwitch);		
	};
}