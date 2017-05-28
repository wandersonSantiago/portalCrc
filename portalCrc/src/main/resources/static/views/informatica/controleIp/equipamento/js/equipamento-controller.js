app.controller("EquipamentoCadastarController", EquipamentoCadastarController);
app.controller("EquipamentoEditarController", EquipamentoEditarController);
app.controller("EquipamentoListarController", EquipamentoListarController);
app.controller("EquipamentoListarAtivoController", EquipamentoListarAtivoController);
app.controller("EquipamentoListarInativoController", EquipamentoListarInativoController);
app.controller("EquipamentoListarBaixadoController", EquipamentoListarBaixadoController);
app.controller("EquipamentoShowController", EquipamentoShowController);

EquipamentoCadastarController.$inject = ['IpService','PontoService','EquipamentoService',  'toastr', '$rootScope', '$scope'];
EquipamentoEditarController.$inject = ['IpService','PontoService','$stateParams', '$state', 'EquipamentoService', 'toastr', '$rootScope', '$scope'];
EquipamentoListarController.$inject = ['$stateParams', '$state', 'EquipamentoService', 'toastr', '$rootScope', '$scope'];
EquipamentoShowController.$inject = ['$stateParams', '$state', 'EquipamentoService', 'toastr', '$rootScope', '$scope'];

function EquipamentoCadastarController(IpService, PontoService, EquipamentoService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	listaTipoEquipamentoEnum();
	listarIp();
	listarPonto(false);
	
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
		 IpService.listarIpInativo().
			then(function(f){
				self.ips = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	 function listarPonto(status){
		 PontoService.listarEmUso(status).
			then(function(f){
				self.pontos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function EquipamentoEditarController(IpService, PontoService, $stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idEquipamento = $stateParams.idEquipamento;
	listaTipoEquipamentoEnum();
	listarIp();
	listarPonto(false);
	self.baixar = baixar;
	
	
	function submit(equipamento) {
		EquipamentoService.alterar(self.equipamento).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.equipamento = null;
			$state.go('equipamento.ativo');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function baixar(equipamento) {			
		swal({
			  title: 'Dar baixa neste equipamento?',
			  type: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Sim!'
			}).then(function () {
				EquipamentoService.baixar(equipamento).
				then(function(response){
					toastr.info("Equipamento baixado com Sucesso!!!");
					$state.go('equipamento.ativo');
					}, function(errResponse){
				});			  
			})		
	};	
	
	function listaTipoEquipamentoEnum(){
		 EquipamentoService.listarTipoEquipamento().
			then(function(f){
				self.tipoEquipamentoEnum = f;			
				}, function(errResponse){
			});
		};
		
	function listarIp(){
		 IpService.listarIpInativo().
			then(function(f){
				self.ips = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		 function listarPonto(status){
			 PontoService.listarEmUso(status).
				then(function(f){
					self.pontos = f;				
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
function EquipamentoListarAtivoController($stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 EquipamentoService.listarAtivos().
			then(function(f){
				self.equipamentos = f;
				$rootScope.qtdEquipamentosAtivo = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function EquipamentoListarInativoController($stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 EquipamentoService.listarInativos().
			then(function(f){
				self.equipamentos = f;
				$rootScope.qtdEquipamentosInativo = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function EquipamentoListarBaixadoController($stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 EquipamentoService.listarBaixado().
			then(function(f){
				self.equipamentos = f;
				$rootScope.qtdEquipamentosBaixado = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function EquipamentoShowController( $stateParams, $state, EquipamentoService, toastr, $rootScope, $scope){
	
}