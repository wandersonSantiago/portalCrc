app.controller("PontoCadastarController", PontoCadastarController);
app.controller("PontoEditarController", PontoEditarController);
app.controller("PontoListarController", PontoListarController);
app.controller("PontoShowController", PontoShowController);

PontoCadastarController.$inject = [	'PortaSwitchService','SwitchService','SetorService', 'PontoService',  'toastr', '$rootScope', '$scope'];
PontoEditarController.$inject = ['PortaSwitchService','SwitchService','$stateParams', '$state', 'PontoService','SetorService', 'toastr', '$rootScope', '$scope'];
PontoListarController.$inject = ['PortaSwitchService','$stateParams', '$state', 'PontoService', 'toastr', '$rootScope', '$scope'];
PontoShowController.$inject = ['$stateParams', '$state', 'PontoService', 'toastr', '$rootScope', '$scope'];

function PontoCadastarController(PortaSwitchService, SwitchService, SetorService, PontoService, toastr, $rootScope, $scope){
	var self = this;
	listarSetores();
	listarSwitch();
	self.submit = submit;
	self.listarPortasSwitch = listarPortasSwitch;
	
	function submit(ponto) {
		PontoService.salvar(self.ponto).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.ponto = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};		
		
	
	 function listarSetores(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function listarSwitch(){
		 SwitchService.listar().
			then(function(f){
				self.switchs = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};		
		
	function listarPortasSwitch(idSwitch){
		if(idSwitch){
			PortaSwitchService.listarPortasLivres(idSwitch).
			then(function(f){
				$scope.portasLivres = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		}		
		else {
			console.log("teste");
			self.ponto.portaSwitch = null;
		}		
	};
}
function PontoEditarController(PortaSwitchService, SwitchService, $stateParams, $state, PontoService, SetorService, toastr, $rootScope, $scope){
	
	var self = this;
	var idPonto = $stateParams.idPonto;
	self.submit = submit;
	listarSetores();
	listarSwitch();
	self.listarPortasSwitch = listarPortasSwitch;
	
	function submit(ponto) {
		PontoService.alterar(self.ponto).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.ponto = null;
			$state.go('ponto.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		PontoService.buscarPorId(id).
		then(function(p){
			self.ponto = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idPonto){
		buscarPorId(idPonto);		
	};
	

	 function listarSetores(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	function listarPortasSwitch(idSwitch){
		PortaSwitchService.listarPortasLivres(idSwitch).
			then(function(f){
				$scope.portasLivres  = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};	
	function listarSwitch(){
		 SwitchService.listar().
			then(function(f){
				self.switchs = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};		
	
}
function PontoListarController(PortaSwitchService, $stateParams, $state, PontoService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	listarPortas();
	self.limparPortas = limparPortas;
	
	 function listar(){
		 PontoService.listar().
			then(function(f){
				self.pontos = f;	
				$rootScope.qtdPonto = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	 function listarPortas(){
		 PortaSwitchService.listar().
			then(function(f){
				self.portaSwitchs = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		
			
		function limparPortas(ponto) {			
			swal({
				  title: 'Desvincular ponto do switch!!!',
				  type: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Sim!'
				}).then(function () {
					ponto.portaSwitch =null;
					ponto.switchs =null;
					PontoService.salvar(ponto).
					then(function(response){
						self.ponto = null;
						}, function(errResponse){
					});			  
				})		
		};
}
function PontoShowController( $stateParams, $state, PontoService, toastr, $rootScope, $scope){
	
}