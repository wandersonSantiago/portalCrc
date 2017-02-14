var app = angular.module('crc',['ngAnimate','chart.js', 'ngCookies','ngRoute', 'toastr','ui.bootstrap','blockUI', 'ngResource', 'ngMaterial', 'ngStorage','ui.utils.masks', 'ngJsonExportExcel'])

.run(function ($rootScope, $location, usuarioService) {
	 
	var self = this;
		  
	  $rootScope.$on('$locationChangeStart', function () { 
		self.role = function(){
				usuarioService.user().
					then(function(u){
						$rootScope.user = u;
						for(i = 0 ; i < $rootScope.user.usuario.perfilsUsuario.length ; i++ ){	
							
							$rootScope.perfil = $rootScope.user.usuario.perfilsUsuario[i];
						
							self.ADMIN($rootScope.perfil);
							self.role_coordenadoria($rootScope.perfil);
							self.role_chamadoSuporteTi($rootScope.perfil);
							self.role_UsuarioChamado($rootScope.perfil);
							self.role_chamadoSuporteManutencao($rootScope.perfil);
							self.role_cadastrar_usuario($rootScope.perfil);
							self.role_cadastrar_telefone($rootScope.perfil);
							self.role_cadastrar_ramal($rootScope.perfil);
							self.role_recursos_humanos($rootScope.perfil);
							self.role_cadastrar_funcionario($rootScope.perfil);
							self.role_cadastrar_cargo($rootScope.perfil);
							self.role_cadastrar_setor($rootScope.perfil);
							self.role_cadastrar_funcao($rootScope.perfil);
							/*self.role_cadastrar_equipamento(perfil);
							self.role_cadastrar_ip(perfil);
							self.role_cadastrar_tipoip(perfil);*/
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
					if(perfil == "COORDENADORIA" || perfil == "ADMIN" ){
						$rootScope.role_coordenadoria = true;						
					}
				}
				
				self.role_chamadoSuporteTi = function (perfil){					
					if(perfil == "CHAMADO_SUPORTE_TI" || perfil == "ADMIN" ){
						$rootScope.role_chamadoSuporteTi = true;
					}
				}
				
				self.role_chamadoSuporteManutencao = function(perfil){
					if(perfil == "CHAMADO_SUPORTE_MANUTENCAO" || perfil == "ADMIN" ){
						$rootScope.role_chamadoSuporteManutencao = true;
					}
				}	
				
				self.role_UsuarioChamado = function(perfil){
					if(perfil == "USUARIO_CHAMADO" || perfil == "ADMIN" ){
						$rootScope.role_UsuarioChamado = true;
					}					
				}
				
				self.role_cadastrar_usuario = function(perfil){
					if(perfil == "CADASTRAR_USUARIO" || perfil == "ADMIN" ){
						$rootScope.role_cadastrar_usuario = true;
					}
				}
				
				self.role_cadastrar_telefone = function(perfil){
					if(perfil == "CADASTRAR_TELEFONE" || perfil == "ADMIN" ){
						$rootScope.role_cadastrar_telefone = true;
					}
				}
				
				self.role_cadastrar_ramal = function(perfil){
					if(perfil == "CADASTRAR_RAMAL" || perfil == "ADMIN" ){
						$rootScope.role_cadastrar_ramal = true;
					}
				}
				
				self.role_recursos_humanos = function(perfil){
					if(perfil == "RECURSOS_HUMANOS" || perfil == "ADMIN" ){
						$rootScope.role_recursos_humanos = true;
					}
				}
				
				self.role_cadastrar_funcionario = function(perfil){
					if(perfil == "CADASTRAR_FUNCIONARIO" || perfil == "ADMIN" ){
						$rootScope.role_cadastrar_funcionario = true;
					}
				}
				
				self.role_cadastrar_cargo = function(perfil){
					if(perfil == "CADASTRAR_CARGO" || perfil == "ADMIN" ){
						$rootScope.role_cadastrar_cargo = true;
					}
				}
				
				self.role_cadastrar_setor = function(perfil){
					if(perfil == "CADASTRAR_SETOR" || perfil == "ADMIN" ){
						$rootScope.role_cadastrar_setor = true;
					}
				}
				
				self.role_cadastrar_funcao = function(perfil){
					if(perfil == "CADASTRAR_FUNCAO" || perfil == "ADMIN" ){
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