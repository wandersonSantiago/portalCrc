app.controller("SetorCadastarController", SetorCadastarController);
app.controller("SetorEditarController", SetorEditarController);
app.controller("SetorListarController", SetorListarController);
app.controller("SetorShowController", SetorShowController);

SetorCadastarController.$inject = ['SetorService',  'UnidadeService','toastr', '$rootScope', '$scope'];
SetorEditarController.$inject = ['$stateParams', '$state', 'SetorService', 'UnidadeService' , 'toastr', '$rootScope', '$scope'];
SetorListarController.$inject = ['SetorService', 'toastr', '$rootScope', '$scope'];
SetorShowController.$inject = ['$stateParams', '$state', 'SetorService', 'toastr', '$rootScope', '$scope'];

function SetorCadastarController( SetorService, UnidadeService ,toastr, $rootScope, $scope){
	var self = this;
		self.submit =submit;
		self.listarTipoSetor = listarTipoSetor;
		listarTipoSetor();
		tiposDeUnidade();
		
		
		function submit(setor) {
			SetorService.salvar(self.setor).
			then(function(response){
				toastr.info('Salvo com Sucesso!!!');
				self.setor = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		function listarTipoSetor(){
			 SetorService.listarTipoSetor().
				then(function(f){
					self.setores = f;		
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		function tiposDeUnidade(){
			 UnidadeService.listarTipoUnidade().
				then(function(f){
					self.unidades = f;				
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
		
}
function SetorEditarController( $stateParams, $state, SetorService, UnidadeService, toastr, $rootScope, $scope){
	var self = this;
		self.submit = submit;
		var idSetor = $stateParams.idSetor;
		listarTipoSetor();
		tiposDeUnidade();
		
		
		function submit(setor) {
			SetorService.alterar(self.setor).
			then(function(response){
				toastr.info('Alterado com sucesso!!!');
				self.setor = null;
				$state.go('setor.listar');
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		function listarTipoSetor(){
			 SetorService.listarTipoSetor().
				then(function(f){
					self.setores = f;		
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
			
		function tiposDeUnidade(){
			 UnidadeService.listarTipoUnidade().
				then(function(f){
					self.unidades = f;				
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
				});
			};
		
		function buscarPorId(id){
			if(!id)return;
			SetorService.buscarPorId(id).
			then(function(p){
				self.setor = p;
		}, function(errResponse){
			sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
		
		if(idSetor){
			buscarPorId(idSetor);			
		}
}
function SetorListarController( SetorService, toastr, $rootScope, $scope){
	var self = this;
	listar();
	
	 function listar(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};
}
function SetorShowController( $stateParams, $state, SetorService, toastr, $rootScope, $scope){
	
}