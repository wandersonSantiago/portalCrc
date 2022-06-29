app.controller("EmprestimoCadastrarController", EmprestimoCadastrarController);
app.controller("EmprestimoEditarController", EmprestimoEditarController);
app.controller("EmprestimoListarController", EmprestimoListarController);
app.controller("EmprestimoShowController", EmprestimoShowController);

app.controller("EmprestimoListarAlterarStatusController", EmprestimoListarAlterarStatusController);

EmprestimoCadastrarController.$inject = [ 'EmprestimoService','EquipamentoService', 'FuncionarioUnidadeService','FuncionarioService', 'toastr', '$rootScope', '$scope','UnidadeService' ];
EmprestimoEditarController.$inject = [ 'EmprestimoService','EquipamentoService', 'FuncionarioUnidadeService','FuncionarioService', 'toastr', '$rootScope', '$scope','UnidadeService', '$stateParams', '$state' ];
EmprestimoListarController.$inject = [ 'EmprestimoService','EquipamentoService', 'FuncionarioService', 'toastr', '$rootScope','$scope', 'blockUI', 'UnidadeService' ];
EmprestimoShowController.$inject = [ '$stateParams', '$state','EquipamentoService', 'FuncionarioService', 'toastr', '$rootScope','$scope', 'UnidadeService' ];

function EmprestimoCadastrarController(EmprestimoService, EquipamentoService, FuncionarioUnidadeService, FuncionarioService, toastr, $rootScope, $scope, UnidadeService) {
	var self = this;
	self.submit = submit;
	//self.equipamento = equipamento; estava errado aqui
	$scope.equipamentos = [];
	self.emprestimo = {
		equipamento : $scope.equipamentos
	}
	
	self.buscarPorTipo = buscarPorTipo;
	status();
	listarUnidades();
	listaTipoEquipamentoEnum();
	//equipamentosList();	
	self.addEquipamento = addEquipamento;
	self.removerequipamento = removerequipamento;
	self.funcionarios = funcionarios;
	
	
	function submit() {

		EmprestimoService.salvar(self.emprestimo).then(function(response) {
			toastr.info('Salvo com Sucesso!!!');
			self.emprestimo = null;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	function addEquipamento(equipamento) {
		$scope.equipamentos.push(equipamento);
	}
	
	
	function removerequipamento(lista){		
		var indice = $scope.equipamentos.indexOf(lista);		
		$scope.equipamentos.splice(indice,1)
	}	
	
	

	function listaTipoEquipamentoEnum() {
		EquipamentoService.listarTipoEquipamento().then(function(f) {
			self.tiposEquipamentoEnum = f;
		}, function(errResponse) {
		});
	}
	;

	function equipamentosList() {
		EquipamentoService.listar().then(function(f) {
			self.equipamentos = f;
		}, function(errResponse) {
		});
	}
	;

	
	function listarUnidades() {
		UnidadeService.listar().then(function(f) {
			self.unidades = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
			
	function buscarPorTipo(tipo){
		 EquipamentoService.buscarPorTipo(tipo).
			then(function(f){					
				self.equipamentos = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
	
	function status() {
		EmprestimoService.status().then(function(f) {
			self.statusEmprestimo = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	
	
	function funcionarios(texto){
	 	return  FuncionarioService.buscarPorTexto(texto).
	 	 then(function(e){
	 		return e.content;
	 	 }, function(errResponse){
	 	 });
	 }
	
	
}
function EmprestimoEditarController(EmprestimoService, EquipamentoService,
		FuncionarioUnidadeService, FuncionarioService, toastr, $rootScope,
		$scope, UnidadeService, $stateParams, $state) {

	var self = this;
	self.submit = submit;
	var idEmprestimo = $stateParams.idEmprestimo;
	$scope.equipamentos = [];
	self.emprestimo = {
		equipamento : $scope.equipamentos
	}
	
	
		
	self.emprestimo.equipamentos = $scope.equipamentos;
	
	
	
	status();
	listarUnidades();
	listaTipoEquipamentoEnum();
	funcionario();
	equipamentosList();

	self.addEquipamento = addEquipamento;
	self.removerequipamento = removerequipamento;

	/* alterado     */

	function submit(emprestimo) {
		self.emprestimo.equipamento = $scope.equipamentos;
		EmprestimoService.alterar(self.emprestimo).then(function(response) {
			toastr.info('Alterado com sucesso!!!');
			self.emprestimo = null;
			$state.go('emprestimo.listar');
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	; 
	
	
	//alterado aqui

	function addEquipamento(equipamento) {
		$scope.equipamentos.push(equipamento);
	}
	
	
	function removerequipamento(lista){		
		var indice = $scope.equipamentos.indexOf(lista);		
		$scope.equipamentos.splice(indice,1)
	}
	
	function buscarPorId(id) {
		if (!id)
			return;
		EmprestimoService.buscarPorId(id).then(function(p) {

			self.emprestimo = p;
			self.emprestimo.dataEmprestimo = new Date(p.dataEmprestimo);
			self.emprestimo.dataDevolucao = new Date(p.dataDevolucao);
			$scope.equipamentos = p.equipamento;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	if (idEmprestimo) {
		buscarPorId(idEmprestimo);
	}

	function listarUnidades() {
		UnidadeService.listar().then(function(f) {
			self.unidades = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	function status() {
		EmprestimoService.status().then(function(f) {
			self.statusEmprestimo = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	function funcionario() {
		FuncionarioService.listarPorUnidade().then(function(f) {
			self.funcionarios = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	function listaTipoEquipamentoEnum() {
		EquipamentoService.listarTipoEquipamento().then(function(f) {
			self.tiposEquipamentoEnum = f;
		}, function(errResponse) {
		});
	}
	;

	function equipamentosList() {
		EquipamentoService.listar().then(function(f) {
			self.equipamentos = f;
		}, function(errResponse) {
		});
	}
	;

}
function EmprestimoListarController(EmprestimoService, EquipamentoService, FuncionarioUnidadeService, FuncionarioService, toastr, $rootScope,
		blockUI, $scope, UnidadeService) {
	var self = this;
	$scope.equipamentos = [];
	self.emprestimo = {
		equipamento : $scope.equipamentos
	}
	status();
	self.alterar = alterar;

	

	function equipamentosList() {
		EquipamentoService.listar().then(function(f) {
			self.equipamentos = f;
		}, function(errResponse) {
		});
	}
	;

	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;

	buscarPorTexto('');

	function buscarPorTexto(texto) {
		$scope.mensagemErro = null;
		blockUI.start();
		self.paginaCorrente == '0' ? self.paginaCorrente = 0
				: self.paginaCorrente = self.paginaCorrente - 1;
		EmprestimoService.buscarPorTexto(texto, self.paginaCorrente).then(
				function(e) {
					$scope.mensagemErro = null;
					self.emprestimos = e.content;
					self.totalElementos = e.totalElements;
					self.totalPaginas = e.totalPages;
					self.paginaCorrente = e.number + 1;
					blockUI.stop();
				}, function(errResponse) {
					blockUI.stop();
					if (errResponse.status == 404) {
						$scope.mensagemErro = errResponse.data.message;
					} else {
						$scope.mensagemErro = errResponse.data.message;
					}
				});
	}
	
	
	
	
	
	
	
	
	

	function status() {
		EmprestimoService.status().then(function(f) {
			self.statusEmprestimo = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	//Funçao para alterar status do objeto emprestimo 
	function alterar(emprestimo) {
		EmprestimoService.alterarStatus(emprestimo).then(function(f) {
			buscarPorTexto('');
			sweetAlert({
				timer : 30000,
				text : "Status Atualizado com Sucesso!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	
}


function EmprestimoShowController($stateParams, $state, EmprestimoService,
		toastr, $rootScope, $scope) {

}