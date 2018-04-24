app.controller("FuncionarioUnidadeCadastarController", FuncionarioUnidadeCadastarController);
app.controller("FuncionarioUnidadeEditarController", FuncionarioUnidadeEditarController);
app.controller("FuncionarioUnidadeListarController", FuncionarioUnidadeListarController);
app.controller("FuncionarioUnidadeShowController", FuncionarioUnidadeShowController);

FuncionarioUnidadeCadastarController.$inject = ['$stateParams', '$state','FuncionarioUnidadeService', 'FuncionarioService', 'CargoService','FuncaoService', 'SetorService','toastr', '$rootScope', '$scope'];
FuncionarioUnidadeEditarController.$inject = ['$stateParams', '$state','FuncionarioUnidadeService', 'FuncionarioService', 'CargoService','FuncaoService', 'SetorService','toastr', '$rootScope', '$scope'];
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
	
	$scope.botao = "Cadastrar";
	
	function submit() {
		self.funcionarioUnidade.funcionario = self.funcionario;
		FuncionarioUnidadeService.salvar(self.funcionarioUnidade).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.funcionarioUnidade = null;
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
		SetorService.listarPorUnidade().
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
			self.funcionarioUnidade.dataCadastro = new Date(p.dataCadastro);
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
	function findAll(id){
		if(!id)return;
		FuncionarioUnidadeService.findAll(id).
		then(function(p){
			self.funcionarioUnidades = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
	if(idFuncionario){
		buscarFuncionarioPorId(idFuncionario);
		buscarPorId(idFuncionario);
		findAll(idFuncionario);
	}
	
}
function FuncionarioUnidadeEditarController( $stateParams, $state, FuncionarioUnidadeService, FuncionarioService, CargoService, FuncaoService, SetorService, toastr, $rootScope, $scope){
	var self = this;
	var idFuncionarioUnidade = $stateParams.idFuncionarioUnidade;
	self.submit = submit;
	status();
	cargos();
	funcao();
	setores();
	
	$scope.botao = "Editar";
	
	function submit() {
		self.funcionarioUnidade.funcionario = self.funcionario;
		FuncionarioUnidadeService.update(self.funcionarioUnidade).
			then(function(response){
				toastr.info('Alterado com Sucesso!!!');
				self.funcionarioUnidade = null;
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
		SetorService.listarPorUnidade().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};		
	
	function buscarPorId(id){ 
		if(!id)return;
		FuncionarioUnidadeService.buscarPorId(id).
		then(function(p){			
			self.funcionarioUnidade = p;
			self.funcionarioUnidade.dataCadastro = new Date(p.dataCadastro);
			self.funcionario = p.funcionario;
			findAll(p.funcionario.id);
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
	function findAll(id){
		if(!id)return;
		FuncionarioUnidadeService.findAll(id).
		then(function(p){
			self.funcionarioUnidades = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
	if(idFuncionarioUnidade){
		buscarPorId(idFuncionarioUnidade);
	}
	
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