app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
	
	.when('/home', {
		templateUrl : "views/home.html"
	})
	.when('/404', {
		templateUrl : "views/erros/404.html"
	})
		.when('/login', {
		templateUrl : "views/login.html"
	})
	
	.when('/semAcesso', {
		templateUrl : "views/erros/permissao.html"
	})
	
	//SECRETARIA
	
	.when("/secretaria/cadastrar",{
		templateUrl : "views/paginas/secretaria/cadastrar.html"
	})
		
	.when("/secretaria/lista",{
		templateUrl : "views/paginas/secretaria/lista.html"
	})
	.when("/secretaria/editar/:idSecretaria",{
		templateUrl : "views/paginas/secretaria/editar.html"
	})
	
	//COORDENADORIA
	
	.when("/coordenadoria/cadastrar",{
		templateUrl : "views/paginas/coordenadoria/cadastrar.html"
	})
		
	.when("/coordenadoria/lista",{
		templateUrl : "views/paginas/coordenadoria/lista.html"
	})
	.when("/coordenadoria/editar/:idCoordenadoria",{
		templateUrl : "views/paginas/coordenadoria/editar.html"
	})
	
	//UNIDADE
	.when("/unidade/cadastrar",{
		templateUrl : "views/paginas/unidade/cadastrar.html"
	})
		
	.when("/unidade/lista",{
		templateUrl : "views/paginas/unidade/lista.html"
	})
	.when("/unidade/editar/:idUnidade",{
		templateUrl : "views/paginas/unidade/editar.html"
	})
	
	//Tipo
		.when("/unidade/tipo/cadastrar",{
		templateUrl : "views/paginas/unidade/tipo/cadastrar.html"
	})
		
	.when("/unidade/tipo/lista",{
		templateUrl : "views/paginas/unidade/tipo/lista.html"
	})
	
	.when("/unidade/tipo/editar/:idTipoUnidade",{
		templateUrl : "views/paginas/unidade/tipo/editar.html"
	})
	
	
		
	//TELEFONE
	
	.when("/unidade/telefone/cadastrar",{
		templateUrl : "views/paginas/unidade/telefone/cadastrar.html"
	})
		
	.when("/unidade/telefone/lista",{
		templateUrl : "views/paginas/unidade/telefone/lista.html"
	})
	
	.when("/unidade/telefone/editar/:idTelefone",{
		templateUrl : "views/paginas/unidade/telefone/editar.html"
	})
	
	//USUARIO
	
	.when("/usuario/cadastrar",{
		templateUrl : "views/paginas/usuario/cadastrar.html"
	})
		
	.when("/usuario/lista",{
		templateUrl : "views/paginas/usuario/lista.html"
	})
	.when("/usuario/editar/:idUsuario",{
		templateUrl : "views/paginas/usuario/editar.html"
	})
	
	
	
	
	//RECURSOS HUMANOS
	
	//Cargo
	.when("/recursosHumanos/cargo/cadastrar",{
		templateUrl : "views/paginas/recursosHumanos/cargo/cadastrar.html"
	})
		
	.when("/recursosHumanos/cargo/lista",{
		templateUrl : "views/paginas/recursosHumanos/cargo/lista.html"
	})
	
	.when("/recursosHumanos/cargo/editar/:idCargo",{
		templateUrl : "views/paginas/recursosHumanos/cargo/editar.html"
	})
	
	//Setor
	.when("/recursosHumanos/setor/cadastrar",{
		templateUrl : "views/paginas/recursosHumanos/setor/cadastrar.html"
	})
		
	.when("/recursosHumanos/setor/lista",{
		templateUrl : "views/paginas/recursosHumanos/setor/lista.html"
	})
	
	.when("/recursosHumanos/setor/editar/:idSetor",{
		templateUrl : "views/paginas/recursosHumanos/setor/editar.html"
	})
	
	//Função
	
	.when("/recursosHumanos/funcao/cadastrar",{
		templateUrl : "views/paginas/recursosHumanos/funcao/cadastrar.html"
	})
		
	.when("/recursosHumanos/funcao/lista",{
		templateUrl : "views/paginas/recursosHumanos/funcao/lista.html"
	})
	
	.when("/recursosHumanos/funcao/editar/:idFuncao",{
		templateUrl : "views/paginas/recursosHumanos/funcao/editar.html"
	})
	
	
			//ramal
	.when("/recursosHumanos/setor/ramal/cadastrar",{
		templateUrl : "views/paginas/recursosHumanos/setor/ramal/cadastrar.html"
	})
		
	.when("/recursosHumanos/setor/ramal/lista",{
		templateUrl : "views/paginas/recursosHumanos/setor/ramal/lista.html"
	})
	
	.when("/recursosHumanos/setor/ramal/editar/:idSetor",{
		templateUrl : "views/paginas/recursosHumanos/setor/ramal/editar.html"
	})
	
	
	//Funcionario
	.when("/recursosHumanos/funcionario/cadastrar",{
		templateUrl : "views/paginas/recursosHumanos/funcionario/cadastrar.html"
	})
		
	.when("/recursosHumanos/funcionario/lista",{
		templateUrl : "views/paginas/recursosHumanos/funcionario/lista.html"
	})
	
	.when("/recursosHumanos/funcionario/editar/:idFuncionario",{
		templateUrl : "views/paginas/recursosHumanos/funcionario/editar.html"
	})
	.when("/recursosHumanos/funcionario/unidade/:idFuncionario",{
		templateUrl : "views/paginas/recursosHumanos/funcionario/atribuirUnidade.html"
	})
	
	//==================CHAMADO=================================================
	//ChamadoManutencao
	
	.when("/chamado/manutencao/cadastrar",{
		templateUrl : "views/paginas/chamado/manutencao/cadastrar.html"
	})
		
	.when("/chamado/manutencao/lista",{
		templateUrl : "views/paginas/chamado/manutencao/lista.html"
	})
	
	.when("/chamado/manutencao/atendimento/:idChamadoManutencao",{
		templateUrl : "views/paginas/chamado/manutencao/atendimento.html"
	})
	
	.when("/chamado/manutencao/suporte/atendimento/:idChamadoManutencao",{
		templateUrl : "views/paginas/chamado/manutencao/suporte/atendimento.html"
	})
	
	.when("/chamado/manutencao/suporte/lista",{
		templateUrl : "views/paginas/chamado/manutencao/suporte/lista.html"
	})
	
	.when("/chamado/manutencao/editar/:idChamadoManutencao",{
		templateUrl : "views/paginas/chamado/manutencao/editar.html"
	})
	//Chamado Tecnologia da Informação
	
	.when("/chamado/informatica/cadastrar",{
		templateUrl : "views/paginas/chamado/informatica/cadastrar.html"
	})
		
	.when("/chamado/informatica/lista",{
		templateUrl : "views/paginas/chamado/informatica/lista.html"
	})
	
	.when("/chamado/informatica/suporte/lista",{
		templateUrl : "views/paginas/chamado/informatica/suporte/lista.html"
	})
	

	.when("/chamado/informatica/atendimento/:idChamadoTi",{
		templateUrl : "views/paginas/chamado/informatica/atendimento.html"
	})
	
	.when("/chamado/informatica/suporte/atendimento/:idChamadoTi",{
		templateUrl : "views/paginas/chamado/informatica/suporte/atendimento.html"
	})
	
	//Modulo Controle De IP ///////////////////////////////////////////////////////
	 //Sub-modulo equipamento
	
	.when("/controleIp/equipamento/cadastrar",{
		templateUrl : "views/paginas/controleIp/equipamento/cadastrar.html"
	})
	
	.when("/controleIp/equipamento/lista",{
		templateUrl : "views/paginas/controleIp/equipamento/lista.html"
	})
	
	 //Sub-modulo ip
	.when("/controleIp/ip/cadastrar",{
		templateUrl : "views/paginas/controleIp/ip/cadastrar.html"
	})
	
	.when("/controleIp/ip/lista",{
		templateUrl : "views/paginas/controleIp/ip/lista.html"
	})
	
	.when("/controleIp/ip/editar/:idIp",{
		templateUrl : "views/paginas/controleIp/ip/editar.html"
	})
	
	
	//Sub-modulo Tipo Ip
	.when("/controleIp/tipoip/cadastrar",{
		templateUrl : "views/paginas/controleIp/tipoip/cadastrar.html"
	})
	
	.when("/controleIp/tipoip/lista",{
		templateUrl : "views/paginas/controleIp/tipoip/lista.html"
	})
	
	.when("/controleIp/tipoip/editar/:idTipoIp",{
		templateUrl : "views/paginas/controleIp/tipoip/editar.html"
	})
	
	//PUBLICO
	.when("/publico/telefones", {
		templateUrl : "views/paginas/publico/telefones.html"
	})
	.when("/publico/ramal", {
		templateUrl : "views/paginas/publico/ramal.html"
	})
	.when('/',{
		redirectTo : "/home"
	})
	

	.otherwise({
			redirectTo : "/404"
		});
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]).run(function(auth) {

auth.init('/', '/login', '/logout');

});

