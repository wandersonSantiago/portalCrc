app.controller("ListarFuncionarioDiariaController",
		ListarFuncionarioDiariaController);
app.controller("FuncionarioDiariaCadastrarController",
		FuncionarioDiariaCadastrarController);
app.controller("FuncionarioDiariaEditarController",
		FuncionarioDiariaEditarController);
app.controller("FuncionarioDiariaListarController",
		FuncionarioDiariaListarController);
app.controller("FuncionarioDiariaCoordenadoriaListarController",
		FuncionarioDiariaCoordenadoriaListarController);
app.controller("FuncionarioDiariaUnidadeListarController",
		FuncionarioDiariaUnidadeListarController);
app.controller("FuncionarioDiariaSecretariaListarController",
		FuncionarioDiariaSecretariaListarController);
app.controller("FuncionarioDiariaShowController",
		FuncionarioDiariaShowController);
app.controller("FuncionarioFinancasDiariaCadastrarController",
		FuncionarioFinancasDiariaCadastrarController);

function ListarFuncionarioDiariaController($state, $stateParams,FuncionarioDiariaService, toastr, $rootScope, $scope, $log, FuncionarioContaDiariaService) {
	
	var self = this;
	
	self.buscarPorTexto = buscarPorTexto;
	self.cadastrarDiariaFuncionario = cadastrarDiariaFuncionario;	
	var idDiaria = $stateParams.idDiaria;

	buscarFuncionarioPorDiariaPorId(idDiaria);

	function buscarPorTexto(texto){
		FuncionarioContaDiariaService.buscarPorTexto(texto).
			then(function(f){
				self.contaFuncionarioDiaria = f;
				self.funcionarioDiaria.funcionario = f.funcionario;	
				}, function(errResponse){
					sweetAlert({text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
		};
		
	
	function buscarFuncionarioPorDiariaPorId(id) {
		FuncionarioDiariaService.buscarFuncionarioPorDiariaPorId(id).then(
				function(p) {
					self.itens = p;
				}, function(errResponse) {
				});
	}
	;
	
		
	function cadastrarDiariaFuncionario(funcionario){
		idDiaria
		$rootScope.idContaFuncionario = funcionario.id;		
		$state.go('item.financasCadastrar', {idDiaria});
	}
}
function FuncionarioDiariaCadastrarController($state, ItemDiariaService,
		$stateParams, FuncionarioDiariaService, FuncionarioContaDiariaService,
		toastr, $rootScope, $scope, CoordenadoriaService, UnidadeService,
		TipoService, VeiculoService) {

	var self = this;
	var idDiaria = $stateParams.idDiaria;
	self.submit = submit;
	
		self.conta = $rootScope.usuario.funcionario.id;
	
	buscarFuncionario(self.conta);

	
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

function FuncionarioFinancasDiariaCadastrarController($state, ItemDiariaService,
		$stateParams, FuncionarioDiariaService, FuncionarioContaDiariaService,
		toastr, $rootScope, $scope, CoordenadoriaService, UnidadeService,
		TipoService, VeiculoService) {

	var self = this;
	var idDiaria = $stateParams.idDiaria;
	self.submit = submit;
	
		self.conta = $rootScope.idContaFuncionario;

	buscarFuncionario(self.conta);

	
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
function FuncionarioDiariaUnidadeListarController($stateParams, $state, FuncionarioDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	$scope.listaDiariaExcel = [];
	listar(idDiaria);

	function listar(idDiaria) {
		FuncionarioDiariaService.porUnidade(idDiaria).then(function(f) {
			self.itens = f;
			forLista(f);
		}, function(errResponse) {
			sweetAlert({
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	forLista = function(f){
		for(i = 0 ; i < f.length ; i++){
			listaExcel(f[i]);
		}
	}
	
	listaExcel = function(lista){
		$scope.listaDiariaExcel.push({
			nome : lista.funcionarioDiaria.contaFuncionario.funcionario.pessoa.nomeCompleto,
			cargo : lista.funcionarioDiaria.contaFuncionario.funcionario.cargoAtual.descricao,
			unidadeLotado :		lista.funcionarioDiaria.unidade.dadosUnidade.mnemonico,
			dataSaida: lista.dataSaida,
			dataChegada : lista.dataChegada,
			localDeslocamento: lista.localDeslocamento,
			motivo: lista.motivo,
			valorDiaria: lista.valorDiaria,
			valorPassagem: lista.valorPassagem
		});
	};
}

function FuncionarioDiariaCoordenadoriaListarController($stateParams, $state, FuncionarioDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	$scope.listaDiariaExcel = [];
	listar(idDiaria);

	function listar(idDiaria) {
		FuncionarioDiariaService.porCoordenadoria(idDiaria).then(function(f) {
			self.itens = f;
			forLista(f);
		}, function(errResponse) {
			sweetAlert({
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	forLista = function(f){
		for(i = 0 ; i < f.length ; i++){
			listaExcel(f[i]);
		}
	}
	
	listaExcel = function(lista){
		$scope.listaDiariaExcel.push({
			nome : lista.funcionarioDiaria.contaFuncionario.funcionario.pessoa.nomeCompleto,
			cargo : lista.funcionarioDiaria.contaFuncionario.funcionario.cargoAtual.descricao,
			unidadeLotado :		lista.funcionarioDiaria.unidade.dadosUnidade.mnemonico,
			dataSaida: lista.dataSaida,
			dataChegada : lista.dataChegada,
			localDeslocamento: lista.localDeslocamento,
			motivo: lista.motivo,
			valorDiaria: lista.valorDiaria,
			valorPassagem: lista.valorPassagem
		});
	};
}


function FuncionarioDiariaSecretariaListarController($stateParams, $state, FuncionarioDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	$scope.listaDiariaExcel = [];
	listar(idDiaria);

	function listar(idDiaria) {
		FuncionarioDiariaService.porSecretaria(idDiaria).then(function(f) {
			self.itens = f;
			forLista(f);
		}, function(errResponse) {
			sweetAlert({
				text : errResponse.data.message,
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	forLista = function(f){
		for(i = 0 ; i < f.length ; i++){
			listaExcel(f[i]);
		}
	}
	
	listaExcel = function(lista){
		$scope.listaDiariaExcel.push({
			nome : lista.funcionarioDiaria.contaFuncionario.funcionario.pessoa.nomeCompleto,
			cargo : lista.funcionarioDiaria.contaFuncionario.funcionario.cargoAtual.descricao,
			unidadeLotado :		lista.funcionarioDiaria.unidade.dadosUnidade.mnemonico,
			dataSaida: lista.dataSaida,
			dataChegada : lista.dataChegada,
			localDeslocamento: lista.localDeslocamento,
			motivo: lista.motivo,
			valorDiaria: lista.valorDiaria,
			valorPassagem: lista.valorPassagem
		});
	};
}

