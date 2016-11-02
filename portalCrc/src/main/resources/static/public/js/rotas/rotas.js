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
	
	//UNIDADE
	.when("/unidade/cadastrar",{
		templateUrl : "views/paginas/unidade/cadastrar.html"
	})
		
	.when("/unidade/lista",{
		templateUrl : "views/paginas/unidade/lista.html"
	})
	
	//USUARIO
	
	.when("/usuario/cadastrar",{
		templateUrl : "views/paginas/usuario/cadastrar.html"
	})
		
	.when("/usuario/lista",{
		templateUrl : "views/paginas/usuario/lista.html"
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

