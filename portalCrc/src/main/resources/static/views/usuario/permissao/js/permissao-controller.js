app.controller("PermissaoCadastarController", PermissaoCadastarController);
app.controller("PermissaoEditarController", PermissaoEditarController);
app.controller("PermissaoListarController", PermissaoListarController);


PermissaoCadastarController.$inject = ['PermissaoService', 'FuncionarioService','UnidadeService','SetorService','toastr', '$rootScope', '$scope'];
PermissaoEditarController.$inject = ['$stateParams', '$state', 'PermissaoService','FuncionarioService','SetorService','UnidadeService', 'toastr', '$rootScope', '$scope'];
PermissaoListarController.$inject = ['$stateParams', '$state', 'PermissaoService',  'toastr', '$rootScope', '$scope'];


function PermissaoCadastarController( PermissaoService, FuncionarioService, UnidadeService, SetorService, toastr, $rootScope, $scope){
	
	var self = this;
	
	self.submit = submit;
	listarModulo();
	
	 function submit(permissao){
				PermissaoService.salvar(self.permissao).
				then(function(response){
					self.permissao = null;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});		
		};	
	
	
		
	 function listarModulo(){
		 PermissaoService.modulos().
			then(function(f){
				self.modulos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
		
}		

function PermissaoEditarController($stateParams, $state , PermissaoService, FuncionarioService, SetorService, UnidadeService, toastr, $rootScope, $scope){
	
	var self = this;
	
	var idPermissao = $stateParams.idPermissao;

	self.submit = submit;
	listarModulo();
	
	 function submit(permissao){
				PermissaoService.salvar(self.permissao).
				then(function(response){
					self.permissao = null;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});		
		};	
	
	
		
	 function listarModulo(){
		 PermissaoService.modulos().
			then(function(f){
				self.modulos = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
	
	function buscarPorId(id){
		if(!id)return;
		PermissaoService.buscarPorId(id).
		then(function(p){
			self.permissao = p;
			}, function(errResponse){
		});
	};

	if(idPermissao){
		self.buscarPorId(idPermissao);		
	}
	
	
}

function PermissaoListarController($stateParams, $state , PermissaoService, toastr, $rootScope, $scope){
	
	var self = this;
		
	listar();
	
	
	 function listar(){
		 PermissaoService.listar().
			then(function(u){				
					self.permissaos = u;			
				}, function(errResponse){
			});
		};		
	
}