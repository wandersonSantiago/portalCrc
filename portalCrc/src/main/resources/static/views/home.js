app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('home', {
			abstract : true,
			url : '/',
			templateUrl : 'views/home.index.html',
			ncyBreadcrumb: {
				    label: 'home'
				  }
		})
		.state('home.menu', {
			url : "",
			templateUrl : "views/home.html",
			controller : "HomeListarController as homeCtrl",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Home'
				  }
		})
	
		
});