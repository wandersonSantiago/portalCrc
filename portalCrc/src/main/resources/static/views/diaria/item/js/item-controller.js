app.controller("ItemDiariaCadastrarController", ItemDiariaCadastrarController);
app.controller("ItemDiariaEditarController", ItemDiariaEditarController);
app.controller("ItemDiariaUnidadeListController", ItemDiariaUnidadeListController);
app.controller("ItemDiariaCoordenadoriaListController", ItemDiariaCoordenadoriaListController);
app.controller("ItemDiariaSecretariaListController", ItemDiariaSecretariaListController);
app.controller("ItemDiariaShowController", ItemDiariaShowController);
app.controller("ItemDiariaUsuarioController", ItemDiariaUsuarioController);


function ItemDiariaCadastrarController($localStorage, $state, ItemDiariaService, $stateParams,	FuncionarioDiariaService, FuncionarioContaDiariaService, toastr,$rootScope, $scope, CoordenadoriaService, UnidadeService, TipoService,	VeiculoService) {

	var self = this;	
	self.buscarCidades = buscarCidades;
	self.buscarUnidades = buscarUnidades;
	self.buscarVeiculos = buscarVeiculos;
	self.submit = submit;
	self.excluir = excluir;
	self.verificaDataFinal = verificaDataFinal;
	self.editar = true;
	self.adicionarLocal = adicionarLocal;
	self.removerLocal = removerLocal;
	buscarEstados(33);
	buscarCoordenadorias();
	buscarTipoUnidade();
	tipos();
	$scope.botao = "Salvar";
	$scope.localDeslocamentos = [];
	$scope.retorno = false;
	
	if($stateParams.idDiaria){
		$localStorage.idDiaria = $stateParams.idDiaria;
	}
	if($stateParams.idFuncionario){
		$localStorage.idFuncionario = $stateParams.idFuncionario;
	}
		
				
		
	
	
	self.buscarValoresDiariaPorIndice = buscarValoresDiariaPorIndice;
	buscarFuncionarioDiaria($localStorage.idFuncionario);
	
	
	
	
	function submit() {
		self.itemDiaria.meioTransporteSaida = self.itemDiaria.meioTransporteSaida.placa;
		self.itemDiaria.meioTransporteRetorno = self.itemDiaria.meioTransporteRetorno.placa;
		self.itemDiaria.funcionario = self.funcionario.contaFuncionario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.funcionario;
		self.itemDiaria.localDeslocamentos = $scope.localDeslocamentos;
		if(self.itemDiaria.horaSaida == null || self.itemDiaria.horaChegada == null){
			self.itemDiaria.meioTransporteRetorno = {placa : self.itemDiaria.meioTransporteRetorno};
			self.itemDiaria.meioTransporteSaida = {placa : self.itemDiaria.meioTransporteSaida};
			sweetAlert({
				text : "os campos horários são obrigatório!!!",
				type : "info",
				width : 300,
				higth : 300,
				padding : 20
			});
		}else
		if(self.itemDiaria.motivo == null){
			self.itemDiaria.meioTransporteRetorno = {placa : self.itemDiaria.meioTransporteRetorno};
			self.itemDiaria.meioTransporteSaida = {placa : self.itemDiaria.meioTransporteSaida};
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
					buscarItensDiariaPorFuncionarioDiaria(self.funcionario.id);
					buscarFuncionarioDiaria($localStorage.idFuncionario);
					toastr.info("Salvo com Sucesso!!!");
					self.itemDiaria.meioTransporteRetorno = {placa : self.itemDiaria.meioTransporteRetorno};
					self.itemDiaria.meioTransporteSaida = {placa : self.itemDiaria.meioTransporteSaida};
					self.itemDiaria.dataSaida = "";
					self.itemDiaria.dataChegada = "";
				}, function(errResponse) {
					self.itemDiaria.meioTransporteRetorno = {placa : self.itemDiaria.meioTransporteRetorno};
					self.itemDiaria.meioTransporteSaida = {placa : self.itemDiaria.meioTransporteSaida};
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});}
	};
	
	
	function adicionarLocal(cidade){
		if(!cidade){
			return
		}
		$scope.localDeslocamentos.push(cidade)
	}
	
	function removerLocal(local){
		
		var indice = $scope.localDeslocamentos.indexOf(local);
		
		$scope.localDeslocamentos.splice(indice,1)
	}
		
	function buscarFuncionario(id) {
		FuncionarioContaDiariaService.buscarPorIdFuncionario(id).then(
				function(f) {					
					buscarFuncionarioDiariaPorConta(f.id);
					buscarValoresDiariaPorIndice(f.indiceUfesp, $localStorage.idDiaria);
				}, function(errResponse) {
					var idFuncionario = $localStorage.idFuncionario;
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
	
	function tipos() {
		ItemDiariaService.tipos().then(
				function(f) {					
					self.tipos = f;
				}, function(errResponse) {
					
				});
	};
	
	function buscarFuncionarioDiariaPorConta(id) {
		ItemDiariaService.buscarFuncionarioDiariaPorConta($localStorage.idDiaria, id).then(
				function(f) {
					self.funcionario = f;
					self.coordenadoria = self.funcionario.contaFuncionario.funcionario.unidadeAtual.coordenadoria;
					self.tipo = self.funcionario.contaFuncionario.funcionario.unidadeAtual.tipoUnidade;
					self.unidade = self.funcionario.contaFuncionario.funcionario.unidadeAtual;
					buscarUnidades(self.tipo.id);
					buscarVeiculos(self.unidade.id);
					buscarItensDiariaPorFuncionarioDiaria(f.id);
				}, function(errResponse) {
					var idDiaria	= $localStorage.idDiaria;	
					var idFuncionario = $localStorage.idFuncionario;
					$state.go('item.cadastrarFuncionario', {idDiaria , idFuncionario});
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
		ItemDiariaService.buscarPorIdFuncionario($localStorage.idDiaria, id).then(
				function(f) {
					self.funcionario = f;
					self.coordenadoria = self.funcionario.contaFuncionario.funcionario.unidadeAtual.coordenadoria;
					self.tipo = self.funcionario.contaFuncionario.funcionario.unidadeAtual.tipoUnidade;
					self.unidade = self.funcionario.contaFuncionario.funcionario.unidadeAtual;
					buscarUnidades(self.tipo.id);
					buscarVeiculos(self.unidade.id);
					buscarItensDiariaPorFuncionarioDiaria(f.id);		
					buscarValoresDiariaPorIndice(f.contaFuncionario.indiceUfesp, $localStorage.idDiaria);
				}, function(errResponse) {
					var idDiaria	= $localStorage.idDiaria;	
					var idFuncionario = $localStorage.idFuncionario;
					$state.go('item.cadastrarFuncionario', {idDiaria , idFuncionario});
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

	if ($localStorage.idDiaria) {
		buscarDiariaPorId($localStorage.idDiaria);
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
				buscarFuncionarioDiaria($localStorage.idFuncionario);
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
	$scope.botao = "Alterar";
	self.buscarCidades = buscarCidades;
	self.buscarUnidades = buscarUnidades;
	self.buscarVeiculos = buscarVeiculos;
	self.adicionarLocal = adicionarLocal;
	self.removerLocal = removerLocal;
	self.submit = submit;
	self.excluir = excluir;
	buscarCoordenadorias();
	buscarTipoUnidade();
	tipos();
	self.proximaPagina = proximaPagina;
	$scope.localDeslocamentos = [];
	
	
					
	function proximaPagina(item) {
		self.myVar = setInterval(function(){ editar(item) }, 500);			
	};
	
	function editar(item){
		$state.go('item.editar' , {idItem : item.id});
		clearInterval(self.myVar);
	}
	
	function submit() {
		self.itemDiaria.meioTransporteSaida = self.itemDiaria.meioTransporteSaida.placa;
		self.itemDiaria.meioTransporteRetorno = self.itemDiaria.meioTransporteRetorno.placa;
		self.itemDiaria.localDeslocamentos = $scope.localDeslocamentos;
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
		ItemDiariaService.alterar(self.itemDiaria).then(
				function(response) {
					self.itemDiaria.meioTransporteRetorno = {placa : self.itemDiaria.meioTransporteRetorno};
					self.itemDiaria.meioTransporteSaida = {placa : self.itemDiaria.meioTransporteSaida};					
					toastr.info("Alterado com Sucesso!!!");	
					$state.go('diaria.listar');
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
		
	function adicionarLocal(cidade){
		if(!cidade){
			return
		}
		$scope.localDeslocamentos.push(cidade)
	}
	
	function removerLocal(local){
		
		var indice = $scope.localDeslocamentos.indexOf(local);
		
		$scope.localDeslocamentos.splice(indice,1)
	}
	
	function buscarPorId(id) {
		if (!id)
			return;
		ItemDiariaService.buscarPorId(id).then(function(p) {
			self.itemDiaria = p;
			self.itemDiaria.meioTransporteRetorno = {placa : self.itemDiaria.meioTransporteRetorno};
			self.itemDiaria.meioTransporteSaida = {placa : self.itemDiaria.meioTransporteSaida};
			self.funcionario = self.itemDiaria.funcionarioDiaria;
			self.diaria = self.itemDiaria.funcionarioDiaria.diaria;
			buscarValoresDiariaPorIndice(self.funcionario.contaFuncionario.indiceUfesp, self.diaria.id);	
			buscarItensDiariaPorFuncionarioDiaria(self.funcionario.id);
			buscarEstados(33);
			self.coordenadoria = self.funcionario.contaFuncionario.funcionario.unidadeAtual.coordenadoria;
			self.tipo = self.funcionario.contaFuncionario.funcionario.unidadeAtual.tipoUnidade;
			self.unidade = self.funcionario.contaFuncionario.funcionario.unidadeAtual;
			buscarUnidades(self.tipo.id);
			buscarVeiculos(self.unidade.id);
			$scope.localDeslocamentos = self.itemDiaria.localDeslocamentos;
		}, function(errResponse) {
		});
	};

	if (idItem) {
		buscarPorId(idItem);
	}


	function buscarValoresDiariaPorIndice(indice, idDiaria) {
		FuncionarioDiariaService.buscarValoresDiariaPorIndice(indice, idDiaria)
				.then(function(p) {
					self.valoresDiaria = p;
				}, function(errResponse) {
				});
	}
	;
	function buscarItensDiariaPorFuncionarioDiaria(id) {
		ItemDiariaService.buscarItensDiariaPorFuncionarioDiaria(id).then(
				function(f) {
					$scope.itens = f;
				}, function(errResponse) {				
				});
	};
	
	function tipos() {
		ItemDiariaService.tipos().then(
				function(f) {					
					self.tipos = f;
				}, function(errResponse) {
					
				});
	};

	function buscarEstados(idPais) {
		FuncionarioDiariaService.estados(idPais).then(function(p) {
			self.estados = p;
			$scope.cidade = self.itemDiaria.localDeslocamento;
			for(i = 0; i < self.estados.length ; i++){
				if(self.estados[i].nome == $scope.cidade.estado.nome){
					self.itemDiaria.estado = self.estados[i];
					buscarCidades(self.itemDiaria.estado.id);
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
				$state.go('diaria.listar');
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
function ItemDiariaUnidadeListController($filter, $stateParams, $state, ItemDiariaService,
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
		var cidade = '';
		for(c = 0 ; c < lista.localDeslocamentos.length ; c++){			
			cidade += lista.localDeslocamentos[c].nome;
			
			if(c +1 < lista.localDeslocamentos.length){
				cidade += " , ";
			}
		}
		var motivo = lista.motivo;
		if(lista.dataSaida < lista.dataChegada){
			lista.dataChegada  = $filter('date')(lista.dataChegada, "dd/MM/yyyy"); 
			motivo += " - retorno no dia " + lista.dataChegada;
		}
		if(lista.valorPassagem == 0){
			var valorPassagem = 0.00;
		}
		if(lista.valorDiaria){
			var valorDiaria =  lista.valorDiaria.toString().replace(".", ",");			
		}
		if(lista.valorPassagem){
			var valorPassagem = lista.valorPassagem.toString().replace(".", ",");
		}	
		
				
		$scope.listaDiariaExcel.push({
			nome : lista.funcionarioDiaria.contaFuncionario.funcionario.pessoa.nomeCompleto,
			cargo : lista.funcionarioDiaria.contaFuncionario.funcionario.cargoAtual.descricao,
			unidadeLotado :		lista.funcionarioDiaria.unidade.dadosUnidade.mnemonico,
			dataSaida: lista.dataSaida,
			localDeslocamento: cidade,
			motivo: motivo,
			valorDiaria: valorDiaria,
			valorPassagem: valorPassagem
		});
	};
}


function ItemDiariaCoordenadoriaListController($filter, $stateParams, $state, ItemDiariaService,
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
		var cidade = '';
		for(c = 0 ; c < lista.localDeslocamentos.length ; c++){			
			cidade += lista.localDeslocamentos[c].nome;
			
			if(c +1 < lista.localDeslocamentos.length){
				cidade += " , ";
			}
		}
		var motivo = lista.motivo;
		if(lista.dataSaida < lista.dataChegada){
			lista.dataChegada  = $filter('date')(lista.dataChegada, "dd/MM/yyyy"); 
			motivo += " - retorno no dia " + lista.dataChegada;
		}
		if(lista.valorPassagem == 0){
			var valorPassagem = 0.00;
		}
		if(lista.valorDiaria){
			var valorDiaria =  lista.valorDiaria.toString().replace(".", ",");			
		}
		if(lista.valorPassagem){
			var valorPassagem = lista.valorPassagem.toString().replace(".", ",");
		}	
		
				
		$scope.listaDiariaExcel.push({
			nome : lista.funcionarioDiaria.contaFuncionario.funcionario.pessoa.nomeCompleto,
			cargo : lista.funcionarioDiaria.contaFuncionario.funcionario.cargoAtual.descricao,
			unidadeLotado :		lista.funcionarioDiaria.unidade.dadosUnidade.mnemonico,
			dataSaida: lista.dataSaida,
			localDeslocamento: cidade,
			motivo: motivo,
			valorDiaria: valorDiaria,
			valorPassagem: valorPassagem
		});
	};
}

function ItemDiariaSecretariaListController($filter, $stateParams, $state, ItemDiariaService,
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
	};
	
	forLista = function(f){
		for(i = 0 ; i < f.length ; i++){
			listaExcel(f[i]);
		}
	}
	
	listaExcel = function(lista){
		var cidade = '';
		for(c = 0 ; c < lista.localDeslocamentos.length ; c++){			
			cidade += lista.localDeslocamentos[c].nome;
			
			if(c +1 < lista.localDeslocamentos.length){
				cidade += " , ";
			}
		}
		var motivo = lista.motivo;
		if(lista.dataSaida < lista.dataChegada){
			lista.dataChegada  = $filter('date')(lista.dataChegada, "dd/MM/yyyy"); 
			motivo += " - retorno no dia " + lista.dataChegada;
		}
		if(lista.valorPassagem == 0){
			var valorPassagem = 0.00;
		}
		if(lista.valorDiaria){
			var valorDiaria =  lista.valorDiaria.toString().replace(".", ",");			
		}
		if(lista.valorPassagem){
			var valorPassagem = lista.valorPassagem.toString().replace(".", ",");
		}	
		
				
		$scope.listaDiariaExcel.push({
			nome : lista.funcionarioDiaria.contaFuncionario.funcionario.pessoa.nomeCompleto,
			cargo : lista.funcionarioDiaria.contaFuncionario.funcionario.cargoAtual.descricao,
			unidadeLotado :		lista.funcionarioDiaria.unidade.dadosUnidade.mnemonico,
			dataSaida: lista.dataSaida,
			localDeslocamento: cidade,
			motivo: motivo,
			valorDiaria: valorDiaria,
			valorPassagem: valorPassagem
		});
	};
}


function ItemDiariaShowController($stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope) {
	var self = this;
	var idFuncionario = $stateParams.idFuncionarioDiaria;
	self.analizado = analizado;
	self.retorno = retorno;
	self.itensPorFuncionarioDiariaETipo = itensPorFuncionarioDiariaETipo;
	tipos();
	
	self.tipo = 'ADMINISTRATIVO';
	
	itensPorFuncionarioDiariaETipo(idFuncionario, self.tipo);
	
	function itensPorFuncionarioDiariaETipo(id , tipo) {
		
		ItemDiariaService.itensPorFuncionarioDiariaETipo(id, tipo).then(
				function(f) {
					$scope.itens = f;					
					$scope.valorTotal = 0;					
					for(i = 0 ; i < $scope.itens.length ; i++){
						$scope.funcionario = $scope.itens[i].funcionarioDiaria;						
						var soma;
						soma = parseFloat($scope.itens[i].valorDiaria);
						$scope.valorTotal += parseFloat(soma);
					}
				}, function(errResponse) {				
				});
	};
	
	function retorno(idItem) {
		ItemDiariaService.retorno(idItem).then(
				function(f) {		
					toastr.success("retorno lançado!");
					itensPorFuncionarioDiariaETipo(idFuncionario, self.tipo)
				}, function(errResponse) {				
				});
	};
	
	function analizado(idItem) {
		ItemDiariaService.analizado(idItem).then(
				function(f) {		
					toastr.success("analisado!");
					itensPorFuncionarioDiariaETipo(idFuncionario, self.tipo)
				}, function(errResponse) {				
				});
	};
	
	function tipos() {
		ItemDiariaService.tipos().then(
				function(f) {					
					self.tipos = f;
				}, function(errResponse) {
					
				});
	};
	
	
}

function ItemDiariaUsuarioController($stateParams, $state, ItemDiariaService, toastr, $rootScope, $scope, FuncionarioContaDiariaService) {
	var self = this;
	var idFuncionario = $rootScope.usuario.funcionario.id;
	var idDiaria = $stateParams.idDiaria;
	self.itensPorFuncionarioDiariaETipo = itensPorFuncionarioDiariaETipo;
	buscarFuncionarioDiaria(idFuncionario);
	
	tipos();
	
	self.tipo = 'ADMINISTRATIVO';	
	
	function buscarFuncionarioDiaria(id) {
		ItemDiariaService.buscarPorIdFuncionario(idDiaria, id).then(
				function(f) {
					$scope.funcionario = f;				
					itensPorFuncionarioDiariaETipo(f.id, self.tipo);
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
		
	function itensPorFuncionarioDiariaETipo(id, tipo) {
		ItemDiariaService.itensPorFuncionarioDiariaETipo(id, tipo).then(
				function(f) {
					$scope.itens = f;					
					$scope.valorTotal = 0;					
					for(i = 0 ; i < $scope.itens.length ; i++){
						$scope.funcionario = $scope.itens[i].funcionarioDiaria;						
						var soma;
						soma = parseFloat($scope.itens[i].valorDiaria);
						$scope.valorTotal += parseFloat(soma);
					}
				}, function(errResponse) {				
				});
	};
	
	function tipos() {
		ItemDiariaService.tipos().then(
				function(f) {					
					self.tipos = f;
				}, function(errResponse) {
					
				});
	};
}