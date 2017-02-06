app.controller('usuarioController', function($scope, toastr, $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
		
	var idUsuario = $routeParams.idUsuario;
	$scope.listaUsuario = [];	
	
	$scope.perfil = function(){
			usuarioService.perfil().
				then(function(u){
					$scope.perfils = u;
					}, function(errResponse){
				});
			};
	
	self.user = function(){
		usuarioService.user().
			then(function(u){
				$rootScope.user = u;
				}, function(errResponse){
			});
		};

	
	
	self.altera = function(usuario){
		
		if(self.senha == self.senhaRepitida){
			usuario.senha = self.senha;
			usuarioService.altera(usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		
		}
	}
	self.alteraEmpreendimento = function(usuario){
			usuario.senha = null;
			usuarioService.altera(usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		
	}
	//self.perfil = ['COORDENADORIA']; 
	 self.salva = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			self.usuario.perfilUsuario = self.perfil;
			usuarioService.salva(self.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		
		}
	}
	 
	
	
	 self.lista = function(){
		 usuarioService.lista().
			then(function(u){
				if(u.ativo == true){
					u.ativo = "ativo";
					self.usuarios = u;
				}else{
					u.ativo = "inativo";
					self.usuarios = u;
				}
				}, function(errResponse){
			});
		};
	
		self.buscaPorId = function(id){
			if(!id)return;
			usuarioService.buscaPorId(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};

		if(idUsuario){
			self.buscaPorId(idUsuario);
			
		}
		self.existeLogin = function(login){			
			usuarioService.existeLogin(login).
			then(function(p){
				self.existe = p;
				if(self.existe == true){
					$scope.userCtrl.usuario.login = null;
					sweetAlert({ timer : 3000,  text :"Usuario já cadastrado",  type : "info", width: 300, higth: 300, padding: 20});
					
				}
				
				}, function(errResponse){
			});
		};
	
});