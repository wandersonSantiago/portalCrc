app.controller("VeiculoCadastarController", VeiculoCadastarController);
app.controller("VeiculoEditarController", VeiculoEditarController);
app.controller("VeiculoListarController", VeiculoListarController);
app.controller("VeiculoShowController", VeiculoShowController);

VeiculoCadastarController.$inject = ['VeiculoService', 'toastr', '$rootScope', '$scope', 'FuncionarioDiariaService'];
VeiculoEditarController.$inject = ['VeiculoService', 'toastr', '$rootScope', '$scope', 'FuncionarioDiariaService','$stateParams'];
VeiculoListarController.$inject = ['$stateParams', '$state', 'VeiculoService', 'toastr', '$rootScope', '$scope'];
VeiculoShowController.$inject = ['$stateParams', '$state', 'VeiculoService', 'toastr', '$rootScope', '$scope'];

function VeiculoCadastarController( VeiculoService,  toastr, $rootScope, $scope, FuncionarioDiariaService){
	
	var self = this;
	self.submit = submit;
	
	buscarEstados(33);
	buscarMarcas();
	buscarCores();
	self.buscarCidades = buscarCidades;
	self.buscarModelos =buscarModelos;
	
	
	function submit(veiculo) {
		VeiculoService.salvar(self.veiculo).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.veiculo = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		function buscarMarcas() {
			VeiculoService.marcas().then(function(p) {
				self.marcas = p;
			}, function(errResponse) {
			});
		}
		;
		
		function buscarCores() {
			VeiculoService.cores().then(function(p) {
				self.cores = p;
			}, function(errResponse) {
			});
		}
		;
		function buscarModelos(idMarca) {
			VeiculoService.modelos(idMarca).then(function(p) {
				self.modelos = p;
			}, function(errResponse) {
			});
		}
		;
	
		function buscarEstados(idPais) {
			FuncionarioDiariaService.estados(idPais).then(function(p) {
				self.estados = p;
			}, function(errResponse) {
			});
		}
		;

		function buscarCidades(idEstado) {
			FuncionarioDiariaService.cidades(idEstado).then(function(p) {
				self.cidades = p;
			}, function(errResponse) {
			});
		}
		;
}
function VeiculoEditarController(VeiculoService,  toastr, $rootScope, $scope, FuncionarioDiariaService, $stateParams){
	
	var self =this;
	var idVeiculo = $stateParams.idVeiculo;
	self.submit = submit;
	
	buscarEstados(33);	
	buscarCores();
	self.buscarCidades = buscarCidades;
	self.buscarModelos =buscarModelos;
	
	
	function submit(veiculo) {
		VeiculoService.salvar(self.veiculo).
			then(function(response){
				toastr.info("Alterado com Sucesso!!!");
				self.veiculo = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		function buscarMarcas() {
			VeiculoService.marcas().then(function(p) {
				self.marcas = p;
				for(i = 0; i < self.marcas.length ; i++){
					if(self.marcas[i].nome == self.veiculo.modelo.marca.nome){
						$scope.marca = self.marcas[i];
						buscarModelos($scope.marca.id);
					}
				}
			}, function(errResponse) {
			});
		}
		;
		
		function buscarCores() {
			VeiculoService.cores().then(function(p) {
				self.cores = p;
			}, function(errResponse) {
			});
		}
		;
		function buscarModelos(idMarca) {
			VeiculoService.modelos(idMarca).then(function(p) {
				self.modelos = p;
			}, function(errResponse) {
			});
		}
		;
	
		function buscarEstados(idPais) {
			FuncionarioDiariaService.estados(idPais).then(function(p) {
				self.estados = p;
				for(i = 0; i < self.estados.length ; i++){
					if(self.estados[i].nome == self.veiculo.cidade.estado.nome){
						$scope.estado = self.estados[i];
						buscarCidades($scope.estado.id);
					}
				}
			}, function(errResponse) {
			});
		}
		;

		function buscarCidades(idEstado) {
			FuncionarioDiariaService.cidades(idEstado).then(function(p) {
				self.cidades = p;
			}, function(errResponse) {
			});
		}
		;
		
	function buscarPorId(id){
			if(!id)return;
			VeiculoService.buscarPorId(id).
			then(function(p){
				self.veiculo = p;
				buscarMarcas();		
				buscarEstados(33);
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
		if(idVeiculo){
			buscarPorId(idVeiculo);			
		}		
}
function VeiculoListarController($stateParams, $state, VeiculoService, toastr, $rootScope, $scope){
	var self = this;
	
	listar();
	
	 function listar(){
		 VeiculoService.listar().
			then(function(f){
				self.veiculos = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function VeiculoShowController( $stateParams, $state, VeiculoService, toastr, $rootScope, $scope){
	
}