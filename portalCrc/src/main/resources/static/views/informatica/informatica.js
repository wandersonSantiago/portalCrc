app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('informatica', {
			abstract : true,
			url : '/informatica',
			templateUrl : 'views/informatica/informatica.index.html',
			ncyBreadcrumb: {
				    label: 'Informatica'
				  }
		})
		.state('informatica.menu', {
			url : "/menu",
			templateUrl : "views/informatica/home.html",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'informatica'
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