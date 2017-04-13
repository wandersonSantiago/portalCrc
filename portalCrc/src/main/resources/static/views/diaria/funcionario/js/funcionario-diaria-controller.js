app.controller("FuncionarioDiariaCadastrarController", FuncionarioDiariaCadastrarController);
app.controller("FuncionarioDiariaEditarController", FuncionarioDiariaEditarController);
app.controller("FuncionarioDiariaListarController", FuncionarioDiariaListarController);
app.controller("FuncionarioDiariaShowController", FuncionarioDiariaShowController);

FuncionarioDiariaCadastrarController.$inject = ['ItemDiariaService','$stateParams','FuncionarioDiariaService', 'FuncionarioService', 'toastr', '$rootScope', '$scope'];
FuncionarioDiariaEditarController.$inject = ['$location','$stateParams', '$state', 'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope'];
FuncionarioDiariaListarController.$inject = ['$stateParams', '$state', 'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope'];
FuncionarioDiariaShowController.$inject = ['$stateParams', '$state', 'FuncionarioDiariaService', 'toastr', '$rootScope', '$scope'];

function FuncionarioDiariaCadastrarController(ItemDiariaService, $stateParams, FuncionarioDiariaService, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	self.buscarValoresDiaria = buscarValoresDiaria;
	listarFuncionarios();
	self.adicionaListaDiaria = adicionaListaDiaria;
	self.submit = submit;
	$scope.listaDiariaItens = [];
	
	$scope.quantidadePernoite = 0;
	$scope.quantidadeRetornoTrezeAsDezenove = 0;
	$scope.quantidadeRetornoAposDezenove = 0;
	$scope.quantidadeDeslocamentoSeisAsDoze = 0;
	$scope.quantidadeDeslocamentoMaisDeDoze = 0;
	$scope.somaTotal = 0;
	$scope.somaParcial = 0;
	
	function submit(funcionarioDiaria) {
		FuncionarioDiariaService.salvar(self.funcionarioDiaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				limpaCampos();
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
		function listarFuncionarios(){
			 FuncionarioService.listar().
				then(function(f){
					self.funcionarios = f;		
					console.log(f);
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
			};
			limpaCampos = function(){
				self.funcionarioDiaria.destino = null;
				self.funcionarioDiaria.dataDiaria = null;
				self.funcionarioDiaria.valorDiaria = null;
				self.funcionarioDiaria.valorPassagem = null;
				self.funcionarioDiaria.motivo = null;
			};
			$scope.somaItem = [];
			
			function adicionaListaDiaria(itens){
				soma();
				 if($scope.somaParcial > $scope.limiteDiaria){				
						sweetAlert({ timer : 10000,  text : 'O valor total esta acima do limite!!!',  type : "info", width: 300, higth: 300, padding: 20});
				     }else{
				$scope.listaDiariaItens.push({
					localDeslocamento : itens.destino,
					codigoLocalDeslocamento : itens.codigoLocalDeslocamento.codigo,
					meioTrasnporte : itens.meioTrasnporte,
					dataHoraSaida : itens.dataHoraSaida,
					dataHoraChegada : itens.dataHoraChegada,
					valorPassagem : itens.valorPassagem,
					motivo : itens.motivo,
					pernoite : self.pernoite,
					retornoTrezeAsDezenove : self.retornoTrezeAsDezenove,
					retornoAposDezenove : self.retornoAposDezenove,
					deslocamentoSeisAsDoze : self.deslocamentoSeisAsDoze,
					deslocamentoMaisDeDoze : self.deslocamentoMaisDeDoze,
					totalItem : $scope.somaItem
					
					})
					$scope.somaTotal =  $scope.somaParcial;
				}
			}
			
			function soma(){				
					$scope.somaItem = self.pernoite + self.retornoTrezeAsDezenove +
					self.retornoAposDezenove + self.deslocamentoSeisAsDoze + 
					self.deslocamentoMaisDeDoze;
					
				$scope.somaParcial = $scope.somaParcial + $scope.somaItem;
			}
		function buscarDiariaPorId(id){
				if(!id)return;
				ItemDiariaService.buscarDiariaPorId(id).
				then(function(p){
					self.diaria = p;
					self.diaria.dataDiaria = new Date(p.dataDiaria);
					$scope.funcionarioCtrl.funcionarioDiaria = {diaria : self.diaria};
			}, function(errResponse){
				});
			};
		
			if(idDiaria){
				buscarDiariaPorId(idDiaria);				
			}
			
		function buscarValoresDiaria(indice){
			FuncionarioDiariaService.buscarValoresDiaria(indice).
			then(function(p){
				self.valoresDiaria = p;
		}, function(errResponse){
			});
		};
			
		self.ativarExcluirLote = function(listaDiariaItens){
			$scope.listaDiariaItens.filter(function(item){
			if(item.selecionado){
				$scope.ativadoExcluirLote = true;
			}
			});
		}
		self.apagarItens = function(listaDiariaItens){
				$scope.listaDiariaItens = listaDiariaItens.filter(function(item){
				if(!item.selecionado) return item;
				$scope.ativadoExcluirLote = null;
			});
		}	
	
		
}

function FuncionarioDiariaEditarController($location, $stateParams, $state, FuncionarioDiariaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idItem = $stateParams.idItem;
	self.excluir = excluir;
	
	function submit(funcionarioDiaria) {
		FuncionarioDiariaService.alterar(self.funcionarioDiaria).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			$location.path('/item/'+self.funcionarioDiaria.diaria.id+'/listar/unidade');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		FuncionarioDiariaService.buscarPorId(id).
		then(function(p){
			self.funcionarioDiaria = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idItem){
		buscarPorId(idItem);		
	};
	
	
	 function excluir(objeto) {
		swal({
			  title: 'Excluir diária!!!',
			  text: 'Tem certeza que deseja excluir esta diária',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Excluir'
			}).then(function () {
				FuncionarioDiariaService.excluir(objeto.id).
				then(function(response){	
					listarUnidade(objeto.diaria.id);					
					}, function(errResponse){
				});	
				 
			})		
	};	
	function listarUnidade(id){
		 FuncionarioDiariaService.listarUnidade(id).
			then(function(f){
				self.diarias = f;	
				forLista(f);
				
				}, function(errResponse){
			});
		};
}
function FuncionarioDiariaListarController($stateParams, $state, FuncionarioDiariaService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 FuncionarioDiariaService.listarSecretaria().
			then(function(f){
				self.funcionarioDiarias = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function FuncionarioDiariaShowController( $stateParams, $state, FuncionarioDiariaService, toastr, $rootScope, $scope){
	
}