app.controller("ModuloCadastarController", ModuloCadastarController);
app.controller("ModuloEditarController", ModuloEditarController);
app.controller("ModuloListarController", ModuloListarController);

ModuloCadastarController.$inject = ['ModuloService', 'toastr', '$rootScope', '$scope'];
ModuloEditarController.$inject = ['$stateParams', '$state', 'ModuloService','toastr', '$rootScope', '$scope'];
ModuloListarController.$inject = ['$stateParams', '$state', 'ModuloService', 'toastr', '$rootScope', '$scope'];

function ModuloCadastarController( ModuloService, toastr, $rootScope, $scope){
	
	var self = this;
	
	self.submit = submit;
	modulos();
	

	function submit(modulo) {
		ModuloService.salvar(self.modulo).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.modulo = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function modulos(){
		 ModuloService.listarTipoModulo().
			then(function(f){
				self.modulos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
	
		
}
function ModuloEditarController($stateParams, $state,ModuloService, toastr, $rootScope, $scope){
	

	var self = this;
	var idModulo = $stateParams.idModulo;
	self.submit = submit;
	modulos();
	

	function submit(modulo) {
		ModuloService.alterar(self.modulo).
			then(function(response){
				toastr.info('Alterado com Sucesso!!!');
				self.modulo = null;
				$state.go('modulo.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
	function modulos(){
		 ModuloService.listarTipoModulo().
			then(function(f){
				self.modulos = f;				
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
	
		
	function buscarPorId(id){
			if(!id)return;
			ModuloService.buscarPorId(id).
			then(function(p){
				self.modulo = p;
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
		});
	};
	
		if(idModulo){
			buscarPorId(idModulo);			
		}
}
function ModuloListarController($stateParams, $state, ModuloService, toastr, $rootScope, $scope){
	
	var self =this;
	
	listar();
	
	 function listar(){
		 ModuloService.listar().
			then(function(f){
				self.modulos = f;	
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}