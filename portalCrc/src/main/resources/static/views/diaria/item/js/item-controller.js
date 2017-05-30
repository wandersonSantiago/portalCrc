app.controller("ItemDiariaCadastrarController", ItemDiariaCadastrarController);
app.controller("ItemDiariaEditarController", ItemDiariaEditarController);
app.controller("ItemDiariaListarController", ItemDiariaListarController);
app.controller("ItemDiariaShowController", ItemDiariaShowController);

/*// ItemDiariaCadastrarController.$inject = ['$stateParams','ItemDiariaService',
// 'FuncionarioService', 'toastr', '$rootScope', '$scope'];
ItemDiariaEditarController.$inject = [ '$location', '$stateParams', '$state',
		'ItemDiariaService', 'toastr', '$rootScope', '$scope' ];*/
ItemDiariaListarController.$inject = [ '$stateParams', '$state',
		'ItemDiariaService', 'toastr', '$rootScope', '$scope' ];
ItemDiariaShowController.$inject = [ '$stateParams', '$state',
		'ItemDiariaService', 'toastr', '$rootScope', '$scope' ];

function ItemDiariaCadastrarController($state, ItemDiariaService, $stateParams,
		FuncionarioDiariaService, FuncionarioContaDiariaService, toastr,
		$rootScope, $scope, CoordenadoriaService, UnidadeService, TipoService,
		VeiculoService) {

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
		self.itemDiaria.meioTransporte = self.itemDiaria.meioTransporte.placa;
		self.itemDiaria.localDeslocamento = self.itemDiaria.localDeslocamento.nome;
		self.itemDiaria.funcionario = $rootScope.usuario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.funcionario;
		ItemDiariaService.salvar(self.itemDiaria).then(
				function(response) {
					buscarItensDiariaPorFuncionarioDiaria(self.itemDiaria.funcionarioDiaria.id);
					toastr.info("Salvo com Sucesso!!!");
				}, function(errResponse) {
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});
	};
	
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {
					buscarFuncionarioDiaria(f.id);
					buscarValoresDiariaPorIndice(f.indiceUfesp, idDiaria)
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
	
	function buscarFuncionarioDiaria(id) {
		ItemDiariaService.buscarPorIdFuncionario(idDiaria, id).then(
				function(f) {
					self.funcionario = f;
					buscarItensDiariaPorFuncionarioDiaria(f.id);
				}, function(errResponse) {
					$state.go('item.cadastrarFuncionario', {idDiaria});
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});
	};
		
	function buscarItensDiariaPorFuncionarioDiaria(id) {
		ItemDiariaService.buscarItensDiariaPorFuncionarioDiaria(id).then(
				function(f) {
					self.itens = f;
				}, function(errResponse) {				
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
		UnidadeService.buscarPorCoordenadoriaPorTipo(self.coordenadoria.id,
				idTipo).then(function(p) {
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

	function buscarVeiculos(idUnidade) {
		VeiculoService.buscarPorUnidade(idUnidade).then(function(f) {
			self.veiculos = f;
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

function ItemDiariaEditarController($state, ItemDiariaService, $stateParams,
		FuncionarioDiariaService, FuncionarioContaDiariaService, toastr,
		$rootScope, $scope, CoordenadoriaService, UnidadeService, TipoService,
		VeiculoService) {

	var self = this;
	var idItem = $stateParams.idItem;
	self.buscarCidades = buscarCidades;
	self.buscarUnidades = buscarUnidades;
	self.buscarVeiculos = buscarVeiculos;
	self.submit = submit;
	buscarEstados(1);
	buscarCoordenadorias();
	buscarTipoUnidade();

	self.buscarValoresDiariaPorIndice = buscarValoresDiariaPorIndice;
	
	function submit() {
		self.itemDiaria.meioTransporte = self.itemDiaria.meioTransporte.placa;
		self.itemDiaria.localDeslocamento = self.itemDiaria.localDeslocamento.nome;
		self.itemDiaria.funcionario = $rootScope.usuario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.funcionario;
		ItemDiariaService.salvar(self.itemDiaria).then(
				function(response) {
					buscarItensDiariaPorFuncionarioDiaria(self.itemDiaria.funcionarioDiaria.id);
					toastr.info("Salvo com Sucesso!!!");
				}, function(errResponse) {
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});
	};
	
	
	
		
	function buscarItensDiariaPorFuncionarioDiaria(id) {
		ItemDiariaService.buscarItensDiariaPorFuncionarioDiaria(id).then(
				function(f) {
					self.itens = f;					
				}, function(errResponse) {				
				});
	};
	function buscarPorId(id) {
		if (!id)
			return;
		ItemDiariaService.buscarPorId(id).then(function(p) {
			self.itemDiaria = p;
			buscarValoresDiariaPorIndice(p.funcionarioDiaria.contaFuncionario.indiceUfesp , p.funcionarioDiaria.diaria.id);
			buscarItensDiariaPorFuncionarioDiaria(p.funcionarioDiaria.id);
			buscarDiariaPorId(p.funcionarioDiaria.diaria.id);
			buscarValoresDiariaPorIndice(p.funcionarioDiaria.contaFuncionario.indiceUfesp , p.funcionarioDiaria.diaria.id);
		}, function(errResponse) {
		});
	}
	;

	if (idItem) {
		buscarPorId(idItem);
	}

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
		UnidadeService.buscarPorCoordenadoriaPorTipo(self.coordenadoria.id,
				idTipo).then(function(p) {
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

	function buscarVeiculos(idUnidade) {
		VeiculoService.buscarPorUnidade(idUnidade).then(function(f) {
			self.veiculos = f;
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

	
	function excluir(objeto) {
		swal({
			title : 'Excluir diária!!!',
			text : 'Tem certeza que deseja excluir esta diária',
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Excluir'
		}).then(function() {
			ItemDiariaService.excluir(objeto.id).then(function(response) {
			}, function(errResponse) {
			});

		})
	}
	;
}
function ItemDiariaListarController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	listar();

	function listar() {
		ItemDiariaService.listarSecretaria().then(function(f) {
			self.itemDiarias = f;
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
function ItemDiariaShowController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {

}