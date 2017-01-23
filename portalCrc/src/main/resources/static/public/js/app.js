var app = angular.module('crc',['ngAnimate', 'ngCookies','ngRoute', 'toastr','ui.bootstrap','blockUI', 'ngResource', 'ngMaterial', 'ngStorage','ui.utils.masks', 'ui.mask'])

.run(function ($rootScope, $location, usuarioService) {
	 
	var self = this;
		  
	  $rootScope.$on('$locationChangeStart', function () { 
		self.role = function(){
				usuarioService.user().
					then(function(u){
						$rootScope.user = u;
						for(i = 0 ; i < $rootScope.user.usuario.perfilsUsuario.length ; i++ ){	
							
						var	perfil = $rootScope.user.usuario.perfilsUsuario[i];
						
							self.ADMIN(perfil);
							self.role_coordenadoria(perfil);
							self.role_chamadoSuporteTi(perfil);
							self.role_UsuarioChamado(perfil);
							self.role_chamadoSuporteManutencao(perfil);
							self.role_cadastrar_usuario(perfil);
							self.role_cadastrar_telefone(perfil);
							self.role_cadastrar_ramal(perfil);
							self.role_recursos_humanos(perfil);
							self.role_cadastrar_funcionario(perfil);
							self.role_cadastrar_cargo(perfil);
							self.role_cadastrar_setor(perfil);
							self.role_cadastrar_funcao(perfil);
							self.role_cadastrar_equipamento(perfil);
							self.role_cadastrar_ip(perfil);
							self.role_cadastrar_tipoip(perfil);
						}
						}, function(errResponse){
					});
				};
							
				
				self.ADMIN = function(perfil){
					if(perfil == "ADMIN" ){
						$rootScope.ADMIN = true;						
					}
				}
				
				self.role_coordenadoria = function(perfil){
					if(perfil == "COORDENADORIA" ){
						$rootScope.role_coordenadoria = true;						
					}
				}
				
				self.role_chamadoSuporteTi = function(perfil){					
					if(perfil == "CHAMADO_SUPORTE_TI" ){
						$rootScope.role_chamadoSuporteTi = true;
					}
				}
				
				self.role_chamadoSuporteManutencao = function(perfil){
					if(perfil == "CHAMADO_SUPORTE_MANUTENCAO"){
						$rootScope.role_chamadoSuporteManutencao = true;
					}
				}	
				
				self.role_UsuarioChamado = function(perfil){
					if(perfil == "USUARIO_CHAMADO"){
						$rootScope.role_UsuarioChamado = true;
					}					
				}
				
				self.role_cadastrar_usuario = function(perfil){
					if(perfil == "CADASTRAR_USUARIO"){
						$rootScope.role_cadastrar_usuario = true;
					}
				}
				
				self.role_cadastrar_telefone = function(perfil){
					if(perfil == "CADASTRAR_TELEFONE"){
						$rootScope.role_cadastrar_telefone = true;
					}
				}
				
				self.role_cadastrar_ramal = function(perfil){
					if(perfil == "CADASTRAR_RAMAL"){
						$rootScope.role_cadastrar_ramal = true;
					}
				}
				
				self.role_recursos_humanos = function(perfil){
					if(perfil == "RECURSOS_HUMANOS"){
						$rootScope.role_recursos_humanos = true;
					}
				}
				
				self.role_cadastrar_funcionario = function(perfil){
					if(perfil == "CADASTRAR_FUNCIONARIO"){
						$rootScope.role_cadastrar_funcionario = true;
					}
				}
				
				self.role_cadastrar_cargo = function(perfil){
					if(perfil == "CADASTRAR_CARGO"){
						$rootScope.role_cadastrar_cargo = true;
					}
				}
				
				self.role_cadastrar_setor = function(perfil){
					if(perfil == "CADASTRAR_SETOR"){
						$rootScope.role_cadastrar_setor = true;
					}
				}
				
				self.role_cadastrar_funcao = function(perfil){
					if(perfil == "CADASTRAR_FUNCAO"){
						$rootScope.role_cadastrar_funcao = true;
					}
				}
				
				/*self.role_cadastrar_equipamento = function(perfil){
					if(perfil == "CADASTRAR_EQUIPAMENTO"){
						$rootScope.role_cadastrar_equipamento = true;
					}
				}
				
				self.role_cadastrar_ip = function(perfil){
					if(perfil == "CADASTRAR_IP"){
						$rootScope.role_cadastrar_ip = true;
					}
				}
				
				self.role_cadastrar_ip = function(perfil){
					if(perfil == "CADASTRAR_TIPO_IP"){
						$rootScope.role_cadastrar_tipoip = true;
					}
				}*/
				
				
				
				
				 self.role();
	  });
	});