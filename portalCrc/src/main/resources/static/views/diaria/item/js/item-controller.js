app.controller("ItemDiariaCadastrarController", ItemDiariaCadastrarController);
app.controller("ItemDiariaEditarController", ItemDiariaEditarController);
app.controller("ItemDiariaListarController", ItemDiariaListarController);
app.controller("ItemDiariaShowController", ItemDiariaShowController);

ItemDiariaCadastrarController.$inject = ['$stateParams','ItemDiariaService', 'FuncionarioService', 'toastr', '$rootScope', '$scope'];
ItemDiariaEditarController.$inject = ['$location','$stateParams', '$state', 'ItemDiariaService', 'toastr', '$rootScope', '$scope'];
ItemDiariaListarController.$inject = ['$stateParams', '$state', 'ItemDiariaService', 'toastr', '$rootScope', '$scope'];
ItemDiariaShowController.$inject = ['$stateParams', '$state', 'ItemDiariaService', 'toastr', '$rootScope', '$scope'];

function ItemDiariaCadastrarController($stateParams, ItemDiariaService, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	var idDiaria = $stateParams.idDiaria;
	/*var idDiariaCoordenadoria = $stateParams.idDiariaCoordenadoria;
	var idDiariaUnidade = $stateParams.idDiariaUnidade;
	var idDiariaSecretaria = $stateParams.idDiariaSecretaria;*/
	self.buscarValoresDiaria = buscarValoresDiaria;
	listarFuncionarios();
	self.adicionaListaDiaria = adicionaListaDiaria;
	self.submit = submit;
	$scope.listaDiariaExcel = [];
	$scope.listaDiariaItens = [];
	
	
	function submit(itemDiaria) {
		ItemDiariaService.salvar(self.itemDiaria).
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
				self.itemDiaria.destino = null;
				self.itemDiaria.dataDiaria = null;
				self.itemDiaria.valorDiaria = null;
				self.itemDiaria.valorPassagem = null;
				self.itemDiaria.motivo = null;
			};
			
			
			function adicionaListaDiaria(itens){
				$scope.listaDiariaItens.push({
					localDeslocamento : itens.destino,
					codigoLocalDeslocamento : itens.codigoLocalDeslocamento,
					meioTrasnporte : itens.meioTrasnporte,
					dataHoraSaida : itens.dataHoraSaida,
					dataHoraChegada : itens.dataHoraChegada,
					valorPassagem : itens.valorPassagem,
					motivo : itens.motivo
				})
			}
			
		function buscarDiariaPorId(id){
				if(!id)return;
				ItemDiariaService.buscarDiariaPorId(id).
				then(function(p){
					self.diaria = p;
					self.diaria.dataDiaria = new Date(p.dataDiaria);
					$scope.itemCtrl.itemDiaria = {diaria : self.diaria};
			}, function(errResponse){
				});
			};
		
			if(idDiaria){
				buscarDiariaPorId(idDiaria);				
			}
			
		function buscarValoresDiaria(indice){
			ItemDiariaService.buscarValoresDiaria(indice).
			then(function(p){
				self.valoresDiaria = p;
		}, function(errResponse){
			});
		};
			
			
			/*function listarSecretaria(id){
				 ItemDiariaService.listarSecretaria(id).
					then(function(f){
						self.diarias = f;	
						forLista(f);
						}, function(errResponse){
					});
				};
				if(idDiariaSecretaria){
					listarSecretaria(idDiariaSecretaria);
				}
				
			function listarCoordenadoria(id){
				console.log(id);
				 ItemDiariaService.listarCoordenadoria(id).
					then(function(f){
						self.diarias = f;	
						forLista(f);
						}, function(errResponse){
					});
				};
				
				if(idDiariaCoordenadoria){
					listarCoordenadoria(idDiariaCoordenadoria);
				}
				
			 function listarUnidade(id){
				 ItemDiariaService.listarUnidade(id).
					then(function(f){
						self.diarias = f;	
						forLista(f);						
						}, function(errResponse){
					});
				};
				if(idDiariaUnidade){
					listarUnidade(idDiariaUnidade);
				}
				
				forLista = function(f){
					for(i = 0 ; i < f.length ; i++){
						listaExcel(f[i]);
					}
				}
				
				listaExcel = function(lista){
					$scope.listaDiariaExcel.push({
						nome : lista.funcionario.pessoa.nomeCompleto,
						cargo : lista.funcionario.cargoAtual.descricao,
						unidadeLotado :		lista.unidade.dadosUnidade.mnemonico,
						dataDiaria: lista.dataDiaria,
						destino: lista.destino,
						motivo: lista.motivo,
						valorDiaria: lista.valorDiaria,
						valorPassagem: lista.valorPassagem
					});
				};
*/}

function ItemDiariaEditarController($location, $stateParams, $state, ItemDiariaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idItem = $stateParams.idItem;
	self.excluir = excluir;
	
	function submit(itemDiaria) {
		ItemDiariaService.alterar(self.itemDiaria).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			$location.path('/item/'+self.itemDiaria.diaria.id+'/listar/unidade');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		ItemDiariaService.buscarPorId(id).
		then(function(p){
			self.itemDiaria = p;
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
				ItemDiariaService.excluir(objeto.id).
				then(function(response){	
					listarUnidade(objeto.diaria.id);					
					}, function(errResponse){
				});	
				 
			})		
	};	
	function listarUnidade(id){
		 ItemDiariaService.listarUnidade(id).
			then(function(f){
				self.diarias = f;	
				forLista(f);
				
				}, function(errResponse){
			});
		};
}
function ItemDiariaListarController($stateParams, $state, ItemDiariaService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 ItemDiariaService.listarSecretaria().
			then(function(f){
				self.itemDiarias = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function ItemDiariaShowController( $stateParams, $state, ItemDiariaService, toastr, $rootScope, $scope){
	
}