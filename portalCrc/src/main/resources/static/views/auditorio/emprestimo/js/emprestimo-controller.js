app.controller("EmprestimoCadastarController", EmprestimoCadastarController);
app.controller("EmprestimoEditarController", EmprestimoEditarController);
app.controller("EmprestimoListarController", EmprestimoListarController);
app.controller("EmprestimoShowController", EmprestimoShowController);

EmprestimoCadastarController.$inject = ['EmprestimoService','toastr', '$rootScope', '$scope', 'UnidadeService'];
EmprestimoEditarController.$inject = ['$stateParams', '$state', 'EmprestimoService','toastr', '$rootScope', '$scope', 'UnidadeService'];
EmprestimoListarController.$inject = ['EmprestimoService', 'toastr', '$rootScope', '$scope'];
EmprestimoShowController.$inject = ['$stateParams', '$state', 'EmprestimoService', 'toastr', '$rootScope', '$scope'];

function EmprestimoCadastarController( EmprestimoService,toastr, $rootScope, $scope, UnidadeService){
	var self = this;
		self.submit =submit;
		//self.listarEmprestimo = listarEmprestimo;
		//listarEmprestimo();
		status();
		listarUnidades();	
		listaTipoEquipamentoEnum();
		
		function submit() {
			EmprestimoService.salvar(self.emprestimo).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.emprestimo = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		
		function listaTipoEquipamentoEnum() {
			EquipamentoService.listarAtivos().then(function(f) {
				self.equipamentos = f;
			}, function(errResponse) {
			});
		}
		;
		
		
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
function EmprestimoEditarController($stateParams, $state, EmprestimoService, toastr, $rootScope, $scope, UnidadeService){
	var self = this;
		self.submit = submit;
		var idEmprestimo = $stateParams.idEmprestimo;
		listarEmprestimo();
		self.status = status;
		
		function submit(Emprestimo) {
			EmprestimoService.alterar(self.emprestimo).
			then(function(response){
				toastr.info('Alterado com sucesso!!!');
				self.emprestimo = null;
				$state.go('Emprestimo.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
				
		function buscarPorId(id){
			if(!id)return;
			EmprestimoService.buscarPorId(id).
			then(function(p){
				self.Emprestimo = p;
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
				 UnidadeService.status().
					then(function(f){
						self.statusEmprestimo = f;	
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
			
		
		
}
function EmprestimoListarController(EmprestimoService, toastr, $rootScope, $scope ){
	var self = this;
	listar();
	
	 function listar(){
		 EmprestimoService.listar().
			then(function(f){
				self.Emprestimos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function EmprestimoShowController( $stateParams, $state, EmprestimoService, toastr, $rootScope, $scope, UnidadeService){
	
}