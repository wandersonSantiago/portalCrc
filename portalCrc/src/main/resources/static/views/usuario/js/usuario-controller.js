app.controller("UsuarioCadastarController", UsuarioCadastarController);
app.controller("UsuarioEditarController", UsuarioEditarController);
app.controller("UsuarioListarController", UsuarioListarController);
app.controller("UsuarioPermissaoController", UsuarioPermissaoController);


function UsuarioCadastarController( UsuarioService, FuncionarioService, UnidadeService, SetorService, toastr, $rootScope, $scope){
	
	var self = this;
	
	self.submit = submit;
	self.existeLogin = existeLogin;
	listarFuncionarios();
	listarUnidades();
	listarSetores();
	
	 function submit(usuario){
			if(self.senha == self.senhaRepitida){
				self.usuario.senha = self.senha;
				self.usuario.perfilUsuario = self.perfil;
				UsuarioService.salvar(self.usuario).
				then(function(response){
					self.usuario = null;
					}, function(errResponse){
						sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
					});
			}else{			
				sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
			
			}
		};
	

	function existeLogin(login){			
		UsuarioService.existeLogin(login).
			then(function(p){
				self.existe = p;
				if(self.existe == true){
					$scope.userCtrl.usuario.login = null;
					sweetAlert({ timer : 3000,  text :"Usuario já cadastrado",  type : "info", width: 300, higth: 300, padding: 20});
					}				
				}, function(errResponse){
			});
		};
		
	function listarFuncionarios(){
		 FuncionarioService.listar().
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
		
	 function listarSetores(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
		
}		

function UsuarioEditarController($stateParams, $state , UsuarioService, FuncionarioService, SetorService, UnidadeService, toastr, $rootScope, $scope){
	
	var self = this;
	
	var idUsuario = $stateParams.idUsuario;
	listarFuncionarios();
	listarUnidades();
	listarSetores();
	self.submit = submit;
	self.buscarPorId = buscarPorId;
	
	function submit(usuario){		
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			UsuarioService.alterar(self.usuario).
			then(function(response){
				toastr.info("Usuario Salvo!!!")
				self.usuario = null;
				}, function(errResponse){
			sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		}
	};	
	
	function listarFuncionarios(){
		 FuncionarioService.listar().
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
	 function listarSetores(){
		 SetorService.listar().
			then(function(f){
				self.setores = f;
				}, function(errResponse){
					sweetAlert({ timer : 3000,  text : errResponse.data.message,  type : "error", width: 300, higth: 300, padding: 20});
			});
		};	
	function buscarPorId(id){
		if(!id)return;
		UsuarioService.buscarPorId(id).
		then(function(p){
			self.usuario = p;
			}, function(errResponse){
		});
	};

	if(idUsuario){
		self.buscarPorId(idUsuario);		
	}
	
	
}

function UsuarioListarController($stateParams, $state , UsuarioService, toastr, $rootScope, $scope){
	
	var self = this;
		
	listar();
	
	
	 function listar(){
		 UsuarioService.listar().
			then(function(u){				
					self.usuarios = u;			
				}, function(errResponse){
			});
		};		
	
}

function UsuarioPermissaoController($stateParams, $state , UsuarioService, PermissaoService,  toastr, $rootScope, $scope){
	
	var self = this;
		
	//listar();
	
	
	function submit(usuario){		
			self.usuario.senha = self.senha;
			UsuarioService.alterar(self.usuario).
			then(function(response){
				toastr.info("Usuario Salvo!!!")
				self.usuario = null;
				}, function(errResponse){
			sweetAlert({ timer : 3000, text: errResponse.data.message , type : "info", width: 300, higth: 100, padding: 20});
					
			});
	};	
	
	 function listar(){
		 PermissaoService.listar().
			then(function(u){				
					self.usuarios = u;			
				}, function(errResponse){
			});
		};		
		
		
		
	
}