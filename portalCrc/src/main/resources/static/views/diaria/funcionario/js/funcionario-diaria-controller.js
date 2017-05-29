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
	self.buscarCidades = buscarCidades;
	self.buscarUnidades = buscarUnidades;
	self.buscarVeiculos = buscarVeiculos;
	self.submit = submit;
	buscarEstados(1);
	buscarCoordenadorias();
	buscarTipoUnidade();

	self.buscarValoresDiariaPorIndice = buscarValoresDiariaPorIndice;
	buscarFuncionario($rootScope.usuario.funcionario.id);

	function submit() {
		
		
		
		self.itens.codigoLocalDeslocamento = self.itens.codigoLocalDeslocamento;
		self.itens.meioTransporte = self.itens.meioTransporte.placa;	
		self.itens.localDeslocamento = self.itens.localDeslocamento.nome;	
		
		self.funcionarioDiaria = {funcionario :  $rootScope.usuario.funcionario, diaria : self.diaria};
		self.funcionarioDiaria.itens = [self.itens];	
			
		FuncionarioDiariaService.salvar(self.funcionarioDiaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");					
				}, function(errResponse){
					sweetAlert({text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {
					self.contaFuncionarioDiaria = f;
					self.funcionario = f.funcionario;
					buscarValoresDiariaPorIndice(f.indiceUfesp, idDiaria)
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

	function buscarDiariaPorId(id) {
		if (!id)
			return;
		ItemDiariaService.buscarDiariaPorId(id).then(function(p) {
			self.diaria = p;
			self.diaria.dataDiaria = new Date(p.dataDiaria);
			/*self.funcionarioDiaria.diaria = {
				diaria : self.diaria
			};*/
		}, function(errResponse) {
		});
	}
	;

	if (idDiaria) {
		buscarDiariaPorId(idDiaria);
	}

	function buscarValoresDiariaPorIndice(indice, idDiaria) {
		FuncionarioDiariaService.buscarValoresDiariaPorIndice(indice, idDiaria)
				.then(function(p) {
					self.valoresDiaria = p;
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

	function buscarCoordenadorias() {
		CoordenadoriaService.listar().then(function(p) {
			self.coordenadorias = p;
		}, function(errResponse) {
		});
	}
	;
	function buscarUnidades(idTipo) {
		UnidadeService.buscarPorCoordenadoriaPorTipo(self.coordenadoria.id, idTipo)
				.then(function(p) {
					self.unidades = p;
				}, function(errResponse) {
				});
	}
	;
	function buscarTipoUnidade() {
		TipoService.listar().then(function(p) {
			self.tiposUnidade = p;
		}, function(errResponse) {
		});
	}
	;

	 function buscarVeiculos(idUnidade){
		 VeiculoService.buscarPorUnidade(idUnidade).
			then(function(f){
				self.veiculos = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		
	// inicio Função data
	$scope.saidaOpen = function() {
		$scope.saida.opened = true;
	};

	$scope.saida = {
		opened : false
	};
	$scope.chegadaOpen = function() {
		$scope.chegada.opened = true;
	};

	$scope.chegada = {
		opened : false
	};
	$scope.format = "dd/MM/yyyy";
	// termino função data

}

function FuncionarioDiariaEditarController($location, $stateParams, $state,
		FuncionarioDiariaService, toastr, $rootScope, $scope) {

	var self = this;
	self.submit = submit;
	var idItem = $stateParams.idItem;
	self.excluir = excluir;

	function submit(funcionarioDiaria) {
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