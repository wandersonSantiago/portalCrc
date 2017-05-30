app.controller("ListarFuncionarioDiariaController",
		ListarFuncionarioDiariaController);
app.controller("FuncionarioDiariaCadastrarController",
		FuncionarioDiariaCadastrarController);
app.controller("FuncionarioDiariaEditarController",
		FuncionarioDiariaEditarController);
app.controller("FuncionarioDiariaListarController",
		FuncionarioDiariaListarController);
app.controller("FuncionarioDiariaShowController",
		FuncionarioDiariaShowController);

ListarFuncionarioDiariaController.$inject = [ '$stateParams',
		'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope' ];
FuncionarioDiariaCadastrarController.$inject = [ '$state', 'ItemDiariaService',
		'$stateParams', 'FuncionarioDiariaService',
		'FuncionarioContaDiariaService', 'toastr', '$rootScope', '$scope',
		'CoordenadoriaService', 'UnidadeService', 'TipoService' , 'VeiculoService'];
FuncionarioDiariaEditarController.$inject = [ '$location', '$stateParams',
		'$state', 'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope' ];
FuncionarioDiariaListarController.$inject = [ '$stateParams', '$state',
		'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope' ];
FuncionarioDiariaShowController.$inject = [ '$stateParams', '$state',
		'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope' ];

function ListarFuncionarioDiariaController($stateParams,
		FuncionarioDiariaService, toastr, $rootScope, $scope, $log) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;

	buscarFuncionarioPorDiariaPorId(idDiaria);

	function buscarFuncionarioPorDiariaPorId(id) {
		if (!id)
			return;
		FuncionarioDiariaService.buscarFuncionarioPorDiariaPorId(id).then(
				function(p) {
					self.listaDiaria = p;
				}, function(errResponse) {
				});
	}
	;

}
function FuncionarioDiariaCadastrarController($state, ItemDiariaService,
		$stateParams, FuncionarioDiariaService, FuncionarioContaDiariaService,
		toastr, $rootScope, $scope, CoordenadoriaService, UnidadeService,
		TipoService, VeiculoService) {

	var self = this;
	var idDiaria = $stateParams.idDiaria;
	self.submit = submit;
	buscarFuncionario($rootScope.usuario.funcionario.id);
	buscarFuncionario();

	
	function submit() {		
		self.funcionarioDiaria = {diaria : self.diaria, contaFuncionario : self.contaFuncionario};
		FuncionarioDiariaService.salvar(self.funcionarioDiaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");	
				$state.go('item.cadastrar', {idDiaria});
				}, function(errResponse){
					sweetAlert({text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
	function buscarDiariaPorId(id) {
		if (!id)
			return;
		ItemDiariaService.buscarDiariaPorId(id).then(function(p) {
			self.diaria = p;			
			self.diaria.dataDiaria = new Date(p.dataDiaria);		
		}, function(errResponse) {
		});
	}
	;

	if (idDiaria) {
		buscarDiariaPorId(idDiaria);
	}
	
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {
					self.contaFuncionario = f;
				}, function(errResponse) {
					
				});
	};
	
}

function FuncionarioDiariaEditarController($location, $stateParams, $state,
		FuncionarioDiariaService, toastr, $rootScope, $scope) {

	var self = this;
	self.submit = submit;
	var idItem = $stateParams.idItem;
	self.excluir = excluir;

	buscarFuncionario($rootScope.usuario.funcionario.id);
	
	function submit() {
		FuncionarioDiariaService.alterar(self.funcionarioDiaria).then(
				function(response) {
					toastr.info("Alterado com Sucesso!!!");
					$location.path('/item/' + self.funcionarioDiaria.diaria.id
							+ '/listar/unidade');
				}, function(errResponse) {
					sweetAlert({
						timer : 3000,
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});
	}
	;
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {
					self.conta = f;
				}, function(errResponse) {
					$state.go('funcionarioContaDiaria.cadastrar');
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});
	};
	
	function buscarPorId(id) {
		if (!id)
			return;
		FuncionarioDiariaService.buscarPorId(id).then(function(p) {
			self.funcionarioDiaria = p;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;

	if (idItem) {
		buscarPorId(idItem);
	}
	;

	function excluir(objeto) {
		swal({
			title : 'Excluir diária!!!',
			text : 'Tem certeza que deseja excluir esta diária',
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Excluir'
		}).then(
				function() {
					FuncionarioDiariaService.excluir(objeto.id).then(
							function(response) {
								listarUnidade(objeto.diaria.id);
							}, function(errResponse) {
							});

				})
	}
	;
	function listarUnidade(id) {
		FuncionarioDiariaService.listarUnidade(id).then(function(f) {
			self.diarias = f;
			forLista(f);

		}, function(errResponse) {
		});
	}
	;
}
function FuncionarioDiariaListarController($stateParams, $state,
		FuncionarioDiariaService, toastr, $rootScope, $scope) {
	var self = this;
	listar();

	function listar() {
		FuncionarioDiariaService.listarSecretaria().then(function(f) {
			self.funcionarioDiarias = f;
		}, function(errResponse) {
			sweetAlert({
				timer : 3000,
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
}
function FuncionarioDiariaShowController($stateParams, $state,
		FuncionarioDiariaService, toastr, $rootScope, $scope) {

}