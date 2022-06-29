app.controller("ItemDiariaCadastrarController", ItemDiariaCadastrarController);
app.controller("ItemDiariaEditarController", ItemDiariaEditarController);
app.controller("ItemDiariaUnidadeListController", ItemDiariaUnidadeListController);
app.controller("ItemDiariaCoordenadoriaTransparenciaController", ItemDiariaCoordenadoriaTransparenciaController);
app.controller("ItemDiariaSecretariaListController", ItemDiariaSecretariaListController);
app.controller("ItemDiariaShowController", ItemDiariaShowController);
app.controller("ItemDiariaUsuarioController", ItemDiariaUsuarioController);


function ItemDiariaCadastrarController($localStorage, $state, ItemDiariaService, $stateParams,	FuncionarioDiariaService, FuncionarioContaDiariaService, toastr,$rootScope, $scope, CoordenadoriaService, UnidadeService, TipoService,	VeiculoService) {

	var self = this;	
	self.buscarCidades = buscarCidades;
	self.submit = submit;
	self.excluir = excluir;
	self.verificaDataFinal = verificaDataFinal;
	self.editar = true;
	self.adicionarLocal = adicionarLocal;
	self.removerLocal = removerLocal;
	self.radio = 'VEICULO';
	$scope.botao = "Salvar";
	$scope.localDeslocamentos = [];
	$scope.retorno = false;
	
	
	
	buscarEstados(33);
	tipos();
	
	if($stateParams.idDiaria){
		$localStorage.idDiaria = $stateParams.idDiaria;
	}
	if($stateParams.idFuncionario){
		$localStorage.idFuncionario = $stateParams.idFuncionario;
	}
		

	self.buscarValoresDiariaPorIndice = buscarValoresDiariaPorIndice;
	buscarFuncionarioDiaria($localStorage.idFuncionario);
	
	
	
	
	function submit(form) {
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		self.itemDiaria.funcionario = self.funcionario.contaFuncionario.funcionario;
		self.itemDiaria.diaria = self.diaria;
		self.itemDiaria.funcionarioDiaria = self.funcionario;
		self.itemDiaria.localDeslocamentos = $scope.localDeslocamentos;
		self.itemDiaria.horaSaida = atribuiADataAHora(self.itemDiaria.dataSaida, self.itemDiaria.horaSaida);
		self.itemDiaria.horaChegada = atribuiADataAHora(self.itemDiaria.dataChegada, self.itemDiaria.horaChegada);
		ItemDiariaService.salvar(self.itemDiaria).then(
				function(response) {
					buscarFuncionarioDiaria($localStorage.idFuncionario);
					buscarItensDiariaPorFuncionarioDiaria(self.funcionario.id);
					toastr.info("Salvo com Sucesso!!!");
					self.itemDiaria.dataSaida = '';
					self.itemDiaria.dataChegada = '';
					 $scope.formDiaria.$setPristine();
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
	
	
	function atribuiADataAHora(data , hora){
		return new Date(data.getFullYear(), data.getMonth(), data.getDate(), hora.getHours(), hora.getMinutes(), 0, 0);
	}
	
	function adicionarLocal(cidade){
		if(!cidade){
			return
		}
		$scope.localDeslocamentos.push(cidade);
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
	function buscarItensDiariaPorFuncionarioDiaria(id) {
		ItemDiariaService.buscarItensDiariaPorFuncionarioDiaria(id).then(
				function(f) {
					$scope.itens = f;
				}, function(errResponse) {				
				});
	};	
	
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
	};

	


}

function ItemDiariaEditarController($state, ItemDiariaService, $stateParams,FuncionarioDiariaService, FuncionarioContaDiariaService, toastr, $rootScope, $scope, TipoService) {

	var self = this;
	var idItem = $stateParams.idItem;
	$scope.botao = "Alterar";
	self.radio = 'VEICULO';
	self.buscarCidades = buscarCidades;
	self.adicionarLocal = adicionarLocal;
	self.removerLocal = removerLocal;
	self.submit = submit;
	tipos();
	$scope.localDeslocamentos = [];
	
	
	
	function submit(form) {	
		if(form.$invalid){
			sweetAlert({title: "Por favor preencha os campos obrigatorios", 	type : "error", timer : 100000,   width: 500,  padding: 20});	
			return;
		}
		;
		self.itemDiaria.horaSaida = atribuiADataAHora(new Date(self.itemDiaria.dataSaida),new Date(self.itemDiaria.horaSaida));
		self.itemDiaria.horaChegada = atribuiADataAHora(new Date(self.itemDiaria.dataChegada), new Date(self.itemDiaria.horaChegada));
		ItemDiariaService.alterar(self.itemDiaria).then(
				function(response) {				
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
				});
	};
		
	function atribuiADataAHora(data , hora){
		return new Date(data.getFullYear(), data.getMonth(), data.getDate(), hora.getHours(), hora.getMinutes(), 0, 0);
	}
	
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
			self.funcionario = self.itemDiaria.funcionarioDiaria;
			self.diaria = self.itemDiaria.funcionarioDiaria.diaria;
			buscarValoresDiariaPorIndice(self.funcionario.contaFuncionario.indiceUfesp, self.diaria.id);	
			buscarItensDiariaPorFuncionarioDiaria(self.funcionario.id);
			buscarEstados(33);
			$scope.localDeslocamentos = self.itemDiaria.localDeslocamentos;
			self.itemDiaria.localDeslocamento = self.itemDiaria.localDeslocamentos[0];
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
	};

	
}
function ItemDiariaUnidadeListController($filter, $stateParams, $state, ItemDiariaService,
		toastr, $rootScope, $scope, blockUI) {
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


function ItemDiariaCoordenadoriaTransparenciaController($filter, $stateParams, $state, $timeout, ItemDiariaService, DiariaService,	toastr, $rootScope, $scope) {
	
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	
	$scope.listaDiariaExcel = [];
	self.diariasDoMes= diariasDoMes;
	self.carregarDiaria = carregarDiaria;
	self.carregarDiariaCoordenadoria = carregarDiariaCoordenadoria;
	diariasDoMes()
	
	listarMes()
	
	$scope.max = 100;
	$scope.dynamic = 0;
	
	//Busca os 12 meses do ano para a pesquisa
	function listarMes(){
		 DiariaService.listarMes().
			then(function(f){
				self.meses = f;					
				}, function(errResponse){
			});
		};
	//fim	
		
	//Busca uma lista de diaria referente ao mes selecionado	
	function diariasDoMes(mes){
		 DiariaService.diariasDoMes(mes).
			then(function(f){
				$scope.dynamic = 0;
				self.diarias = f;					
				}, function(errResponse){
			});
		};
	//fim	
	//Carrega todas as diarias da coordenadoria para a exportação para excell
		function carregarDiariaCoordenadoria(mes) {
			ItemDiariaService.porCoordenadoria(mes).then(function(f) {				
				incrementDynamic();
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
		//fim
		
		 //método para realizar o progress da lista   coordenadoria
		  incrementDynamic = function() {	
			  var tempo = 50;
			  $scope.dynamic++;
		      if ($scope.dynamic <= 100) {
		    	  $scope.$evalAsync(function () {
		    		 $scope.dynamic == 99 ? tempo = 200 : '';		    		
		    		  setTimeout(function() {
		    			  incrementDynamic()
		    		      }, tempo)
		    	  });
		      }		  
		  }
		//fim
		  
	//Carrega a lista da diaria selecionada para a exportação para excell
	function carregarDiaria(diaria) {
		ItemDiariaService.porUnidade(diaria.id).then(function(f) {
			diaria.dynamic = 0;
			increment(diaria);
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
	//fim
	
	 //método para realizar o progress da lista   
	  increment = function(diaria) {
		  var tempo = 50;
		  diaria.dynamic++;
	      if (diaria.dynamic <= 100) {
	    	  $scope.$evalAsync(function () {
	    		  $scope.dynamic == 99 ? tempo = 2000 : '';	
	    		  setTimeout(function() {
	    			  increment(diaria)
	    		      }, tempo)
	    	  });
	      }		  
	  }
	//fim
	  
	 //TUDO: para transferir a tabela para excell
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
		lista.dataSaida = $filter('date')(lista.dataSaida, "dd/MM/yyyy"); 
				
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
	//FIM: adiciona tabela para excell
	
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


function ItemDiariaShowController($stateParams, $state, ItemDiariaService,	toastr, $rootScope, $scope, blockUI) {
	var self = this;
	var idFuncionario = $stateParams.idFuncionarioDiaria;
	self.analizado = analizado;
	self.retorno = retorno;
	self.itensPorFuncionarioDiariaETipo = itensPorFuncionarioDiariaETipo;
	tipos();
	self.excluir = excluir;
	self.tipo = 'ADMINISTRATIVO';
	$scope.desabilitar = false;
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
						
						self.tipo = $scope.itens[i].tipo;
					}
				}, function(errResponse) {	
					$state.go('diaria.listar');
					sweetAlert({
						text : errResponse.data.message,
						type : "info",
						width : 300,
						higth : 300,
						padding : 20
					});
				});
	};
	
	function retorno(idItem) {
		ItemDiariaService.retorno(idItem).then(
				function(f) {		
					toastr.success("retorno lançado!");
					itensPorFuncionarioDiariaETipo(idFuncionario, self.tipo)
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
	
	function analizado(idItem) {
		ItemDiariaService.analizado(idItem).then(
				function(f) {		
					toastr.success("analisado!");
					itensPorFuncionarioDiariaETipo(idFuncionario, self.tipo)
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
	
	function tipos() {
		ItemDiariaService.tipos().then(
				function(f) {					
					self.tipos = f;
				}, function(errResponse) {
					
				});
	};
	
self.imprimir = imprimir;
	
	
	function imprimir(id , tipo){
		blockUI.start();
		ItemDiariaService.imprimir(id , tipo)
   	 .then(function(d){ 
   		var file = new Blob([d],{type: 'application/pdf'});
   		var fileURL = URL.createObjectURL(file);
   		blockUI.stop();
   	    window.open(fileURL);
   	 	 },function(errResponse){	
   	 		blockUI.stop();
				 swal({ timer : 3000, text : errResponse.data ,  type : "error", width: 200, higth: 100, padding: 20}).catch(swal.noop);
		 	});
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
				toastr.success("Excluido!");
				$scope.itens.splice($scope.itens.indexOf(objeto), 1);
			}, function(errResponse) {
			});

		})
	};
	
}

function ItemDiariaUsuarioController($stateParams, $state, ItemDiariaService, toastr, $rootScope, $scope, FuncionarioContaDiariaService, blockUI) {
	var self = this;
	var idFuncionario = $rootScope.user.funcionario.id;
	var idDiaria = $stateParams.idDiaria;
	self.itensPorFuncionarioDiariaETipo = itensPorFuncionarioDiariaETipo;
	buscarFuncionarioDiaria(idFuncionario);
	self.excluir = excluir;
	tipos();
	$scope.desabilitar = true;
	self.tipo = 'ADMINISTRATIVO';	
	
	function buscarFuncionarioDiaria(id) {
		ItemDiariaService.buscarPorIdFuncionario(idDiaria, id).then(
				function(f) {
					$scope.funcionario = f;				
					itensPorFuncionarioDiariaETipo(f.id, self.tipo);
				}, function(errResponse) {
					$state.go('diaria.listar');
					sweetAlert({
						text : "Não existe diária lançada para este funcionário!",
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
	
self.imprimir = imprimir;
	
	
	function imprimir(id, tipo){
		blockUI.start();
		ItemDiariaService.imprimir(id, tipo)
   	 .then(function(d){
   		var file = new Blob([d],{type: 'application/pdf'});
   		var fileURL = URL.createObjectURL(file);
   		blockUI.stop();
   	    window.open(fileURL);
   	 	 },function(errResponse){	
   	 		blockUI.stop();
				 swal({ timer : 3000, text : errResponse.data ,  type : "error", width: 200, higth: 100, padding: 20}).catch(swal.noop);
		 	});
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
				toastr.success("Excluido!");
				$scope.itens.splice($scope.itens.indexOf(objeto), 1);
			}, function(errResponse) {
			});

		})
	};
	
}