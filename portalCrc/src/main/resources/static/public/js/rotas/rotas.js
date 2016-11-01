app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider
	
	.when('/', {
		templateUrl : "views/home.html"
	})
	
	.when("/unidade/cadastrar",{
		templateUrl : "views/paginas/unidade/cadastrar.html"
	})
		
	.when("/unidade/lista",{
		templateUrl : "views/paginas/unidade/lista.html"
	})
	
	.otherwise({
		redirectTo : "/"
	})
}]);

