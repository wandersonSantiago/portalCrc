app.controller("IpCadastarController", IpCadastarController);
app.controller("IpEditarController", IpEditarController);
app.controller("IpListarController", IpListarController);
app.controller("IpListarAtivosController", IpListarAtivosController);
app.controller("IpListarInativosController", IpListarInativosController);
app.controller("IpShowController", IpShowController);
app.controller("IpCMDController", IpCMDController);

IpCadastarController.$inject = ['TipoIpService','IpService',  'toastr', '$rootScope', '$scope'];
IpEditarController.$inject = ['$stateParams', '$state', 'IpService', 'toastr', '$rootScope', '$scope'];
IpListarController.$inject = ['TipoIpService','$stateParams', '$state', 'IpService', 'toastr', '$rootScope', '$scope'];
IpShowController.$inject = ['$stateParams', '$state', 'IpService', 'toastr', '$rootScope', '$scope'];
IpCMDController.$inject = ['$stateParams', '$state', 'IpService', 'toastr', '$rootScope', '$scope'];

function IpCadastarController(TipoIpService, IpService, toastr, $rootScope, $scope){
	var self = this;
	
	self.submit = submit;
	listarTipo();
	
	function submit(ip) {
		concatenaIp();
		IpService.salvar(self.ip).
			then(function(response){
				toastr.info("Salvo com Sucesso!!!");
				self.ip = null;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};
		concatenaIp = function() {
			self.ip.numero = $scope.classeIp + "." + $scope.redeIp + "." + $scope.rangeIp + ".";			
		}		
		
		 function listarTipo(){
			 TipoIpService.listar().
				then(function(f){
					self.tipoIps = f;				
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
	
}
function IpEditarController($stateParams, $state, IpService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idIp = $stateParams.idIp;
	
	function submit(ip) {
		IpService.alterar(self.ip).
		then(function(response){
			toastr.info("Alterado com Sucesso!!!");
			self.ip = null;
			$state.go('ip.listar');
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function buscarPorId(id){
		if(!id)return;
		IpService.buscarPorId(id).
		then(function(p){
			self.ip = p;
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idIp){
		buscarPorId(idIp);		
	};
	
	
}
function IpListarController(TipoIpService, $stateParams, $state, IpService, toastr, $rootScope, $scope){
	var self = this;
	self.alterar =  alterar;
	listarTodos();
	
	 function listarTodos(){
		 IpService.listar().
			then(function(f){
				self.ips = f;	
				$rootScope.qtdIps = f.length;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
		};				
		
		function alterar(ip) {		
			IpService.alterar(ip).
			then(function(response){		
				listar();
				}, function(errResponse){
			});
		}
		
		 function listarTipo(){
			 TipoIpService.listar().
				then(function(f){
					self.tipoIps = f;	
					$rootScope.qtdTipoIps = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
}

function IpListarAtivosController(TipoIpService, $stateParams, $state, IpService, toastr, $rootScope, $scope){
	var self = this;
	self.alterar =  alterar;
	listarTipo();
	listarAtivo();
	
	
		function listarAtivo(){
			 IpService.listarIpAtivo().
				then(function(f){
					self.ips = f;	
					$rootScope.qtdIpsAtivos = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
		
		function alterar(ip) {		
			IpService.alterar(ip).
			then(function(response){		
				listar();
				}, function(errResponse){
			});
		}
		
		 function listarTipo(){
			 TipoIpService.listar().
				then(function(f){
					self.tipoIps = f;	
					$rootScope.qtdTipoIps = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
}
function IpListarInativosController(TipoIpService, $stateParams, $state, IpService, toastr, $rootScope, $scope){
	var self = this;
	self.alterar =  alterar;
	listarInativo();
	
	
		function listarInativo(){
			 IpService.listarIpInativo().
				then(function(f){
					self.ips = f;	
					$rootScope.qtdIpsInativos = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};	
		function alterar(ip) {		
			IpService.alterar(ip).
			then(function(response){		
				listar();
				}, function(errResponse){
			});
		}
		
		 function listarTipo(){
			 TipoIpService.listar().
				then(function(f){
					self.tipoIps = f;	
					$rootScope.qtdTipoIps = f.length;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
				});
			};
}

function IpShowController( $stateParams, $state, IpService, toastr, $rootScope, $scope){
	
}

function IpCMDController($stateParams, $state, IpService, toastr, $rootScope, $scope){
	
	var self = this;
	self.submit = submit;
	var idIp = $stateParams.idIp;

	var retorno = [];
	self.comando = {
			host : '',
			executar : '',
			retorno : retorno,
			ip : ''
	}
	
	$scope.list =[];
	
	function submit() {
		addList(self.comando);
		self.comando.retorno = [];
		IpService.cmdExecuter(self.comando).
		then(function(response){	
			self.comando = response;
			self.comando.executar = null;
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	function host() {
		self.comando.executar ='hostname';
		IpService.host(self.comando).
		then(function(response){	
			self.comando = response;
			self.comando.retorno = [];
			self.comando.executar = null;
			}, function(errResponse){
				sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};
	
	
	function addList(response){
		if(response.retorno.length < 1){
			return;
		}
		$scope.list.push({
			host     : response.host,
			executar : response.executar,
			resposta : response.retorno,
			data     : new Date()
		});
	}
	
	function buscarPorId(id){
		if(!id)return;
		IpService.buscarPorId(id).
		then(function(p){
			self.ip = p;
			self.comando.ip = p.numero;
			host();
	}, function(errResponse){
		sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "info", width: 300, higth: 300, padding: 20});
			});
	};

	if(idIp){
		buscarPorId(idIp);		
	};
	
	
}