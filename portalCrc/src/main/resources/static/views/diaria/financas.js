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
			templateUrl : "views/diaria/home.html",
			controller : "DiariaListarController as diariaCtrl",
			ncyBreadcrumb: {
				 	parent: 'financas',
				    label: 'Finanças'
				  }
		})
		

});