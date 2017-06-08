app.controller("ItemDiariaCadastrarController", ItemDiariaCadastrarController);
app.controller("ItemDiariaEditarController", ItemDiariaEditarController);
app.controller("ItemDiariaFinancasCadastrarController", ItemDiariaFinancasCadastrarController);
app.controller("ItemDiariaUnidadeListController", ItemDiariaUnidadeListController);
app.controller("ItemDiariaCoordenadoriaListController", ItemDiariaCoordenadoriaListController);
app.controller("ItemDiariaSecretariaListController", ItemDiariaSecretariaListController);
app.controller("ItemDiariaShowController", ItemDiariaShowController);



function ItemDiariaCadastrarController($state, ItemDiariaService, $stateParams,
		FuncionarioDiariaService, FuncionarioContaDiariaService, toastr,
		$rootScope, $scope, CoordenadoriaService, UnidadeService, TipoService,
		VeiculoService) {

	var self = this;
	var idDiaria = $stateParams.idDiaria;
	var idFuncionario;
	self.buscarCidades = buscarCidades;
	self.buscarUnidades = buscarUnidades;
	self.buscarVeiculos = buscarVeiculos;
	self.submit = submit;
	self.excluir = excluir;
	self.verificaDataFinal = verificaDataFinal;
	self.editar = true;
	buscarEstados(1);
	buscarCoordenadorias();
	buscarTipoUnidade();
	
		idFuncionario = $rootScope.usuario.funcionario.id;	
		
	
	
	self.buscarValoresDiariaPorIndice = buscarValoresDiariaPorIndice;
	buscarFuncionario(idFuncionario);
	
	
	
	
	function submit() {
		self.itemDiaria.meioTransporteSaida = self.itemDiaria.meioTransporteSaida.placa;
		self.itemDiaria.meioTransporteRetorno = self.itemDiaria.meioTransporteRetorno.placa;
		self.itemDiaria.funcionario = self.funcionario.contaFuncionario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.funcionario;
		if(self.itemDiaria.horaSaida == null || self.itemDiaria.horaChegada == null){
			sweetAlert({
				text : "os campos horários são obrigatório!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}else
		if(self.itemDiaria.motivo == null){
			sweetAlert({
				text : "o campo motivo é obrigatório!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}else{
		ItemDiariaService.salvar(self.itemDiaria).then(
				function(response) {
					buscarFuncionarioDiaria(self.funcionario.contaFuncionario.id);
					toastr.info("Salvo com Sucesso!!!");
				}, function(errResponse) {
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});}
	};
	
	
		
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {					
					buscarFuncionarioDiaria(f.id);
					buscarValoresDiariaPorIndice(f.indiceUfesp, idDiaria);
				}, function(errResponse) {
					$state.go('funcionarioContaDiaria.cadastrar', {idFuncionario});
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
					$scope.itens = f;
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
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	function verificaDataFinal(dataInicial, dataFinal){
		if(dataInicial > dataFinal){
			self.itemDiaria.dataChegada = '';
			sweetAlert({
				text : "Data Final tem que ser igual ou posterior a data Inicial!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}
			
	}
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
				buscarFuncionarioDiaria(self.funcionario.contaFuncionario.id);
			}, function(errResponse) {
			});

		})
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

function ItemDiariaFinancasCadastrarController($state, ItemDiariaService, $stateParams,
		FuncionarioDiariaService, FuncionarioContaDiariaService, toastr,
		$rootScope, $scope, CoordenadoriaService, UnidadeService, TipoService,
		VeiculoService) {

	var self = this;
	var idDiaria = $stateParams.idDiaria;
	var idFuncionario;
	self.buscarCidades = buscarCidades;
	self.buscarUnidades = buscarUnidades;
	self.buscarVeiculos = buscarVeiculos;
	self.submit = submit;
	self.excluir = excluir;
	self.verificaDataFinal = verificaDataFinal;
	self.editar = true;
	buscarEstados(1);
	buscarCoordenadorias();
	buscarTipoUnidade();
	
		
		idFuncionario = $rootScope.idContaFuncionario;
		
	self.buscarValoresDiariaPorIndice = buscarValoresDiariaPorIndice;
	buscarFuncionario(idFuncionario);
	
	
	
	
	function submit() {
		self.itemDiaria.meioTransporteSaida = self.itemDiaria.meioTransporteSaida.placa;
		self.itemDiaria.meioTransporteRetorno = self.itemDiaria.meioTransporteRetorno.placa;
		self.itemDiaria.funcionario = self.funcionario.contaFuncionario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.funcionario;
		if(self.itemDiaria.horaSaida == null || self.itemDiaria.horaChegada == null){
			sweetAlert({
				text : "os campos horários são obrigatório!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}else
		if(self.itemDiaria.motivo == null){
			sweetAlert({
				text : "o campo motivo é obrigatório!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}else{
		ItemDiariaService.salvar(self.itemDiaria).then(
				function(response) {
					buscarFuncionarioDiaria(self.funcionario.contaFuncionario.id);
					toastr.info("Salvo com Sucesso!!!");
				}, function(errResponse) {
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});}
	};
	
	
		
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {					
					buscarFuncionarioDiaria(f.id);
					buscarValoresDiariaPorIndice(f.indiceUfesp, idDiaria);
				}, function(errResponse) {
					$state.go('funcionarioContaDiaria.cadastrar', {idFuncionario});
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
					$scope.itens = f;
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
				text : errResponse.data.message,
				type : "error",
				width : 300,
				higth : 300,
				padding : 20
			});
		});
	}
	;
	
	function verificaDataFinal(dataInicial, dataFinal){
		if(dataInicial > dataFinal){
			self.itemDiaria.dataChegada = '';
			sweetAlert({
				text : "Data Final tem que ser igual ou posterior a data Inicial!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}
			
	}
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
				buscarFuncionarioDiaria(self.funcionario.contaFuncionario.id);
			}, function(errResponse) {
			});

		})
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
		self.itemDiaria.meioTransporteSaida = self.itemDiaria.meioTransporteSaida.placa;
		self.itemDiaria.meioTransporteRetorno = self.itemDiaria.meioTransporteRetorno.placa;
		self.itemDiaria.funcionario = $rootScope.usuario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.itemDiaria.funcionarioDiaria;
		ItemDiariaService.alterar(self.itemDiaria).then(
				function(response) {
					var idDiaria = self.diaria.id;;
					$state.go('item.cadastrar', {idDiaria});
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
					$scope.itens = f;					
				}, function(errResponse) {				
				});
	};
	function buscarPorId(id) {
		if (!id)
			return;
		ItemDiariaService.buscarPorId(id).then(function(p) {
			self.itemDiaria = p;
			self.itemDiaria.dataSaida = new Date(p.dataSaida);
			self.itemDiaria.dataChegada = new Date(p.dataChegada);
			buscarValoresDiariaPorIndice(p.funcionarioDiaria.contaFuncionario.indiceUfesp , p.funcionarioDiaria.diaria.id);
			buscarItensDiariaPorFuncionarioDiaria(p.funcionarioDiaria.id);
			buscarDiariaPorId(p.funcionarioDiaria.diaria.id);
			buscarValoresDiariaPorIndice(p.funcionarioDiaria.contaFuncionario.indiceUfesp , p.funcionarioDiaria.diaria.id);
			self.editar = false;
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
function ItemDiariaUnidadeListController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	$scope.listaDiariaExcel = [];
	listar(idDiaria);

	function listar(idDiaria) {
		ItemDiariaService.porUnidades(idDiaria).then(function(f) {
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
			localDeslocamento: lista.localDeslocamento.nome,
			motivo: lista.motivo,
			valorDiaria: lista.valorDiaria,
			valorPassagem: lista.valorPassagem
		});
	};
}


function ItemDiariaCoordenadoriaListController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	$scope.listaDiariaExcel = [];
	listar(idDiaria);

	function listar(idDiaria) {
		ItemDiariaService.porCoordenadoria(idDiaria).then(function(f) {
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
			localDeslocamento: lista.localDeslocamento.nome,
			motivo: lista.motivo,
			valorDiaria: lista.valorDiaria,
			valorPassagem: lista.valorPassagem
		});
	};
}

function ItemDiariaSecretariaListController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	$scope.listaDiariaExcel = [];
	listar(idDiaria);

	function listar(idDiaria) {
		ItemDiariaService.porSecretaria(idDiaria).then(function(f) {
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
			localDeslocamento: lista.localDeslocamento.nome,
			motivo: lista.motivo,
			valorDiaria: lista.valorDiaria,
			valorPassagem: lista.valorPassagem
		});
	};
}


function ItemDiariaShowController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idFuncionario = $stateParams.idFuncionarioDiaria;
	
	buscarItensDiariaPorFuncionarioDiaria(idFuncionario);
	
	function buscarItensDiariaPorFuncionarioDiaria(id) {
		ItemDiariaService.buscarItensDiariaPorFuncionarioDiaria(id).then(
				function(f) {
					$scope.itens = f;
					for(i = 0 ; i < $scope.itens.length ; i++){
						$scope.funcionario = $scope.itens[i].funcionarioDiaria;
					}
				}, function(errResponse) {				
				});
	};
}