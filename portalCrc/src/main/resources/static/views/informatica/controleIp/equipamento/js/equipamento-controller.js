app.controller("EquipamentoCadastarController", EquipamentoCadastarController);
app.controller("EquipamentoEditarController", EquipamentoEditarController);
app.controller("EquipamentoListarController", EquipamentoListarController);
app.controller("EquipamentoShowController", EquipamentoShowController);

EquipamentoCadastarController.$inject = ['IpService','PontoService','EquipamentoService',  'toastr', '$rootScope', '$scope'];
EquipamentoEditarController.$inject = ['$stateParams', '$state', 'EquipamentoService', 'toastr', '$rootScope', '$scope'];
EquipamentoListarController.$inject = ['$stateParams', '$state', 'EquipamentoService', 'toastr', '$rootScope', '$scope'];
EquipamentoShowController.$inject = ['$stateParams', '$state', 'EquipamentoService', 'toastr', '$rootScope', '$scope'];

function EquipamentoCadastarController(IpService, PontoService, EquipamentoService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	listaTipoEquipamentoEnum();
	listarIp();
	listarPonto();
	
	function submit(equipamento) {
		EquipamentoService.salvar(self.equipamento).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.equipamento = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
	function listaTipoEquipamentoEnum(){
			 EquipamentoService.listarTipoEquipamento().
				then(function(f){
					self.tipoEquipamentoEnum = f;			
					}, function(errResponse){
				});
			};
			
	 function listarIp(){
		 IpService.listarIpSemUso().
			then(function(f){
				self.ips = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	 function listarPonto(){
		 PontoService.listar().
			then(function(f){
				self.pontos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function EquipamentoEditarController($stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idEquipamento = $stateParams.idEquipamento;
	
	function submit(equipamento) {
		EquipamentoService.alterar(self.equipamento).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.equipamento = null;
			$state.go('equipamento.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		EquipamentoService.buscarPorId(id).
		then(function(p){
			self.equipamento = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idEquipamento){
		buscarPorId(idEquipamento);		
	};
	
	
}
function EquipamentoListarController($stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 EquipamentoService.listar().
			then(function(f){
				self.equipamentos = f;
				$rootScope.qtdEquipamentos = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function EquipamentoShowController( $stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	
}