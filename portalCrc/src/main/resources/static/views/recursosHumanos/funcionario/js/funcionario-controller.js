app.controller("FuncionarioCadastarController", FuncionarioCadastarController);
app.controller("FuncionarioEditarController", FuncionarioEditarController);
app.controller("FuncionarioListarController", FuncionarioListarController);
app.controller("FuncionarioShowController", FuncionarioShowController);

FuncionarioCadastarController.$inject = ['FuncionarioService',  'buscaCepService', 'CargoService', 'toastr', '$rootScope', '$scope'];
FuncionarioEditarController.$inject = ['$stateParams', '$state', 'FuncionarioService', 'CargoService', 'buscaCepService', 'toastr', '$rootScope', '$scope'];
FuncionarioListarController.$inject = ['FuncionarioService'];
FuncionarioShowController.$inject = ['$stateParams', '$state', 'FuncionarioService', 'toastr', '$rootScope', '$scope'];

function FuncionarioCadastarController( FuncionarioService,  buscaCepService , CargoService, toastr, $rootScope, $scope){
			var self = this;
			self.findCep = findCep;
			self.submit = submit;
			estadoCivil();
			cargos();
			
		function findCep() {				
				self.cep = $scope.cadFuncCtrl.funcionario.pessoa.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){
					$scope.cadFuncCtrl.funcionario.pessoa.endereco = result;
				}).catch(function error(msg) {
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});				
		    };
		function submit(funcionario) {
				FuncionarioService.salvar(self.funcionario).
				then(function(response){
					toastr.info('Salvo com Sucesso!!!');
					self.funcionario = null;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		 function estadoCivil(){
				 FuncionarioService.estadoCivil().
					then(function(f){
						self.listaEstadoCivil = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
		 function cargos(){
			 CargoService.listar().
				then(function(f){
					self.cargos = f;									
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
}
function FuncionarioEditarController($stateParams, $state , FuncionarioService, CargoService, buscaCepService ,  toastr, $rootScope, $scope){
	
			var self = this;
			var idFuncionario = $stateParams.idFuncionario;
			self.findCep = findCep;
			self.submit = submit;
			estadoCivil();
			cargos();
			
		function findCep() {				
				self.cep = $scope.cadFuncCtrl.funcionario.pessoa.endereco.cep;
				buscaCepService.get({'cep': self.cep}).$promise
				.then(function success(result){
					$scope.cadFuncCtrl.funcionario.pessoa.endereco = result;
				}).catch(function error(msg) {
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});				
		    };
		function submit(funcionario) {
				FuncionarioService.alterar(self.funcionario).
				then(function(response){
					toastr.info('Alterado com Sucesso!!!');
					self.funcionario = null;
					$state.go('funcionario.listar');
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		 function estadoCivil(){
				 FuncionarioService.estadoCivil().
					then(function(f){
						self.listaEstadoCivil = f;									
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
				};
		 function cargos(){
			 CargoService.listar().
				then(function(f){
					self.cargos = f;									
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		function buscarPorId(id){
				if(!id)return;
				FuncionarioService.buscarPorId(id).
				then(function(p){
					p.pessoa.dataNascimento = new Date(p.pessoa.dataNascimento);
					self.funcionario = p;
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
		
			if(idFuncionario){
				buscarPorId(idFuncionario);
				
			}
}
function FuncionarioListarController( FuncionarioService){
			var self = this;
			listar();
			
			function listar(){
				 FuncionarioService.listar().
					then(function(f){
						self.funcionarios = f;				
						}, function(errResponse){
							sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
						});
				};
}
function FuncionarioShowController( FuncionarioService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	
}