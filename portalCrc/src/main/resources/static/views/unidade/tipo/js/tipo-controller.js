app.controller("TipoCadastarController", TipoCadastarController);
app.controller("TipoEditarController", TipoEditarController);
app.controller("TipoListarController", TipoListarController);


TipoCadastarController.$inject = ['TipoService', 'SecretariaService', 'toastr', '$rootScope', '$scope'];
TipoEditarController.$inject = ['$stateParams', '$state', 'TipoService', 'SecretariaService', 'toastr', '$rootScope', '$scope'];
TipoListarController.$inject = ['$stateParams', '$state', 'TipoService', 'toastr', '$rootScope', '$scope'];


function TipoCadastarController( TipoService, SecretariaService, toastr, $rootScope, $scope){
	
	var  self = this;
	
	self.submit = submit;
	listarSecretaria();
	
	
	function submit(tipoUnidade) {
		TipoService.salvar(self.tipoUnidade).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.tipoUnidade = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		}
		
	 function listarSecretaria(){
		 SecretariaService.listar().
			then(function(f){
				self.secretarias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}

function TipoEditarController( $stateParams, $state,TipoService, SecretariaService, toastr, $rootScope, $scope){
	
    var  self = this;
	
	self.submit = submit;
	listarSecretaria();
	var idTipo = $stateParams.idTipo;
	
	function submit(tipoUnidade) {
		TipoService.salvar(self.tipoUnidade).
			then(function(response){
				toastr.info("Alterado com Sucesso!!!");
				self.tipoUnidade = null;
				$state.go('tipo.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		}
		
	 function listarSecretaria(){
		 SecretariaService.listar().
			then(function(f){
				self.secretarias = f;			
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		function buscarPorId(id){
			if(!id)return;
			TipoService.buscarPorId(id).
			then(function(p){
				self.tipoUnidade = p;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
		   });
		};

		if(idTipo){
			buscarPorId(idTipo);		
		}

}

function TipoListarController( $stateParams, $state,TipoService, toastr, $rootScope, $scope){
	
	
var self = this;	
	
	listar();	

	 function listar(){
		 TipoService.listar().
			then(function(f){
				self.tipos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
}