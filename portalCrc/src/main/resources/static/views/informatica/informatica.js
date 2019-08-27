app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('informatica', {
			abstract : true,
			url : '/informatica',
			templateUrl : 'views/informatica/informatica.index.html',
			ncyBreadcrumb: {
				parent: 'home.menu',
				    label: 'Informatica'
				  }
		})
		.state('informatica.menu', {
			url : "/menu",
			templateUrl : "views/informatica/home.html",
			ncyBreadcrumb: {
				 	parent: 'informatica',
				    label: 'menu'
				  }
		})	
		
		.state('informatica.controleIp', {
			url : "/controle/ip",
			templateUrl : "views/informatica/controleIp/home.html",
			ncyBreadcrumb: {
				 	parent: 'informatica.menu',
				    label: 'controle Ip '
				  }
		})	
});