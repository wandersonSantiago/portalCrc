app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider

	
	.when("/diaria/cadastrar",{
		templateUrl : "views/paginas/diaria/cadastrar.html"
	})
		
	.when("/diaria/lista/geral",{
		templateUrl : "views/paginas/diaria/lista.html"
	})
	.when("/diaria/lista/coordenadoria",{
		templateUrl : "views/paginas/diaria/listaCoordenadoria.html"
	})
	.when("/diaria/lista/unidade",{
		templateUrl : "views/paginas/diaria/listaUnidade.html"
	})
	.when("/diaria/editar/:idDiaria",{
		templateUrl : "views/paginas/diaria/editar.html"
	})
	
	

}]);

