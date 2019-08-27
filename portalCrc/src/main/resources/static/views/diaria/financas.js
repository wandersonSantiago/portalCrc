app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		
	.state('financas', {
		abstract : true,	
		url : '/financas',
		templateUrl : 'views/diaria/diaria.index.html',
		redirectTo : 'diaria.listar',
		ncyBreadcrumb: {
			parent: 'home.menu',
			    label: 'Financas'
			  }
	})
	
	.state('financas.menu', {
			url : "/menu",
			templateUrl : "views/diaria/diaria.list.html",
			controller : "DiariaListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'financas',
				    label: 'Finanças / Diaria'
				  }
		})
		

});