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

