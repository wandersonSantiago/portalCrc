pp.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('infraestrutura', {
			abstract : true,
			url : '/infraestrutura',
			templateUrl : 'views/infra/infra.index.html',
			ncyBreadcrumb: {
				    label: 'Infraestrutura'
				  }
		})
		.state('infraestrutura.menu', {
			url : "/menu",
			templateUrl : "views/infra/home.html",
			ncyBreadcrumb: {
				 	parent: 'infraestrutura',
				    label: 'Menu'
				  }
		})	
		
});