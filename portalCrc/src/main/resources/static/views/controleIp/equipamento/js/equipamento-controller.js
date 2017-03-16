app.controller("SecretariaCadastarController", SecretariaCadastarController);
app.controller("SecretariaEditarController", SecretariaEditarController);
app.controller("SecretariaListarController", SecretariaListarController);
app.controller("SecretariaShowController", SecretariaShowController);

SecretariaCadastarController.$inject = ['SecretariaService',  'toastr', '$rootScope', '$scope'];
SecretariaEditarController.$inject = ['$stateParams', '$state', 'SecretariaService', 'toastr', '$rootScope', '$scope'];
SecretariaListarController.$inject = ['$stateParams', '$state', 'SecretariaService', 'toastr', '$rootScope', '$scope'];
SecretariaShowController.$inject = ['$stateParams', '$state', 'SecretariaService', 'toastr', '$rootScope', '$scope'];

function SecretariaCadastarController( SecretariaService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	
	function submit(secretaria) {
		SecretariaService.salvar(self.secretaria).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.secretaria = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
	
}
function SecretariaEditarController($stateParams, $state, SecretariaService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idSecretaria = $stateParams.idSecretaria;
	
	function submit(secretaria) {
		SecretariaService.alterar(self.secretaria).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.secretaria = null;
			$state.go('secretaria.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		SecretariaService.buscarPorId(id).
		then(function(p){
			self.secretaria = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idSecretaria){
		buscarPorId(idSecretaria);		
	};
	
	
}
function SecretariaListarController($stateParams, $state, SecretariaService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 SecretariaService.listar().
			then(function(f){
				self.secretarias = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}
function SecretariaShowController( $stateParams, $state, SecretariaService, toastr, $rootScope, $scope){
	
}