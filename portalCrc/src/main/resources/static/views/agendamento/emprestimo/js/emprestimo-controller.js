app.controller("EmprestimoCadastarController", EmprestimoCadastarController);
app.controller("EmprestimoEditarController", EmprestimoEditarController);
app.controller("EmprestimoListarController", EmprestimoListarController);
app.controller("EmprestimoShowController", EmprestimoShowController);

EmprestimoCadastarController.$inject = ['EmprestimoService','EquipamentoService','FuncionarioUnidadeService','FuncionarioService','toastr', '$rootScope', '$scope', 'UnidadeService'];
EmprestimoEditarController.$inject = ['EmprestimoService','EquipamentoService','FuncionarioUnidadeService','FuncionarioService','toastr', '$rootScope', '$scope', 'UnidadeService', '$stateParams', '$state'];
EmprestimoListarController.$inject = ['EmprestimoService','EquipamentoService','FuncionarioService', 'toastr', '$rootScope', '$scope', 'blockUI', 'UnidadeService'];
EmprestimoShowController.$inject = ['$stateParams', '$state', 'EquipamentoService','FuncionarioService', 'toastr', '$rootScope', '$scope', 'UnidadeService'];

function EmprestimoCadastarController(EmprestimoService,EquipamentoService,FuncionarioUnidadeService, FuncionarioService,toastr, $rootScope, $scope, UnidadeService){
	var self = this;
		self.submit =submit;
		$scope.equipamentos = [];
		self.emprestimo = {equipamento : $scope.equipamentos}
		status();
		listarUnidades();	
		listaTipoEquipamentoEnum();
		funcionario();
		equipamentosList();
				
		self.addEquipamento = addEquipamento;
		
		function submit() {
			
			EmprestimoService.salvar(self.emprestimo).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.emprestimo = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		function addEquipamento(equipamento){
			$scope.equipamentos.push(
				 equipamento
			);
		}
		
		function listaTipoEquipamentoEnum() {
			EquipamentoService.listarTipoEquipamento().then(function(f) {
				self.tiposEquipamentoEnum = f;
			}, function(errResponse) {
			});
		};
		
		function equipamentosList() {
			EquipamentoService.listar().then(function(f) {
				self.equipamentos = f;
			}, function(errResponse) {
			});
		};
		
										
			 function funcionario(){
				 FuncionarioService.listarPorUnidade().
					then(function(f){
						self.funcionarios = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
			
		
		
		 function listarUnidades(){
			 UnidadeService.listar().
				then(function(f){
					self.unidades = f;	
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};

		

			
			function status(){
				EmprestimoService.status().
					then(function(f){
						self.statusEmprestimo = f;	
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
			
}
function EmprestimoEditarController(EmprestimoService, EquipamentoService, FuncionarioUnidadeService, FuncionarioService, toastr, $rootScope, $scope, UnidadeService, $stateParams, $state){
														  						
	var self = this;
		self.submit = submit;
		var idEmprestimo = $stateParams.idEmprestimo;		
		$scope.equipamentos = [];
		self.emprestimo = {equipamento : $scope.equipamentos}
		status();
		listarUnidades();
		listaTipoEquipamentoEnum();		
		funcionario();
		equipamentosList();
				
		self.addEquipamento = addEquipamento;
		
				
		/* alterado     */
		
		function submit(emprestimo) {
			self.emprestimo.equipamento = $scope.equipamentos;
			EmprestimoService.alterar(self.emprestimo).
			then(function(response){
				toastr.info('Alterado com sucesso!!!');
				self.emprestimo = null;
				$state.go('emprestimo.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};        //alterado aqui
		

		
	
	function addEquipamento(equipamento){
		$scope.equipamentos.push(
			 equipamento
		);
	}
	
				
		function buscarPorId(id){
			if(!id)return;
			EmprestimoService.buscarPorId(id).
			then(function(p){
				
				self.emprestimo = p;
				self.emprestimo.dataEmprestimo = new Date( p.dataEmprestimo);  
				self.emprestimo.dataDevolucao = new Date(p.dataDevolucao);			
				
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		if(idEmprestimo){
			buscarPorId(idEmprestimo);			
		}
	
		
		function listarUnidades(){
			 UnidadeService.listar().
				then(function(f){
					self.unidades = f;	
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
		
			function status(){
				EmprestimoService.status().
					then(function(f){
						self.statusEmprestimo = f;	
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
				
				
				 function funcionario(){
					 FuncionarioService.listarPorUnidade().
						then(function(f){
							self.funcionarios = f;									
							}, function(errResponse){
								sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
						});
					};
				
															
					
					
					function listaTipoEquipamentoEnum() {
						EquipamentoService.listarTipoEquipamento().then(function(f) {
							self.tiposEquipamentoEnum = f;
						}, function(errResponse) {
						});
					};
					
					function equipamentosList() {
						EquipamentoService.listar().then(function(f) {
							self.equipamentos = f;
						}, function(errResponse) {
						});
					};
														
									
						
}
function EmprestimoListarController(EmprestimoService,EquipamentoService,FuncionarioUnidadeService,FuncionarioService, toastr, $rootScope, blockUI, $scope, UnidadeService ){
	var self = this;
	//listar();	
	$scope.equipamentos = [];
	self.emprestimo = {equipamento : $scope.equipamentos}
	
		
	 function listar(){
		 EmprestimoService.listar().
			then(function(f){
				self.emprestimos = f;
				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		
		function equipamentosList() {
			EquipamentoService.listar().then(function(f) {
				self.equipamentos = f;
			}, function(errResponse) {
			});
		};
		
	
		
		self.buscarPorTexto = buscarPorTexto;
		self.totalElementos = {};
		self.totalPaginas = null;
		self.paginaCorrente = 0;
		
		buscarPorTexto('');
		
		     
		    function buscarPorTexto(texto){
		    	$scope.mensagemErro = null;
		    	 blockUI.start();	    	 
		    	 self.paginaCorrente == '0'? self.paginaCorrente = 0 : self.paginaCorrente = self.paginaCorrente - 1;    	 
		    	 EmprestimoService.buscarPorTexto(texto, self.paginaCorrente).
		    	 then(function(e){
		    		 $scope.mensagemErro = null;
		    		 self.emprestimos = e.content;	
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
		
		
		
}
function EmprestimoShowController( $stateParams, $state, EmprestimoService, toastr, $rootScope, $scope){
	
}