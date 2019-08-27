app.controller("FuncionarioContaDiariaCadastrarController", FuncionarioContaDiariaCadastrarController);
app.controller("FuncionarioContaDiariaEditarController", FuncionarioContaDiariaEditarController);
app.controller("FuncionarioContaDiariaListarController", FuncionarioContaDiariaListarController);
app.controller("FuncionarioContaBuscarListarController", FuncionarioContaBuscarListarController);


function FuncionarioContaDiariaCadastrarController($state, FuncionarioService, $stateParams, FuncionarioContaDiariaService, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	var idFuncionario = $stateParams.idFuncionario;	
	self.submit = submit;
	limite();
	indice();
	
	
	function submit(contaFuncionarioDiaria) {
		if($scope.indiceUfesp == 'SETE'){
			self.contaFuncionarioDiaria.indiceUfesp = 7;
		}else{
			self.contaFuncionarioDiaria.indiceUfesp = 9;
		}
		if($scope.limiteCemPorCento == 'CEM_POR_CENTO'){
			self.contaFuncionarioDiaria.limiteCemPorCento = 100;
		}else{
			self.contaFuncionarioDiaria.limiteCemPorCento = 50;
		}
		self.contaFuncionarioDiaria.funcionario = self.funcionario;
		FuncionarioContaDiariaService.salvar(self.contaFuncionarioDiaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				$state.go('diaria.listar');
				}, function(errResponse){
					sweetAlert({text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	
		function buscarFuncionarioPorId(id){
				if(!id)return;
				FuncionarioService.buscarPorId(id).
				then(function(p){
					self.funcionario = p;
					buscarContaPorIdFuncionario(p.id);
			}, function(errResponse){
				});
			};
		
			if(idFuncionario){
				buscarFuncionarioPorId(idFuncionario);				
			}
			
		function buscarContaPorIdFuncionario(id){
				if(!id)return;
				FuncionarioContaDiariaService.buscarContaPorIdFuncionario(id).
				then(function(p){
					var idConta = p.id
					$state.go('funcionarioContaDiaria.editar', {idConta});
			}, function(errResponse){
				});
			};
			
			function limite(){
				 FuncionarioContaDiariaService.limite().
					then(function(f){
						$scope.limites = f;				
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
				
				function indice(){
					 FuncionarioContaDiariaService.indice().
						then(function(f){
							$scope.indices = f;				
							}, function(errResponse){
								sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
						});
					};
	
		
}

function FuncionarioContaDiariaEditarController($state, FuncionarioService, $stateParams, FuncionarioContaDiariaService, FuncionarioService, toastr, $rootScope, $scope){
	var self = this;
	var idConta = $stateParams.idConta;	
	self.submit = submit;
	limite();
	indice();
	
	function submit(contaFuncionarioDiaria) {
		if($scope.indiceUfesp == 'SETE'){
			self.contaFuncionarioDiaria.indiceUfesp = 7;
		}else{
			self.contaFuncionarioDiaria.indiceUfesp = 9;
		}
		if($scope.limiteCemPorCento == 'CEM_POR_CENTO'){
			self.contaFuncionarioDiaria.limiteCemPorCento = 100;
		}else{
			self.contaFuncionarioDiaria.limiteCemPorCento = 50;
		}
		self.contaFuncionarioDiaria.funcionario = self.funcionario;
		FuncionarioContaDiariaService.alterar(self.contaFuncionarioDiaria).
			then(function(response){
				toastr.info("alterado com Sucesso!!!");
				$state.go('funcionarioContaDiaria.buscar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		
	
		function buscarContaFuncionarioPorId(id){
				if(!id)return;
				FuncionarioContaDiariaService.buscarPorId(id).
				then(function(p){
					self.contaFuncionarioDiaria = p;
					
					if(self.contaFuncionarioDiaria.indiceUfesp == 7){
						$scope.indiceUfesp = 'SETE';
					}else{
						$scope.indiceUfesp = 'NOVE';
					}
					if(self.contaFuncionarioDiaria.limiteCemPorCento == 100){
						$scope.limiteCemPorCento = 'CEM_POR_CENTO';
					}else{
						$scope.limiteCemPorCento = 'CINQUENTA_POR_CENTO';
					}
					self.funcionario = p.funcionario;
			}, function(errResponse){
				});
			};
		
			if(idConta){
				buscarContaFuncionarioPorId(idConta);				
			}
			
			function limite(){
				 FuncionarioContaDiariaService.limite().
					then(function(f){
						$scope.limites = f;				
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});
				};
				
				function indice(){
					 FuncionarioContaDiariaService.indice().
						then(function(f){
							$scope.indices = f;				
							}, function(errResponse){
								sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
						});
					};
	
}
function FuncionarioContaDiariaListarController($stateParams, $state, FuncionarioContaDiariaService, toastr, $rootScope, $scope){
	
	listar();
	
	 function listar(){
		 FuncionarioContaDiariaService.listarPorUnidade().
			then(function(f){
				$scope.contas = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}

function FuncionarioContaBuscarListarController(FuncionarioContaDiariaService, $state, FuncionarioService, $rootScope, $scope, blockUI){
	var self = this;
	self.buscarFuncionarioPorTexto = buscarFuncionarioPorTexto;	
	self.buscarPorTexto = buscarPorTexto;
	self.totalElementos = {};
	self.totalPaginas = null;
	self.paginaCorrente = 0;
	$scope.funcionario = null;
	buscarPorTexto('');
		
			     
    function buscarPorTexto(texto){
    	$scope.mensagemErro = null;
    	 blockUI.start();	    	 
    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;    	 
    	 FuncionarioContaDiariaService.buscarPorTexto(texto, self.paginaCorrente).
    	 then(function(e){
    		 $scope.mensagemErro = null;
    		 self.contas = e.content;	
    		 self.totalElementos = e.totalElements;
    		 self.totalPaginas = e.totalPages;
    		 self.paginaCorrente = e.number + 1;
    		 blockUI.stop();
    	 }, function(errResponse){
    		 blockUI.stop();
    		 if(errResponse.status == 404){
    			 $scope.mensagemErro = errResponse.data.message;
    		 }else{
    			 $scope.mensagemErro =errResponse.data.message;
    		 }
		 });
    }
	    
	 function buscarFuncionarioPorTexto(texto){
	     	return  FuncionarioService.buscarPorTexto(texto).
	     	 then(function(e){
	     		return e.content;
	     	 }, function(errResponse){
	     	 });
	     }
	
}