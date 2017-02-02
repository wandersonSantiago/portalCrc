app.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
	$routeProvider

	
	.when("/diaria/cadastrar",{
		templateUrl : "views/paginas/diaria/cadastrar.html"
	})
		
	.when("/diaria/em-aberto",{
		templateUrl : "views/paginas/diaria/em-aberto.html"
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
	
	.when("/diaria/relatorio",{
		templateUrl : "views/paginas/diaria/relatorio/relatorio.html"
	})
	
	
	
	.when("/diaria/item/cadastrar/:idDiaria",{
		templateUrl : "views/paginas/diaria/item/cadastrar.html"
	})
		
	.when("/diaria/item/lista/geral/:idDiariaGeral",{
		templateUrl : "views/paginas/diaria/item/lista.html"
	})
	.when("/diaria/item/lista/coordenadoria/:idDiariaCoordenadoria",{
		templateUrl : "views/paginas/diaria/item/listaCoordenadoria.html"
	})
	.when("/diaria/item/lista/unidade/:idDiariaUnidade",{
		templateUrl : "views/paginas/diaria/item/listaUnidade.html"
	})
	.when("/diaria/item/editar/:idDiaria",{
		templateUrl : "views/paginas/diaria/item/editar.html"
	})

}]);

