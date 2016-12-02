app.controller('permissao', function($scope, toastr, $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
	
	
	self.role = function(){
		usuarioService.user().
			then(function(u){
				$rootScope.user = u;
				
				for(i = 0 ; i < $rootScope.user.usuario.perfilsUsuario.length ; i++ ){	
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "COORDENADORIA"){
						$rootScope.role_coordenadoria = true;
					}
					if($rootScope.user.usuario.perfilsUsuario[i] == "CHAMADO_SUPORTE_TI"){
						$rootScope.role_chamadoSuporteTi = true;
					}
					if($rootScope.user.usuario.perfilsUsuario[i] == "CHAMADO_SUPORTE_MANUTENCAO"){
						$rootScope.role_chamadoSuporteManutencao = true;
					}
					if($rootScope.user.usuario.perfilsUsuario[i] == "USUARIO_CHAMADO"){
						$rootScope.role_UsuarioChamado = true;
					}
					
				}
				}, function(errResponse){
			});
		};
		
	
});