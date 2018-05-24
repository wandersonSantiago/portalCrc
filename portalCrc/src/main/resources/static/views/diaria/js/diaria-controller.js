app.controller("DiariaCadastarController", DiariaCadastarController);
app.controller("DiariaEditarController", DiariaEditarController);
app.controller("DiariaListarController", DiariaListarController);
app.controller("DiariaRelatorioController", DiariaRelatorioController);


function DiariaCadastarController( DiariaService, $state, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	$scope.botao = "Cadastrar";
	listarMes();
	
	
	function submit(diaria) {
		DiariaService.salvar(self.diaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.diaria = null;
				$state.go('diaria.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	function listarMes(){
		 DiariaService.listarMes().
			then(function(f){
				$scope.listarMes = f;					
				}, function(errResponse){
			});
		};
}
function DiariaEditarController($stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	$scope.botao = "Editar";
	var idDiaria = $stateParams.idDiaria;
	
	listarMes();
	
	
	function submit(diaria) {
		DiariaService.alterar(self.diaria).
			then(function(response){
				toastr.success("Alterado com Sucesso!!!");
				self.diaria = null;
				$state.go('diaria.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	function listarMes(){
		 DiariaService.listarMes().
			then(function(f){
				$scope.listarMes = f;					
				}, function(errResponse){
			});
		};
	function buscarPorId(id){
		if(!id)return;
		DiariaService.buscarPorId(id).
		then(function(p){
			self.diaria = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idDiaria){
		buscarPorId(idDiaria);		
	};
	
	
}
function DiariaListarController(ItemDiariaService, $stateParams, $state, DiariaService, toastr, $rootScope, $scope){
	var self = this;
	self.encerrar = encerrar;
	self.buscarFuncionarioDiaria = buscarFuncionarioDiaria;
	
	diariasEmAberto();
	dashBoard();
	
	$rootScope.escondeMenu = true;
		
			
	function diariasEmAberto(){
			 DiariaService.diariasEmAberto().
				then(function(f){
					self.diaria = f;		
					buscarFuncionarioDiaria(f.id, $rootScope.user.funcionario.id);
					}, function(errResponse){
				});
			};
		
			function buscarFuncionarioDiaria(idDiaria, idFuncionario) {
				ItemDiariaService.buscarPorIdFuncionario(idDiaria, idFuncionario).then(
						function(f) {
							$scope.funcionario = f;				
						}, function(errResponse) {
							$scope.funcionario = null;
						});
			};
	function encerrar(objeto) {
			swal({
				  title: 'Encerrar Lançamentos!!!',
				  text: "Após esta ação as Unidades não conseguiram realizar mais lançamentos no Mês selecionado ",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Encerrar'
				}).then(function () {
					 DiariaService.encerrar(objeto).
					then(function(response){
						toastr.success("Encerrado com Sucesso!!!");
						diariasEmAberto();
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
					});	
					 
				})		
		};
		
		$scope.data = [];
		
		function dashBoard(){
			ItemDiariaService.dashBoard().
				then(function(f){
					self.dash = f;
					chart(f);
					}, function(errResponse){
				});
			};
			
		 
		function chart(f){
			var administrativo = [];
			var seguranca = [];
			var label = [];
			for (var value of f) {						
				 administrativo.push(value.valores[0]);
				 seguranca.push(value.valores[1]);
				 label.push(value.mes);							
				}
			 $scope.series = ['Administrativo', 'Segurança'];
			 $scope.data =[administrativo , seguranca];
			 $scope.labels = label;
		}	
			    

		 
}
function DiariaRelatorioController( $stateParams, $state, DiariaService, toastr, $rootScope, $scope, FuncionarioContaDiariaService, FuncionarioDiariaService, blockUI){
	var self = this;
	self.buscarPorTexto = buscarPorTexto;
	self.pesquisar = pesquisar;
	
	
	$scope.tipo = 'LISTAGEM';
    $scope.imprimir = 'PAGINA';
    
    
			
		 function buscarPorTexto(texto){
		     	return  FuncionarioContaDiariaService.buscarPorTexto(texto).
		     	 then(function(e){
		     		return e.content;
		     	 }, function(errResponse){
		     		 $scope.messageErro = errResponse.data.message;
		     	 });
		 }
		 
		 function pesquisar(imprimir, idFuncionario, dataInicial , dataFinal){
			  if($scope.tipo == 'LISTAGEM'){
				  idFuncionario = null;
				  if(imprimir == 'PAGINA'){
					  buscarMovimentacaoPorDataEPorFuncionario(idFuncionario, dataInicial , dataFinal);
				  }else{
					  buscarListagemPorDiariaEPorDataPDF(idFuncionario, dataInicial , dataFinal);
				  }			  
			  }			 
			  if($scope.tipo == 'NOMINAL'){
				  if(imprimir == 'PAGINA'){
					  buscarMovimentacaoPorDataEPorFuncionario(idFuncionario, dataInicial , dataFinal);
				  }else{
					  buscarListagemPorDiariaEPorDataPDF(idFuncionario, dataInicial , dataFinal);
				  }		
				 
			  }
		  }
		  
		   
		     function buscarListagemPorDiariaEPorDataPDF(idFuncionario, dataInicial , dataFinal){	
			    	blockUI.start();
			    	DiariaService.imprimir(idFuncionario, dataInicial , dataFinal)
	 	   	 .then(function(d){
	 	   		var file = new Blob([d],{type: 'application/pdf'});
	 	   		var fileURL = URL.createObjectURL(file);
	 	   		blockUI.stop();
	 	   	    window.open(fileURL);
	 	   	 	 }).catch(function error(msg) {
	 	   	 		 if(msg.data.message){
	 	   	 		mensagemAlerta(msg.data.message, 'info');
	 	   	 		 }
	 	   	 	mensagemAlerta("Não foi possivel gerar o PDF", 'error');
	 	   	 });
		     };		     
		     
		   				
			function buscarMovimentacaoPorDataEPorFuncionario(idFuncionario, dataInicial , dataFinal){
		    	 DiariaService.funcionariosPorDataPorId(idFuncionario, dataInicial , dataFinal).then(
							function(f) {
								self.funcionarios = f;		
							}, function(errResponse) {								
								mensagemAlerta(errResponse.data.message, 'info');								
								$scope.funcionario = null;
							});
				};	
			
				function mensagemAlerta(texto, tipo){
					sweetAlert({ timer : 30000,  text : texto,  type : tipo, width: 300, higth: 300, padding: 20});
				}
}