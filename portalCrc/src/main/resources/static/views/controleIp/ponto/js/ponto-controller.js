app.controller("PontoCadastarController", PontoCadastarController);
app.controller("PontoEditarController", PontoEditarController);
app.controller("PontoListarController", PontoListarController);
app.controller("PontoShowController", PontoShowController);

PontoCadastarController.$inject = ['SetorService', 'PontoService',  'toastr', '$rootScope', '$scope'];
PontoEditarController.$inject = ['$stateParams', '$state', 'PontoService','SetorService', 'toastr', '$rootScope', '$scope'];
PontoListarController.$inject = ['$stateParams', '$state', 'PontoService', 'toastr', '$rootScope', '$scope'];
PontoShowController.$inject = ['$stateParams', '$state', 'PontoService', 'toastr', '$rootScope', '$scope'];

function PontoCadastarController(SetorService, PontoService, toastr, $rootScope, $scope){
	var self = this;
	listarSetores();
	self.submit = submit;
	
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
	
}
function PontoEditarController($stateParams, $state, PontoService, SetorService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	listarSetores();
	var idPonto = $stateParams.idPonto;
	
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
	
	
}
function PontoListarController($stateParams, $state, PontoService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 PontoService.listar().
			then(function(f){
				self.pontos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function PontoShowController( $stateParams, $state, PontoService, toastr, $rootScope, $scope){
	
}