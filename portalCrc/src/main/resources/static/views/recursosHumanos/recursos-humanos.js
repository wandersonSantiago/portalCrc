app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider
		.state('recursos-humanos', {
			abstract : true,
			url : '/recursos-humanos',
			templateUrl : 'views/recursosHumanos/recursos.humanos.index.html',
			ncyBreadcrumb: {
				parent : "home.menu",
				    label: 'Recursos Humanos'
				  }
		})
		.state('recursos-humanos.menu', {
			url : "/menu",
			templateUrl : "views/recursosHumanos/home.html",
			controller : "UsuarioListarController as ctrl",
			ncyBreadcrumb: {
				 	parent: 'recursos-humanos',
				    label: 'Recursos Humanos'
				  }
		})
	
	


		
});