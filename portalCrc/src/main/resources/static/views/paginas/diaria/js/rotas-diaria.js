app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider

	
	.when("/diaria/cadastrar",{
		templateUrl : "views/paginas/diaria/cadastrar.html"
	})
		
	.when("/diaria/lista",{
		templateUrl : "views/paginas/diaria/lista.html"
	})
	.when("/diaria/editar/:idDiaria",{
		templateUrl : "views/paginas/diaria/editar.html"
	})
	
	

}]);

