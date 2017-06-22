app.controller("FuncionarioUnidadeCadastarController", FuncionarioUnidadeCadastarController);
app.controller("FuncionarioUnidadeEditarController", FuncionarioUnidadeEditarController);
app.controller("FuncionarioUnidadeListarController", FuncionarioUnidadeListarController);
app.controller("FuncionarioUnidadeShowController", FuncionarioUnidadeShowController);

FuncionarioUnidadeCadastarController.$inject = ['$stateParams', '$state','FuncionarioUnidadeService', 'FuncionarioService', 'CargoService','FuncaoService', 'SetorService','toastr', '$rootScope', '$scope'];
FuncionarioUnidadeEditarController.$inject = ['$stateParams', '$state', 'FuncionarioUnidadeService', 'CoordenadoriaService', 'buscaCepService', 'toastr', '$rootScope', '$scope'];
FuncionarioUnidadeListarController.$inject = ['$stateParams', '$state', 'FuncionarioUnidadeService', 'toastr', '$rootScope', '$scope'];
FuncionarioUnidadeShowController.$inject = ['$stateParams', '$state', 'FuncionarioUnidadeService', 'toastr', '$rootScope', '$scope'];

function FuncionarioUnidadeCadastarController( $stateParams, $state, FuncionarioUnidadeService, FuncionarioService, CargoService, FuncaoService, SetorService, toastr, $rootScope, $scope){
	var self = this;
	var idFuncionario = $stateParams.idFuncionario;
	self.submit = submit;
	status();
	cargos();
	funcao();
	setores();
	
	function submit(funcionarioUnidade) {
		self.funcionarioUnidade.funcionario = self.funcionario;
		FuncionarioUnidadeService.salvar(self.funcionarioUnidade).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.funcionarioUnidade.funcionario = null;
				$state.go('funcionario.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	function status(){
		 FuncionarioUnidadeService.listarStatus().
			then(function(f){
				self.status = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	function cargos(){
		 CargoService.listar().
			then(function(f){
				self.cargos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	function funcao(){
		 FuncaoService.listar().
			then(function(f){
				self.funcao = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
	
	function setores(){
		SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};		
	function buscarFuncionarioPorId(id){
		if(!id)return;
		FuncionarioService.buscarPorId(id).
		then(function(p){
			self.funcionario = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	function buscarPorId(id){
		if(!id)return;
		FuncionarioUnidadeService.buscarPorIdFuncionario(id).
		then(function(p){
			self.funcionarioUnidade = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	if(idFuncionario){
		buscarFuncionarioPorId(idFuncionario);
		buscarPorId(idFuncionario);
	}
	
}
function FuncionarioUnidadeEditarController( FuncionarioUnidadeService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	
}
function FuncionarioUnidadeListarController( FuncionarioUnidadeService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	function listar(){
		FuncionarioUnidadeService.listar().
			then(function(f){
				self.funcionarios = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
		};
}
function FuncionarioUnidadeShowController( FuncionarioUnidadeService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	
}