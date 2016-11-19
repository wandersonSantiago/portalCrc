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
			console.log($rootScope.user);
				}, function(errResponse){
			});
		};

	
	
	self.altera = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
			usuarioService.altera(self.usuario).
			then(function(response){
				self.usuario = null;
				}, function(errResponse){
			});
		}else{			
			sweetAlert({ timer : 3000, text: "senha não coencidem, digite novamente" , type : "error", width: 300, higth: 100, padding: 20});
		
		}
	}
	
	 self.salva = function(usuario){
		if(self.senha == self.senhaRepitida){
			self.usuario.senha = self.senha;
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
				console.log(self.usuarios.ativo);
				}, function(errResponse){
			});
		};
		//busca a usuario atraves do id
		self.buscaPorId = function(id){
			if(!id)return;
			usuarioService.buscaPorId(id).
			then(function(p){
				self.usuario = p;
				}, function(errResponse){
			});
		};
	//verifica se o params esta com o ide executa o metodo de busca 	
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