app.controller("SistemaCadastarController", SistemaCadastarController);
app.controller("SistemaEditarController", SistemaEditarController);
app.controller("SistemaListarController", SistemaListarController);
app.controller("SistemaShowController", SistemaShowController);

SistemaCadastarController.$inject = ['SistemaService', 'CoordenadoriaService', 'buscaCepService', 'toastr', '$rootScope', '$scope'];
SistemaEditarController.$inject = ['$stateParams', '$state', 'SistemaService', 'CoordenadoriaService', 'buscaCepService', 'toastr', '$rootScope', '$scope'];
SistemaListarController.$inject = ['$stateParams', '$state', 'SistemaService', 'toastr', '$rootScope', '$scope'];
SistemaShowController.$inject = ['$stateParams', '$state', 'SistemaService', 'toastr', '$rootScope', '$scope'];

function SistemaCadastarController( SistemaService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	
	var self = this;
	
	self.submit = submit;
	
	

	function submit(sistema) {
		SistemaService.salvar(self.sistema).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.sistema = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	
		
}
function SistemaEditarController($stateParams, $state,SistemaService, CoordenadoriaService, buscaCepService ,toastr, $rootScope, $scope){
	

	var self = this;
	var idSistema = $stateParams.idSistema;
	self.submit = submit;
	
	function submit(sistema) {
		SistemaService.alterar(self.sistema).
			then(function(response){
				toastr.info('Alterado com Sucesso!!!');
				self.sistema = null;
				$state.go('sistema.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
			
	function buscarPorId(id){
			if(!id)return;
			SistemaService.buscarPorId(id).
			then(function(p){
				self.sistema = p;
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
		if(idSistema){
			buscarPorId(idSistema);			
		}
}
function SistemaListarController($stateParams, $state, SistemaService, toastr, $rootScope, $scope){
	
	var self =this;
	
	listar();
	
	 function listar(){
		 SistemaService.listar().
			then(function(f){
				self.sistemas = f;	
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function SistemaShowController( $stateParams, $state, SistemaService, toastr, $rootScope, $scope){
	
}