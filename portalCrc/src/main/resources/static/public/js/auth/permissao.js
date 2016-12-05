app.controller('permissao', function($scope, $location, toastr, $rootScope, usuarioService, $http, $routeParams){
	
	var self = this;
	
	
	self.role = function(){
		usuarioService.user().
			then(function(u){
				$rootScope.user = u;
				
				for(i = 0 ; i < $rootScope.user.usuario.perfilsUsuario.length ; i++ ){	
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "COORDENADORIA"){
						$rootScope.role_coordenadoria = true;
					}else{
						if($location.path() == '/coordenadoria/cadastrar' || $location.path() == '/coordenadoria/editar/:idCoordenadoria' ||
								$location.path() == '/secretaria/cadastrar' || $location.path() == '/secretaria/editar/:idCoordenadoria' ||
								$location.path() == '/unidade/cadastrar' || $location.path() == '/unidade/editar/:idCoordenadoria'   ){
							$location.path('/semAcesso');
							}						
						}
					
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "CHAMADO_SUPORTE_TI"){
						$rootScope.role_chamadoSuporteTi = true;
					}else{
						if($location.path() == '/chamado/informatica/suporte/lista' || $location.path() == '/chamado/informatica/suporte/atendimento/:idChamadoTi'){
							$location.path('/semAcesso');
							}						
					}
										
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "CHAMADO_SUPORTE_MANUTENCAO"){
						$rootScope.role_chamadoSuporteManutencao = true;
					}else{
						if($location.path() == '/chamado/manutencao/suporte/lista' || $location.path() == '/chamado/manutencao/suporte/atendimento/:idChamadoTi'){
							$location.path('/semAcesso');
							}						
					}
					
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "USUARIO_CHAMADO"){
						$rootScope.role_UsuarioChamado = true;
					}else{
						if($location.path() == '/chamado/manutencao/cadastrar' || $location.path() == '/chamado/manutencao/lista' ||
							$location.path() == '/chamado/manutencao/atendimento/:idChamadoManutencao' || $location.path() == '/chamado/informatica/cadastrar' 
								|| $location.path() == '/chamado/informatica/lista' || $location.path() == "/chamado/informatica/atendimento/:idChamadoTi"){
							$location.path('/semAcesso');
							}						
					}
					
					
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "CADASTRAR_USUARIO"){
						$rootScope.role_cadastrar_usuario = true;
					}else{
						if($location.path() == '/usuario/cadastrar' || $location.path() == '/usuario/lista' ||
							$location.path() == '/usuario/editar/:idUsuario'){
							$location.path('/semAcesso');
							}						
					}
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "CADASTRAR_TELEFONE"){
						$rootScope.role_cadastrar_telefone = true;
					}else{
						if($location.path() == '/unidade/telefone/cadastrar' || $location.path() == '/unidade/telefone/editar/:idTelefone' 	){
							$location.path('/semAcesso');
							}						
					}
					
					if($rootScope.user.usuario.perfilsUsuario[i] == "CADASTRAR_RAMAL"){
						$rootScope.role_cadastrar_ramal = true;
					}else{
						if($location.path() == '/recursosHumanos/setor/ramal/cadastrar' || $location.path() == '/recursosHumanos/setor/ramal/editar/:idSetor'){
							$location.path('/semAcesso');
							}						
					}
					
				}
				}, function(errResponse){
			});
		};
		
	
});