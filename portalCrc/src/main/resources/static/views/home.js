app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('home', {
			abstract : true,
			url : '/',
			templateUrl : 'views/home.index.html',
			ncyBreadcrumb: {
				    label: 'Home'
				  }
		})
		.state('home.menu', {
			url : "",
			templateUrl : "views/home.html",
			ncyBreadcrumb: {
				 	parent: 'home',
				    label: 'Menu'
				  }
		})
	
});