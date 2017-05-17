app.controller("FuncionarioContaDiariaCadastrarController", FuncionarioContaDiariaCadastrarController);
app.controller("FuncionarioContaDiariaEditarController", FuncionarioContaDiariaEditarController);
app.controller("FuncionarioContaDiariaListarController", FuncionarioContaDiariaListarController);
app.controller("FuncionarioDiariaListarController", FuncionarioDiariaListarController);

FuncionarioContaDiariaCadastrarController.$inject = ['$state','FuncionarioService','$stateParams','FuncionarioContaDiariaService', 'FuncionarioService', 'toastr', '$rootScope', '$scope'];
FuncionarioContaDiariaEditarController.$inject = ['$state','FuncionarioService','$stateParams','FuncionarioContaDiariaService', 'FuncionarioService', 'toastr', '$rootScope', '$scope'];
FuncionarioContaDiariaListarController.$inject = ['$stateParams', '$state', 'FuncionarioContaDiariaService', 'toastr', '$rootScope', '$scope'];
FuncionarioDiariaListarController.$inject = ['$stateParams', '$state', 'FuncionarioService', 'toastr', '$rootScope', '$scope'];

function FuncionarioContaDiariaCadastrarController($state, FuncionarioService, $stateParams, FuncionarioContaDiariaService, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	var idFuncionario = $stateParams.idFuncionario;	
	self.submit = submit;

	
	function submit(contaFuncionarioDiaria) {
		self.contaFuncionarioDiaria.funcionario = self.funcionario;
		FuncionarioContaDiariaService.salvar(self.contaFuncionarioDiaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				$state.go('funcionarioContaDiaria.unidade');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	
		function buscarFuncionarioPorId(id){
				if(!id)return;
				FuncionarioService.buscarPorId(id).
				then(function(p){
					self.funcionario = p;
			}, function(errResponse){
				});
			};
		
			if(idFuncionario){
				buscarFuncionarioPorId(idFuncionario);				
			}
			
	
	
		
}

function FuncionarioContaDiariaEditarController($state, FuncionarioService, $stateParams, FuncionarioContaDiariaService, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	var idConta = $stateParams.idConta;	
	self.submit = submit;

	
	function submit(contaFuncionarioDiaria) {
		self.contaFuncionarioDiaria.funcionario = self.funcionario;
		FuncionarioContaDiariaService.alterar(self.contaFuncionarioDiaria).
			then(function(response){
				toastr.info("alterado com Sucesso!!!");
				$state.go('funcionarioContaDiaria.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	
		function buscarContaFuncionarioPorId(id){
				if(!id)return;
				FuncionarioContaDiariaService.buscarPorId(id).
				then(function(p){
					self.contaFuncionarioDiaria = p;
					self.funcionario = p.funcionario;
			}, function(errResponse){
				});
			};
		
			if(idConta){
				buscarContaFuncionarioPorId(idConta);				
			}
	
}
function FuncionarioContaDiariaListarController($stateParams, $state, FuncionarioContaDiariaService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 FuncionarioContaDiariaService.listarPorUnidade().
			then(function(f){
				self.contas = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function FuncionarioDiariaListarController($stateParams, $state, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 FuncionarioService.listarPorUnidade().
			then(function(f){
				self.funcionarios = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}